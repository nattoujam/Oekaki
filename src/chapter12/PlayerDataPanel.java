/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author local-nattou
 */
public class PlayerDataPanel extends JPanel {
    
    private final JTextArea dataArea;
    
    public PlayerDataPanel() {
        dataArea = new JTextArea();
        this.setLayout(new BorderLayout());
        this.add(dataArea, BorderLayout.CENTER);
    }
    
    public void addPlayer(UserData userData) {
        dataArea.append(userData.getName() + userData.getColor().toString() + "\r\n");
    }
    
    public void setTheme(String theme) {
        dataArea.append(theme);
    }
}
