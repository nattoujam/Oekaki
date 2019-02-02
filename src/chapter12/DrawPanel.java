/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 *
 * @author local-nattou
 */
public class DrawPanel extends JPanel {
    private final DrawComponent drawComponent;
    private final MouseMotionListener mv;
    
    public DrawPanel() {
        drawComponent = new DrawComponent();
        mv = new MouseMove(drawComponent);
        addMouseMotionListener(mv);
        
        this.setLayout(new BorderLayout());
        this.add(drawComponent, BorderLayout.CENTER);
        //this.setBackground(Color.LIGHT_GRAY);
    }
}
