/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

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
public class ReceiveFromClientThread extends Thread {
    
    private final List<ObjectOutputStream> senders;
    private final ObjectInputStream receiver;
    
    public ReceiveFromClientThread(List<ObjectOutputStream> senders, ObjectInputStream receiver) {
        this.senders = senders;
        this.receiver = receiver;
    }
    
    @Override
    public void run() {
        while(true) {
            receiveFromClient();
        }
    }
    
    //クライアント全員に共有
    private void shareWithClient(Packet packet) {
        try {
            for(ObjectOutputStream sender : senders) {
                System.out.println("Share");
                sender.writeObject(packet);
                sender.flush();
            }
        }
        catch (IOException ex) {
            Logger.getLogger(NetworkServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //クライアントから受信
    private void receiveFromClient() {
        try {
            System.out.println("Receive");
            Packet packet = (Packet)receiver.readObject();

            if(packet != null) shareWithClient(packet);
        }
        catch (IOException ex) {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
