/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

/**
 *
 * @author local-nattou
 */
public class PenButton extends JButton{
    private Color color;
    private final Border off;
    private final Border on;
    
    public PenButton(Color c) {
        this.color = c;
        this.setBackground(c);
        on = new CompoundBorder(new LineBorder(new Color(255, 150, 150), 3), new LineBorder(Color.WHITE));
        off = new CompoundBorder(new LineBorder(color), new LineBorder(color));
        this.setBorder(off);
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void isPressed(boolean b) {
        this.setBorder(b ? on : off);
    }
}
