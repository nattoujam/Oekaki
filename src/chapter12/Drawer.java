/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;

/**
 *
 * @author local-nattou
 */
public class Drawer implements MouseMotionListener, MouseListener {
    private final DrawComponent dCom; 
    private Pen pen;
    
    public Drawer(DrawComponent d) {
        this.dCom = d;
        this.pen = new CirclePen();
    }
    
    public void setPen(Pen p) {
        this.pen = p;
    }
    
    //MouseMotionListener
    @Override
    public void mouseDragged(MouseEvent e) {
        pen.draw(e.getPoint());
        dCom.repaint();
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
        pen.penInit(dCom.getGraphics2D(), e.getPoint());
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
