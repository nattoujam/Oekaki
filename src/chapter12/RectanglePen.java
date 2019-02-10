/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.BasicStroke;
import java.awt.Point;
import java.awt.Stroke;

/**
 *
 * @author local-nattou
 */
public class RectanglePen extends Pen {

    @Override
    public String toString() {
        return "RectanglePen";
    }
    
    @Override
    protected void penDraw(Point p) {
        g2D.fillRect(p.x - radius, p.y - radius, radius * 4, radius * 2);
    }
    
}
