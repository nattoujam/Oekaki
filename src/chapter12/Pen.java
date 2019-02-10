/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

/**
 *
 * @author local-nattou
 */
public abstract class Pen {
    private Color color = Color.BLACK;
    protected int radius = 5;
    protected Graphics2D g2D;
    private Point previousPoint;
    
    public void penInit(Graphics2D g, Point first) {
        this.g2D = g;
        this.previousPoint = first;
        Stroke s = new BasicStroke(radius * 2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2D.setStroke(s);
    }
    
    public void draw(Point p) {
        g2D.setColor(color);
        penDraw(p);
        linerComplement(p);
    }
    
    public void setColor(Color c) {
        this.color = c;
    }
    
    public void setRadius(int r) {
        this.radius = r;
    }
    
    //名前を決める
    @Override
    public abstract String toString();
    
    //ラインの描き方
    protected abstract void penDraw(Point p);
    
    private void linerComplement(Point p) {
        g2D.drawLine(previousPoint.x, previousPoint.y, p.x, p.y);
        this.previousPoint = p;
    }
    
}
