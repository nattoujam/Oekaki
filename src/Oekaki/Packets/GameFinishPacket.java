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
public class GameFinishPacket extends Packet {
    
    private final long finishTime;
    
    public GameFinishPacket(UserData sender, long time) {
        super(sender);
        this.finishTime = time;
    }

    /**
     * @return the finishTime
     */
    public long getFinishTime() {
        return finishTime;
    }
}
