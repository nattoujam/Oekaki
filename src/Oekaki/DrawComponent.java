/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oekaki;

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

    private BufferedImage buffer;
    private Graphics2D bg;
    
    public Graphics2D getGraphics2D() {
        return bg;
    }
    
    public void clear() {
        makeBufferedImage();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(buffer, this.getX(), this.getY(), this);
        g.dispose();
    }
    
    private void makeBufferedImage() {
        buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        bg = buffer.createGraphics();
        bg.setColor(Color.white);
        bg.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
    }
}
