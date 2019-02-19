/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12.Packets;

import chapter12.Pens.Pen;
import chapter12.Packets.Packet;
import chapter12.UserData;
import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author local-nattou
 */
public class MousePressedPacket extends Packet {
    
    private final Pen pen;
    private final Point point;
    
    //
    //penをシリアライズすると、color, radiusの情報が失われるため、退避
    //
    private final Color color;
    private final int radius;
    
    public MousePressedPacket(UserData sender, Pen pen, Point point) {
        super(sender);
        this.pen = pen;
        this.point = point;
        this.color = pen.getColor();
        this.radius = pen.getRadius();
    }

    /**
     * @return the pen
     */
    public Pen getPen() {
        pen.setColor(color);
        pen.setRadius(radius);
        return pen;
    }

    /**
     * @return the point
     */
    public Point getPoint() {
        return point;
    }
}
