/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;

/**
 *
 * @author local-nattou
 */
public class Drawer {
    private DrawComponent dCom; 
    private Pen pen;
    
    public Drawer() {
        this.pen = new CirclePen();
    }
    
    //描画場所
    public void setDrawComponent(DrawComponent d) {
        this.dCom = d;
    }
    
    //使うペン
    public void setPen(Pen p) {
        this.pen = p;
    }
    
    //描画準備メソッドの実行
    public void prepareDrawing(Point p) {
        pen.penInit(dCom.getGraphics2D(), p);
    }
    
    //軌跡描画メソッドの実行
    public void doDrawing(Point p) {
        pen.draw(p);
        dCom.repaint();
    }
}
