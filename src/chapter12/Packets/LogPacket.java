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
public class LogPacket extends Packet {
    
    private final Object time;
    private final String log;
    
    public LogPacket(UserData sender, Object time, String log) {
        super(sender);
        this.time = time;
        this.log = log;
    }

    /**
     * @return the time
     */
    public Object getTime() {
        return time;
    }

    /**
     * @return the log
     */
    public String getLog() {
        return log;
    }
}
