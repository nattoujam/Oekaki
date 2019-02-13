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
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author local-nattou
 */
public class DrawPanel extends JPanel {
    private final DrawComponent dCom;
    private final MouseInput mi;
    
    public DrawPanel(Drawer d) {
        this.setBackground(Color.RED);
        this.setLayout(new BorderLayout(0, 5));
        
        this.dCom = new DrawComponent();
        d.setDrawComponent(dCom);
        this.add(dCom, BorderLayout.CENTER);
        
        this.mi = new MouseInput(d);
        
        JButton reset = new JButton("レイヤークリア");
        reset.setFocusPainted(false);
        reset.addActionListener(e -> {
            dCom.makeBufferedImage();
            dCom.repaint();
        });
        this.add(reset, BorderLayout.SOUTH);
    }
    
    //マウス受付の切り替え
    public void changeMauseInputReception(boolean b)
    {
        if(b) {
            this.addMouseMotionListener(mi);
            this.addMouseListener(mi);
        }
        else {
            this.removeMouseMotionListener(mi);
            this.removeMouseListener(mi);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        //System.out.println(dCom.getGraphics2D());
        if(dCom.getGraphics2D() == null) {
            dCom.makeBufferedImage();
        }
    }
}
