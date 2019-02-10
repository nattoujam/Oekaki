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
    private final DrawComponent dCom;
    private final MouseInput mi;
    
    public DrawPanel(int width, int height, Drawer d) {
        this.setSize(width, height);
        this.setLayout(new BorderLayout());
        
        this.dCom = new DrawComponent(this.getWidth(), this.getHeight());
        d.setDrawComponent(dCom);
        this.add(dCom, BorderLayout.CENTER);
        
        this.mi = new MouseInput(d);
        
    }
    
    //マウス受付の切り替え
    public void changeMauseInputReception(boolean b) {
        if(b) {
            this.addMouseMotionListener(mi);
            this.addMouseListener(mi);
        }
        else {
            this.removeMouseMotionListener(mi);
            this.removeMouseListener(mi);
        }
    }
}
