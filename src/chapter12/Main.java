/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.BorderLayout;
import javax.swing.*;

/**
 *
 * @author local-nattou
 */
public class Main {
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
                final JFrame frame = new JFrame();
                frame.setBounds(100, 100, 400, 300);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(null);
                
                Drawer drawer = new Drawer();
                
                Palette palette = new Palette(drawer);
                palette.setBounds(0, 0, 300, 50);
                
                DrawPanel drawPanel = new DrawPanel(200, 200, drawer);
                drawPanel.setLocation(50, 50);
                drawPanel.changeMauseInputReception(true);
                
                //Thread thread = new Thread(drawPanel);
                //thread.start();
                
                frame.add(drawPanel);
                frame.add(palette);
                
                //frame.getContentPane().add(drawPanel);
                frame.setVisible(true);
        });
    }
    
}
