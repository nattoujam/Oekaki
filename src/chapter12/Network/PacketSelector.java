/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12.Network;

import chapter12.Packets.Packet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author local-nattou
 */
public class PacketSelector {
    //ソケットサブクラス別イベントのリスト
    private final HashMap<Class, List<Consumer<Packet>>> packetHandlers;
    
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
    
    public void receive(Packet data) {
        System.out.println("Receive:[" + data.getClass() + "]");
        List<Consumer<Packet>> handlers = packetHandlers.get(data.getClass());
        for(Consumer<Packet> c : handlers) {
            c.accept(data);
        }
    }
}
