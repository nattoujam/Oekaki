/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12.Network;

import chapter12.Packets.Packet;
import chapter12.UserData;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author local-nattou
 */

//client
public class NetworkClient implements Runnable {

    private final PacketSelector packetSelector;
    
    private Socket socket;
    private ObjectOutputStream sender;
    private ObjectInputStream receiver;
    private UserData myData;
    
    public NetworkClient() {
        packetSelector = new PacketSelector();
    }
    
    //サーバーに接続
    public void connect(String IP, int port) {
        try {
            this.socket = new Socket(IP, port);
            this.sender = new ObjectOutputStream(socket.getOutputStream());
            this.receiver = new ObjectInputStream(socket.getInputStream());
        }
        catch (IOException ex) {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //サーバーに集約
    public synchronized void aggregation(Packet p) {
        try {
            System.out.println("aggregation:" + p.getUserData().getName() + "(Client) → [" + p.getClass() + "]");
            sender.writeObject(p);
            sender.flush();
        }
        catch (IOException ex) {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close() {
        try {
            sender.close();
            receiver.close();
            socket.close();
        }
        catch (IOException ex) {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void run() {
        try {
            //サーバーから受信
            while(true) {
                Packet packet = (Packet)receiver.readObject();
                System.out.println("Receive at Client");
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

    /**
     * @return the packetSelector
     */
    public PacketSelector getPacketSelector() {
        return packetSelector;
    }

    /**
     * @return the myData
     */
    public UserData getMyData() {
        return myData;
    }

    /**
     * @param myData the myData to set
     */
    public void setMyData(UserData myData) {
        this.myData = myData;
    }
}
