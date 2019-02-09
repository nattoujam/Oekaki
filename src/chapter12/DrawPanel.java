/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 *
 * @author local-nattou
 */
public class DrawPanel extends JPanel {
    private final DrawComponent drawComponent;
    private final Drawer d;
    
    public DrawPanel(int width, int height) {
        this.setSize(width, height);
        this.drawComponent = new DrawComponent(this.getWidth(), this.getHeight());
        this.d = new Drawer(drawComponent);
        this.addMouseMotionListener(d);
        this.addMouseListener(d);
        
        this.setLayout(new BorderLayout());
        this.add(drawComponent, BorderLayout.CENTER);
    }
}
