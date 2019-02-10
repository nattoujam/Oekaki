/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author local-nattou
 */
public class PenButton extends JButton{
    private Color color;
    
    public PenButton(Color c) {
        this.color = c;
        this.setBackground(c);
    }
    public Color getColor() {
        return this.color;
    }
}
