/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

/**
 *
 * @author local-nattou
 */
public class GameStartPacket extends Packet {
    
    private final String drawer;
    private final String theme;
    
    public GameStartPacket(UserData sender, String drawer, String theme) {
        super(sender);
        this.drawer = drawer;
        this.theme = theme;
    }

    /**
     * @return the drawer
     */
    public String getDrawer() {
        return drawer;
    }

    /**
     * @return the theme
     */
    public String getTheme() {
        return theme;
    }
    
}
