/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oekaki.Packets;

import Oekaki.UserData;

/**
 *
 * @author local-nattou
 */
public class GameReadyPacket extends Packet {
    
    private final boolean isReady; 
    
    public GameReadyPacket(UserData sender, boolean isReady) {
        super(sender);
        this.isReady = isReady;
    }

    /**
     * @return the isReady
     */
    public boolean isReady() {
        return isReady;
    }
    
}
