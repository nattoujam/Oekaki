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
public class MouseDraw extends JComponent implements MouseMotionListener {
     private final ArrayList<Point> points = new ArrayList<>();
     
     public MouseDraw() {
         addMouseMotionListener(this);
     }

    @Override
    public void mouseDragged(MouseEvent e) {
         points.add(new Point(e.getX(),e.getY()));
         repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected void paintComponent(Graphics g) {
       final int radius = 10;
 
       for(Point p : points){
           //g.drawRect(p.x, p.y, 1, 1);
           g.fillOval(p.x, p.y, radius, radius);
       }
       //g.fillOval(p.x, p.y, radius, radius);
    }
}
