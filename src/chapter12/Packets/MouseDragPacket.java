/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12.Packets;

import chapter12.UserData;
import java.awt.Point;

/**
 *
 * @author local-nattou
 */
public class MouseDragPacket extends Packet {
    
    private final Point point;
    
    public MouseDragPacket(UserData sender, Point point) {
        super(sender);
        this.point = point;
    }

    /**
     * @return the point
     */
    public Point getPoint() {
        return point;
    }
}
