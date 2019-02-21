/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oekaki.Packets;

import Oekaki.Packets.Packet;
import Oekaki.UserData;
import java.awt.Color;

/**
 *
 * @author local-nattou
 */
public class ColorPacket extends Packet {
    private final Color color;

    public ColorPacket(UserData sender, Color color) {
        super(sender);
        this.color = color;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }
}
