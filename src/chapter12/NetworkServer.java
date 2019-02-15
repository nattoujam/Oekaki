/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

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
    
    private ServerSocket server;
    private final List<Socket> clients;
    private final List<ObjectOutputStream> senders;
    private final List<ObjectInputStream> receivers;
    private final int numOfClients; 
    
    public NetworkServer(int numOfClients) {
        this.clients = new ArrayList<>();
        this.senders = new ArrayList<>();
        this.receivers = new ArrayList<>();
        this.numOfClients = numOfClients;
    }
    
    //クライアント追加
    private void addClient(Socket sc) {
        clients.add(sc);
        try {
            senders.add(new ObjectOutputStream(sc.getOutputStream()));
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

            System.out.println("Welcom!");
            addClient(sc);
        }
        catch(IOException ex)
        {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void startClientReceiverThread(ObjectInputStream receiver) {
        Thread thread = new ReceiveFromClientThread(senders, receiver);
        thread.start();
        System.out.println("run");
    }
    
    public void close() {
        try {
            for(int i = 0; i < clients.size(); i++) {
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
            server = new ServerSocket(10000);
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
