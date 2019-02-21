/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12.Packets;

import chapter12.UserData;

/**
 *
 * @author local-nattou
 */
public class SEPacket extends Packet {
    
    private final int SEName;
    
    public SEPacket(UserData sender, int name) {
        super(sender);
        this.SEName = name;
    }

    /**
     * @return the SEName
     */
    public int getSEName() {
        return SEName;
    }
    
}
