/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

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

        g2D.setColor(color);
        g2D.setStroke(makeStroke());
        penDraw(first);
    }

    public void draw(Point p) {
        penDraw(p);
        manyPointComplement(p);
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
    protected abstract void penDraw(Point p);

    //点間に点をピクセル単位で描画して補間
    private void manyPointComplement(Point p) {
        double distance = Math.hypot(p.x - previousPoint.x, p.y - previousPoint.y);
        double dx = (p.x - previousPoint.x) / distance;
        double dy = (p.y - previousPoint.y) / distance;
        for (int i = 0; i < (int) distance; i++) {
            int x = previousPoint.x + (int) (dx * i);
            int y = previousPoint.y + (int) (dy * i);
            g2D.drawLine(x, y, x, y);
        }
        this.previousPoint = p;
    }
}
