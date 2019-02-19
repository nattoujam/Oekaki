/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author local-nattou
 */
public class Tools {
    public static JPanel LabeledJComponent(String label, JComponent jcomp) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 0));
        JLabel l = new JLabel(label);
        panel.add(l, BorderLayout.WEST);
        panel.add(jcomp, BorderLayout.CENTER);
        return panel;
    }
}
