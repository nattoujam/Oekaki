/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;


import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author local-nattou
 */
public class MouseMove implements MouseMotionListener {
    private final ArrayList<Point> points = new ArrayList<>();
    private DrawComponent d;
  
    public MouseMove() {
        
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        points.add(new Point(e.getX(),e.getY()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    
    
}
