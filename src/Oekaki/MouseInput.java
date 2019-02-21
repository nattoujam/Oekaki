/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oekaki;

import Oekaki.Packets.MousePressedPacket;
import Oekaki.Packets.MouseDragPacket;
import Oekaki.Network.NetworkClient;
import Oekaki.Pens.Pen;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author local-nattou
 */
public class MouseInput implements MouseMotionListener, MouseListener {

    private final DrawComponent dCom;
    private Pen pen;
    private Pen sendPen;
    private final NetworkClient client;
    private boolean canReception = true;
    
    public MouseInput(NetworkClient client, DrawComponent dCom) {
        this.client = client;
        this.dCom = dCom;
        client.getPacketSelector().addHandler(MousePressedPacket.class, p -> {
            System.out.println("receive(" + p.getPen().getColor() + ")");
            this.sendPen = p.getPen();
            drawInit(p.getPoint(), sendPen);
        });
        client.getPacketSelector().addHandler(MouseDragPacket.class, p -> {
            draw(p.getPoint(), sendPen);
        });
    }
    
    public void setPen(Pen pen) {
        this.pen = pen;
    }
    
    private void drawInit(Point p, Pen usePen) {
        usePen.penInit(dCom.getGraphics2D(), p);
        dCom.repaint();
    }
    
    private void draw(Point p, Pen usePen) {
        usePen.draw(dCom.getGraphics2D(), p);
        dCom.repaint();
    }
    
    //MouseMotionListener
    @Override
    public void mouseDragged(MouseEvent e) {
        if(canReception) {
            //draw(e.getPoint(), pen);
            //client.aggregation(new MouseDragPacket(client.getMyData(), e.getPoint()));
            client.aggregation(new MouseDragPacket(new ServerUserData(), e.getPoint()));
        }
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
        if(canReception) {
            //drawInit(e.getPoint(), pen);
            System.out.println("send(" + pen.getColor() + ")");
            //client.aggregation(new MousePressedPacket(client.getMyData(), pen, e.getPoint()));
            client.aggregation(new MousePressedPacket(new ServerUserData(), pen, e.getPoint()));
        }
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

    /**
     * @param canReception the canReception to set
     */
    public void setCanReception(boolean canReception) {
        this.canReception = canReception;
    }
}
