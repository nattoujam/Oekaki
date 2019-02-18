/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12.Pens;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

/**
 *
 * @author local-nattou
 */
public class CirclePen extends Pen {

    @Override
    public String getName() {
        return "CirclePen";
    }
    
    @Override
    protected void penDraw(Graphics2D g, Point p) {
        g.fillOval(p.x - radius, p.y - radius, radius * 2, radius * 2);
    }

    @Override
    protected Stroke makeStroke() {
        return new BasicStroke(radius * 2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    }
}
