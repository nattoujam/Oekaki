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
public class LogPacket extends Packet {
    
    private final long time;
    private final String log;
    
    public LogPacket(UserData sender, long time, String log) {
        super(sender);
        this.time = time;
        this.log = log;
    }

    /**
     * @return the time
     */
    public long getTime() {
        return time;
    }

    /**
     * @return the log
     */
    public String getLog() {
        return log;
    }
}
