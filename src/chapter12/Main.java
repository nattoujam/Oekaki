/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

/**
 *
 * @author local-nattou
 */
public class Main {
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
                final JFrame frame = new JFrame();
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setBounds(100, 100, 1000, 700);
                
                Drawer drawer = new Drawer();
                
                frame.setLayout(null);
                
                Palette palette = new Palette(drawer);
                palette.setBounds(305, 510, 500, 50);
                
                DrawPanel drawPanel = new DrawPanel(drawer);
                drawPanel.setBounds(305, 5, 500, 500);
                drawPanel.changeMauseInputReception(true);
                
                //Thread thread = new Thread(drawPanel);
                //thread.start();
                
                frame.add(drawPanel);
                frame.add(palette);
                
                //frame.pack();
                //frame.getContentPane().add(drawPanel);
                frame.setVisible(true);
        });
    }
}
