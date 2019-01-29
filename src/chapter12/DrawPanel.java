/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import javax.swing.JPanel;

/**
 *
 * @author local-nattou
 */
public class DrawPanel extends JPanel {
    private MouseMove mv;
    private DrawComponent drawcomponent;
    
    public DrawPanel() {
        mv = new MouseMove();
        drawcomponent = new DrawComponent();
        addMouseMotionListener(mv);
    }
    
    
}
