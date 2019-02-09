/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 *
 * @author local-nattou
 */
public class DrawComponent extends JComponent {

    private final BufferedImage buffer;
    private final Graphics2D bg;

    public DrawComponent(int width, int height) {
        this.setSize(width, height);
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bg = buffer.createGraphics();
        bg.setColor(Color.white);
        bg.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    
    public Graphics2D getGraphics2D() {
        return bg;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(buffer, this.getX(), this.getY(), this);
    }
}
