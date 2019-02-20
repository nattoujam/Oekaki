/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author local-nattou
 */
public class Canvas extends JPanel {
    
    private Image image;
    
    public Canvas() {
        
    }
    
    public void setImage(Image image) {
        this.image = image;
        this.setSize(this.image.getWidth(this), this.image.getHeight(this));
    }
    
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
