/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12.Pens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author local-nattou
 */
public abstract class Pen implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Color color = Color.BLACK;
    protected int radius = 5;
    private Point previousPoint;

    public void penInit(Graphics2D g, Point first) {
        this.previousPoint = first;

        g.setColor(getColor());
        g.setStroke(makeStroke());
        penDraw(g, first);
    }

    public void draw(Graphics2D g, Point p) {
        penDraw(g, p);
        manyPointComplement(g, p);
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public void setRadius(int r) {
        this.radius = r;
    }

    @Override
    public String toString() {
        return getName();
    }

    //名前を決める
    public abstract String getName();

    //ストローク生成
    protected abstract Stroke makeStroke();

    //ラインの描き方, 終点描画
    protected abstract void penDraw(Graphics2D g, Point p);

    //点間に点をピクセル単位で描画して補間
    private void manyPointComplement(Graphics2D g, Point p) {
        double distance = Math.hypot(p.x - previousPoint.x, p.y - previousPoint.y);
        double dx = (p.x - previousPoint.x) / distance;
        double dy = (p.y - previousPoint.y) / distance;
        for (int i = 0; i < (int) distance; i++) {
            int x = previousPoint.x + (int) (dx * i);
            int y = previousPoint.y + (int) (dy * i);
            g.drawLine(x, y, x, y);
        }
        this.previousPoint = p;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }
}
