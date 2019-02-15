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
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author local-nattou
 */

//client
public class NetworkClient implements Runnable {

    //ソケットサブクラス別イベントのリスト
    private final HashMap<Class, List<Consumer<Packet>>> packetHandlers;
    
    private Socket socket;
    private ObjectOutputStream sender;
    private ObjectInputStream receiver;
    
    {
        packetHandlers = new HashMap<>();
    }
    
    //ソケットサブクラス別ラムダ式のリスト要素追加
    public <T extends Packet> void addHandler(Class<T> clazz, Consumer<T> handler) {
        List<Consumer<Packet>> list = packetHandlers.get(clazz);
        if(list == null) { 
            list = new ArrayList<>();
            packetHandlers.put(clazz, list);
        }
        list.add(p -> handler.accept((T)p));
    }
    
    //サーバーに接続
    public void connect(int port) {
        try {
            this.socket = new Socket("localhost", port);
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
    
    //サーバーから受信
    @Override
    public void run() {
        try {
            while(true) {
                Packet data = (Packet)receiver.readObject();
                
                if(data == null) continue;
                
                List<Consumer<Packet>> handlers = packetHandlers.get(data.getClass());
                for(Consumer<Packet> c : handlers) {
                    c.accept(data);
                }
            }
        }
        catch (IOException ex) {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(NetworkClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
