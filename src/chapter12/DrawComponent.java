/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author local-nattou
 */
public class DrawComponent extends JComponent {

    //private final Palette palette;
    private final BufferedImage buffer;
    private final Graphics2D bg;
    private Point previousPoint;
    private int radius = 10;
    private Color color = Color.BLACK;

    public DrawComponent(int width, int height/*, Palette palette*/) {
        this.setSize(width, height);
        //this.palette = palette;
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bg = buffer.createGraphics();
        bg.setColor(Color.white);
        bg.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    
    public void startDraw(Point input) {
        previousPoint = input;
        //this.color = palette.getPenColor();
        //this.radius = palette.getPenRadius();
        Stroke st = new BasicStroke(radius * 2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        bg.setStroke(st);
    }

    public void drawPoint(Point input) {
        bg.setColor(color);
        bg.fillOval(input.x - radius, input.y - radius, radius * 2, radius * 2);
        linerComplement(input);
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(buffer, this.getX(), this.getY(), this);
    }
    
    private void linerComplement(Point p) {
        bg.drawLine(previousPoint.x, previousPoint.y, p.x, p.y);
        previousPoint = p;
    }
}
