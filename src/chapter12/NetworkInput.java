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
public class NetworkInput implements Runnable {

    //ソケットサブクラス別イベントのリスト
    private final HashMap<Class, List<Consumer<Packet>>> packetHandlers;
    
    private Socket socket;
    private ObjectOutputStream sender;
    private ObjectInputStream receiver;
    
    {
        packetHandlers = new HashMap<>();
    }
    
    public <T extends Packet> void addHandler(Class<T> clazz, Consumer<T> handler) {
        List<Consumer<Packet>> list = packetHandlers.get(clazz);
        if(list == null) { 
            list = new ArrayList<>();
            packetHandlers.put(clazz, list);
        }
        list.add(p -> handler.accept((T)p));
    }
    
    public void Connect(int port) {
        try {
            this.socket = new Socket("localhost", port);
            this.sender = new ObjectOutputStream(socket.getOutputStream());
            this.receiver = new ObjectInputStream(socket.getInputStream());
        }
        catch (IOException ex) {
            Logger.getLogger(NetworkInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        while(true) {
            System.out.println("receive");
            try {
                //１行受信の処理
                Packet data = (Packet)receiver.readObject();
                if(data == null) continue;
                List<Consumer<Packet>> handlers = packetHandlers.get(data.getClass());
                for(Consumer<Packet> c : handlers) {
                    c.accept(data);
                }
            }
            catch (IOException ex) {
                Logger.getLogger(NetworkInput.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (ClassNotFoundException ex) {
                Logger.getLogger(NetworkInput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
