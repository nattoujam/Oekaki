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
public class RectanglePen extends Pen {

    @Override
    public String getName() {
        return "RectanglePen";
    }
    
    @Override
    protected void penDraw(Graphics2D g, Point p) {
        g.fillRect(p.x - getRadius(), p.y - getRadius(), getRadius() * 2, getRadius() * 2);
    }

    @Override
    protected Stroke makeStroke() {
        return new BasicStroke(getRadius() * 2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL);
    }
}
