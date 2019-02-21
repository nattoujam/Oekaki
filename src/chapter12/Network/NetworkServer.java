/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12.Network;

import chapter12.GameManager;
import chapter12.Packets.ColorPacket;
import chapter12.Packets.Packet;
import chapter12.ServerUserData;
import chapter12.SoundEffect;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author local-nattou
 */

//server
public class NetworkServer implements Runnable {
    
    private final int port;
    private ServerSocket server;
    private final List<ObjectOutputStream> senders;
    private final List<ObjectInputStream> receivers;
    private final int numOfClients; 
    private final List<Color> colors;
    private final GameManager gm;
    
    public NetworkServer(int port, int numOfClients, GameManager gm) {
        this.port = port;
        this.senders = new ArrayList<>();
        this.receivers = new ArrayList<>();
        this.numOfClients = numOfClients;
        this.colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.ORANGE);
        colors.add(Color.MAGENTA);
        this.gm = gm;
    }
    
    //クライアント追加
    private void addClient(Socket sc) {
        try {
            ObjectOutputStream sender = new ObjectOutputStream(sc.getOutputStream());
            System.out.println("server → ColorPacket");
            sender.writeObject(new ColorPacket(new ServerUserData(), colors.get(0)));
            colors.remove(0);
            senders.add(sender);
            receivers.add(new ObjectInputStream(sc.getInputStream()));
        }
        catch (IOException ex) {
            Logger.getLogger(NetworkServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void connect() {
        try{
            System.out.println("Waiting now ...");
            //クライアント接続待機
            Socket sc = server.accept();

            System.out.println("Welcome!");
            addClient(sc);
        }
        catch(IOException ex)
        {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void startClientReceiverThread(ObjectInputStream receiver) {
        ShareClientsThread thread = new ShareClientsThread(senders, receiver, gm);
        gm.addTimeLimitMethid(thread::timeLimitMethod);
        thread.start();
        System.out.println("start ClientReceiverThread");
    }
    
//    public void sendToAllClients(Packet packet) {
//        for(ObjectOutputStream sender : senders) {
//            try {
//                sender.writeObject(packet);
//                sender.flush();
//            }
//            catch (IOException ex) {
//                Logger.getLogger(NetworkServer.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } 
//    }
    
    public void close() {
        try {
            for(int i = 0; i < receivers.size(); i++) {
                senders.get(i).close();
                receivers.get(i).close();
                server.close();
            }
        }
        catch (IOException ex) {
            Logger.getLogger(NetworkServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
        }
        catch (IOException ex) {
            Logger.getLogger(NetworkServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int i = 0; i < numOfClients; i++) {
            connect();
        }
        
        for(ObjectInputStream receiver : receivers) {
            startClientReceiverThread(receiver);
        }
    }
}
