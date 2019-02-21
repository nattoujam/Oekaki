/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oekaki.Pens;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.io.Serializable;

/**
 *
 * @author local-nattou
 */
public class CirclePen extends Pen implements Serializable{

    @Override
    public String getName() {
        return "CirclePen";
    }
    
    @Override
    protected void penDraw(Graphics2D g, Point p) {
        g.fillOval(p.x - getRadius(), p.y - getRadius(), getRadius() * 2, getRadius() * 2);
    }

    @Override
    protected Stroke makeStroke() {
        return new BasicStroke(getRadius() * 2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    }
}
