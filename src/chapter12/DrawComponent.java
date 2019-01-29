/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

/**
 *
 * @author local-nattou
 */
public class DrawComponent extends JComponent {
    private Point p;
    
    public DrawComponent() {
        
    }
    
    public void SetDrawPoint(Point input) {
        this.p = input;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
       final int radius = 10;
 
       //for(Point p : md.points){
       //    //g.drawRect(p.x, p.y, 1, 1);
       //    g.fillOval(p.x, p.y, radius, radius);
       //}
       g.fillOval(p.x, p.y, radius, radius);
    }
    
}
