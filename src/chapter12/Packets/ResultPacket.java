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
public class ResultPacket extends Packet {
    
    private final String drawer;
    private final String respondent;
    private final int score;
    private final boolean isTimeLimit;
    
    public ResultPacket(UserData sender, String drawer, String respondent, int score, boolean isTimeLimit) {
        super(sender);
        this.drawer = drawer;
        this.respondent = respondent;
        this.score = score;
        this.isTimeLimit = isTimeLimit;
    }

    /**
     * @return the drawer
     */
    public String getDrawer() {
        return drawer;
    }

    /**
     * @return the respondent
     */
    public String getRespondent() {
        return respondent;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the isTimeLimit
     */
    public boolean isIsTimeLimit() {
        return isTimeLimit;
    }
}
