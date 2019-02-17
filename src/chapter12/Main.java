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
            final JFrame initFrame = new JFrame();
            initFrame.setLocationRelativeTo(null);
            initFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            initFrame.setBounds(50, 50, 1000, 700);
            initFrame.setLayout(null);
            
            //ここから最初の画面
            JTextField inputName = new JTextField("");
            ServerForm serverForm = new ServerForm(initFrame, inputName);
            ClientForm clientForm = new ClientForm(initFrame, inputName);
            
            inputName.setBounds(500, 150, 100, 20);
            serverForm.setBounds(100, 300, 200, 200);
            clientForm.setBounds(450, 300, 200, 200);
            
            initFrame.add(inputName);
            initFrame.add(serverForm);
            initFrame.add(clientForm);
            initFrame.setVisible(true);
            //ここまで
            
            
            
            //frame.pack();
            //frame.getContentPane().add(drawPanel);
            //frame.setVisible(true);
        });
    }
}
