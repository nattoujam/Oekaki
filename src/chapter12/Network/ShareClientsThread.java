/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12.Network;

import chapter12.GameManager;
import chapter12.JTimer;
import chapter12.Packets.*;
import chapter12.ServerUserData;
import chapter12.SoundEffect;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author local-nattou
 */
public class ShareClientsThread extends Thread {
    
    private final PacketSelector packetSelector;
    private final List<ObjectOutputStream> senders;
    private final ObjectInputStream receiver;
    private final GameManager gm;
    
    public ShareClientsThread(List<ObjectOutputStream> senders, ObjectInputStream receiver, GameManager gm) {
        this.packetSelector = new PacketSelector();
        this.senders = senders;
        this.receiver = receiver;
        this.gm = gm;
        
        packetSelector.addHandler(UserDataPacket.class, p -> {
            sendToAllClients(p);
            this.gm.addPlayer(p.getUserData().getName());
        });
        packetSelector.addHandler(MousePressedPacket.class, this::sendToAllClients);
        packetSelector.addHandler(MouseDragPacket.class, this::sendToAllClients);
        packetSelector.addHandler(LayerClearPacket.class, this::sendToAllClients);
        packetSelector.addHandler(GameReadyPacket.class, p -> {
            //ゲーム開始
            if(this.gm.readyGame(p.isReady())) {
                sendToAllClients(new SEPacket(new ServerUserData(), SoundEffect.GAME_START));
                gm.init();
                nextGame();
            }
        });
        packetSelector.addHandler(LogPacket.class, p -> {
            sendToAllClients(p);
            //正解した場合
            if(this.gm.isCollectAnswer(p.getLog())) {
                sendToAllClients(new SEPacket(new ServerUserData(), SoundEffect.CORRECT));
                sendToAllClients(new LogPacket(new ServerUserData(), p.getTime(), p.getUserData().getName() + "さんが正解しました！\r\nお題は「" + this.gm.getTheme() + "」でした。"));
                sendToAllClients(new ResultPacket(new ServerUserData(), this.gm.getDrawer(), p.getUserData().getName(), this.gm.getScore(p.getTime()), false));
                nextGame();
            }
        });
    }
    
    public void timeLimitMethod() {
        //時間切れの場合
            sendToAllClients(new SEPacket(new ServerUserData(), SoundEffect.UNCORRECT));
            sendToAllClients(new ResultPacket(new ServerUserData(), null, null, 0, true));
            sendToAllClients(new LogPacket(new ServerUserData(), System.currentTimeMillis(), "時間切れです。\r\nお題は「" + this.gm.getTheme() + "」でした。"));
            nextGame();
    }
    
    private void nextGame() {
        if(gm.isFinish()) {
            System.out.println("Finish");
            //timer.stop();
            sendToAllClients(new LogPacket(new ServerUserData(), System.currentTimeMillis(), "ゲームが終了しました。"));
            sendToAllClients(new GameFinishPacket(new ServerUserData(), System.currentTimeMillis()));
        }
        else {
            //timer.start();
            gm.startTimer();
            String drawer = gm.getNextDrawer();
            sendToAllClients(new LogPacket(new ServerUserData(), System.currentTimeMillis(), drawer + "さんが描き手です！"));
            sendToAllClients(new GameStartPacket(new ServerUserData(), drawer, gm.getNextTheme()));
        }
    }
    
    @Override
    public void run() {
        try {
            //クライアントから受信
            while(true) {
                Packet packet = (Packet)receiver.readObject();
                System.out.println("Receive at ShareClientsThread");
                packetSelector.receive(packet);
            }
        }
        catch (IOException ex) {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //クライアント全員に共有
    synchronized private void sendToAllClients(Packet packet) {
        try {
            for(ObjectOutputStream sender : senders) {
                System.out.println("Share:" + packet.getUserData().getName() + "(ShareClientsThread)" + " → " + "[" + packet.getClass() + "]");
                sender.writeObject(packet);
                sender.flush();
            }
        }
        catch (IOException ex) {
            Logger.getLogger(NetworkServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the packetSelector
     */
    public PacketSelector getPacketSelector() {
        return packetSelector;
    }
}
