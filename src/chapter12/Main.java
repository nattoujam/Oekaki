/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import javax.swing.*;

/**
 *
 * @author local-nattou
 */
public class Main {
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
 
            @Override
            public void run() {
                final JFrame frame = new JFrame();
                frame.setBounds(100, 100, 400, 300);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                DrawPanel drawPanel = new DrawPanel();
                
                //Thread thread = new Thread(drawPanel);
                //thread.start();
 
                frame.add(drawPanel);
                
                //frame.getContentPane().add(drawPanel);
                frame.setVisible(true);
            }
        });
    }
    
}
