/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oekaki.Packets;

import Oekaki.Packets.Packet;
import Oekaki.UserData;

/**
 *
 * @author local-nattou
 */
public class GameStartPacket extends Packet {
    
    private UserData drawerData;
    private String theme;
    
    public GameStartPacket(UserData sender, UserData drawer, String theme) {
        super(sender);
        this.drawerData = drawer;
        this.theme = theme;
    }

    public GameStartPacket(UserData sender) {
        super(sender);
    }

    /**
     * @return the drawer
     */
    public UserData getDrawer() {
        return drawerData;
    }

    /**
     * @return the theme
     */
    public String getTheme() {
        return theme;
    }
    
}
