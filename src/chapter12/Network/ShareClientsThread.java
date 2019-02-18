/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12.Network;

import chapter12.GameManager;
import chapter12.Packets.UserDataPacket;
import chapter12.Packets.GameStartPacket;
import chapter12.Packets.LogPacket;
import chapter12.Packets.MouseDragPacket;
import chapter12.Packets.MousePressedPacket;
import chapter12.Packets.Packet;
import chapter12.ServerUserData;
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
        
        packetSelector.addHandler(UserDataPacket.class, this::sendToAllClients);
        packetSelector.addHandler(MousePressedPacket.class, this::sendToAllClients);
        packetSelector.addHandler(MouseDragPacket.class, this::sendToAllClients);
        packetSelector.addHandler(LogPacket.class, p -> {
            sendToAllClients(p);
            if(gm.isCollectAnswer(p.getLog())) {
                sendToAllClients(new GameStartPacket(new ServerUserData(), gm.getNextDrawer(), gm.getNextTheme()));
            }
        });
    }
    
    @Override
    public void run() {
        try {
            //クライアントから受信
            while(true) {
                Packet packet = (Packet)receiver.readObject();
                System.out.println("Receive at ShareClientsThread");
                if(packet != null) packetSelector.receive(packet);
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
    private void sendToAllClients(Packet packet) {
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
