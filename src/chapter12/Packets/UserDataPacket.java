/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12.Packets;

import chapter12.Packets.Packet;
import chapter12.UserData;
import java.awt.Color;

/**
 *
 * @author local-nattou
 */
public class UserDataPacket extends Packet {
    
    private final UserData data;
    
    public UserDataPacket(UserData sender) {
        super(sender);
        this.data = sender;
    }

    /**
     * @return the data
     */
    public UserData getUserData() {
        return data;
    }
    
}
