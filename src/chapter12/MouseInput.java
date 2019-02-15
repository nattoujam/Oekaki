/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author local-nattou
 */
public class MouseInput implements MouseMotionListener, MouseListener {

    private Drawer d;

    public MouseInput(Drawer d) {
        this.d = d;
    }

    //MouseMotionListener
    @Override
    public void mouseDragged(MouseEvent e) {
        d.doDrawing(e.getPoint());
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
        d.prepareDrawing(e.getPoint());
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
