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
            final JFrame frame = new JFrame();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBounds(50, 50, 1000, 700);
            frame.setLayout(null);

            UserData you = new UserData("Anago", Color.RED);

            Drawer drawer = new Drawer();

            AnswerPanel aPanel = new AnswerPanel(you);
            aPanel.setBounds(5, 5, 300, 600);

            Palette palette = new Palette(drawer);
            palette.setBounds(310, 560, 500, 50);

            DrawPanel drawPanel = new DrawPanel(drawer);
            drawPanel.setBounds(310, 5, 500, 550);
            drawPanel.changeMauseInputReception(true);

            //Thread thread = new Thread(drawPanel);
            //thread.start();
            frame.add(aPanel);
            frame.add(drawPanel);
            frame.add(palette);

            //frame.pack();
            //frame.getContentPane().add(drawPanel);
            frame.setVisible(true);
        });
    }
}
