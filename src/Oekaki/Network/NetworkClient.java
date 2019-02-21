/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oekaki.Network;

import Oekaki.Packets.ClosedPacket;
import Oekaki.Packets.ColorPacket;
import Oekaki.Packets.Packet;
import Oekaki.Packets.UserDataPacket;
import Oekaki.UserData;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author local-nattou
 */

//client
public class NetworkClient implements Runnable{

    private final PacketSelector packetSelector;
    
    private Socket socket;
    private ObjectOutputStream sender;
    private ObjectInputStream receiver;
    private UserData myData;
    private String myName;
    
    public NetworkClient() {
        packetSelector = new PacketSelector();
        packetSelector.addHandler(ClosedPacket.class, p -> {
            JOptionPane.showMessageDialog(null, "切断されました。\r\nアプリケーションを終了します。", "業務連絡", JOptionPane.PLAIN_MESSAGE);
            //this.close();
            System.exit(0);
        });
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
    synchronized public void aggregation(Packet p) {
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
                Packet packet = (Packet) receiver.readObject();
                System.out.println("Receive at " + myName + "(Client)");
                if(packet.getUserData().getName().equals(myName)) {
                    System.out.println("Ignore reception");
                    continue;
                }
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

    /**
     * @return the packetSelector
     */
    public PacketSelector getPacketSelector() {
        return packetSelector;
    }
    
    public void setName(String name) {
        this.myName = name;
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
