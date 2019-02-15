/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class NetworkSender {
    
    private final int port = 10000;
    private ObjectOutputStream sender;
    private ObjectInputStream receiver;
    
    private final List<Socket> clients;
    
    public NetworkSender() {
        clients = new ArrayList<>();
    }
    
    public void addClient(Socket sc) {
        clients.add(sc);
        try {
            this.sender = new ObjectOutputStream(sc.getOutputStream());
            this.receiver = new ObjectInputStream(sc.getInputStream());
        }
        catch (IOException ex) {
            Logger.getLogger(NetworkSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Send(Packet packet) {
        try {
            sender.writeObject(packet);
            sender.flush();
            //System.out.println("Send!!!!");
        }
        catch (IOException ex) {
            Logger.getLogger(NetworkSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
