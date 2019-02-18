/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import chapter12.Packets.MousePressedPacket;
import chapter12.Packets.MouseDragPacket;
import chapter12.Network.NetworkClient;
import chapter12.Pens.Pen;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author local-nattou
 */
public class MouseInput implements MouseMotionListener, MouseListener {

    private DrawComponent dCom; //final
    private Pen pen;
    private final NetworkClient client;
    
    public MouseInput(NetworkClient client) {
        this.client = client;
        client.getPacketSelector().addHandler(MousePressedPacket.class, p -> {
            this.pen = p.getPen();
            drawInit(p.getPoint());
        });
        client.getPacketSelector().addHandler(MouseDragPacket.class, p -> {
            draw(p.getPoint());
        });
    }
    
    public void setDrawComponent(DrawComponent dCom) {
        this.dCom = dCom;
    }
    
    public void setPen(Pen pen) {
        this.pen = pen;
    }
    
    private void drawInit(Point p) {
        pen.penInit(dCom.getGraphics2D(), p);
        dCom.repaint();
    }
    
    private void draw(Point p) {
        pen.draw(dCom.getGraphics2D(), p);
        dCom.repaint();
    }
    
    //MouseMotionListener
    @Override
    public void mouseDragged(MouseEvent e) {
        draw(e.getPoint());
        client.aggregation(new MouseDragPacket(client.getMyData(), e.getPoint()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    //MouseLisetener
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawInit(e.getPoint());
        client.aggregation(new MousePressedPacket(client.getMyData(), pen, e.getPoint()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
