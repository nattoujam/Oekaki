/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author local-nattou
 */
public class Main {

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            boolean isHost = false;
            
            final JFrame frame = new JFrame();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBounds(50, 50, 1000, 700);
            frame.setLayout(null);

            UserData you = new UserData("Anago", Color.RED);

            Drawer drawer = new Drawer();
            
            //server
            NetworkServer sender = new NetworkServer(3);
            //client
            NetworkClient input = new NetworkClient();
            
            JButton b2 = new JButton("server");
            b2.setBounds(850, 150, 100, 100);
            b2.addActionListener(e -> {
                Thread thread = new Thread(sender);
                thread.start();
                
                input.connect(10000);
                Thread thread2 = new Thread(input);
                thread2.start();
            });
            frame.add(b2);
            
            JButton b = new JButton();
            b.setBounds(850, 5, 100, 100);
            b.addActionListener(e -> {
                you.color = Color.BLUE;
                input.connect(10000);
                Thread thread = new Thread(input);
                thread.start();
            });
            frame.add(b);

            AnswerPanel aPanel = new AnswerPanel(you, input);
            aPanel.setBounds(5, 5, 300, 600);

            Palette palette = new Palette(drawer);
            palette.setBounds(310, 560, 500, 50);

            DrawPanel drawPanel = new DrawPanel(drawer);
            drawPanel.setBounds(310, 5, 500, 550);
            drawPanel.changeMauseInputReception(true);

            
            
            frame.add(aPanel);
            frame.add(drawPanel);
            frame.add(palette);

            //frame.pack();
            //frame.getContentPane().add(drawPanel);
            frame.setVisible(true);
        });
    }
}
