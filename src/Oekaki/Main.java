/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oekaki;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author local-nattou
 */
public class Main {
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
        });
    }
    
    public Main() {
        ImageIcon image = new ImageIcon(getClass().getResource("/Image/frontpage.png"));
            
        final JFrame initFrame = new JFrame();
        initFrame.setTitle("おえか木");
        initFrame.setLocationRelativeTo(null);
        initFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initFrame.setBounds(50, 50, 1025, 650);
        initFrame.setLayout(null);
        initFrame.setResizable(false);

        SoundEffect se = new SoundEffect();

        //ここから最初の画面
        JTextField inputName = new JTextField("");
        inputName.setHorizontalAlignment(JTextField.CENTER);
        
        ServerForm serverForm = new ServerForm(initFrame, inputName, se);
        ClientForm clientForm = new ClientForm(initFrame, inputName, se);
        JPanel networkForm = new JPanel();
        GridLayout layout = new GridLayout(1, 2);
        layout.setHgap(10);
        networkForm.setLayout(layout);
        
        networkForm.add(serverForm);
        networkForm.add(clientForm);
        
        
        JPanel panel = new JPanel();
        panel.setBorder(new CompoundBorder(
                new CompoundBorder(
                        new LineBorder(Color.WHITE, 5), 
                        new BevelBorder(BevelBorder.LOWERED)), 
                new LineBorder(new Color(240, 240, 240), 5)
        ));
        panel.setLayout(new BorderLayout(10, 5));
        panel.setBounds(273, 300, 470, 200);

        panel.add(Tools.LabeledJComponent("おなまえ", inputName), BorderLayout.NORTH);
        panel.add(networkForm, BorderLayout.CENTER);
        
        
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBounds(0, 0, 1025, 650);
        
        initFrame.add(panel);
        initFrame.add(imageLabel);
        initFrame.setVisible(true);
        //ここまで
        se.frontPageBGM();
    }
}
