/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author local-nattou
 */
public class DrawComponent extends JComponent {

    private ArrayList<Point> pointBuffer;

    public DrawComponent() {
        pointBuffer = new ArrayList<Point>();
        this.setBackground(Color.red);
    }

    public void SetDrawPoint(Point input) {
        this.pointBuffer.add(input);
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        final int radius = 10;

        //for(Point p : md.points){
        //    //g.drawRect(p.x, p.y, 1, 1);
        //    g.fillOval(p.x, p.y, radius, radius);
        //}
        for (Point p : pointBuffer) {
            g.fillOval(p.x, p.y, radius, radius);
        }
        pointBuffer.clear();
    }
}
