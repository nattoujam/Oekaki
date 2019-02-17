/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author local-nattou
 */
public class ClientForm extends JPanel {
    public ClientForm(JFrame initFrame, JTextField inputName) {
        this.setBorder(new TitledBorder(new EtchedBorder(), "クライアント"));
        GridLayout layout = new GridLayout(3, 1);
        this.setLayout(layout);
        layout.setVgap(50);
        
        JTextField inputPort = new JTextField();
        JTextField inputIP = new JTextField();
        JButton connection = new JButton("接続");
        
        NetworkClient client = new NetworkClient();
        MainFrame mainFrame = new MainFrame(client);
        connection.addActionListener(e -> {
            if(inputName.getText().equals("")) return;
            
            initFrame.setVisible(false);
            mainFrame.init(inputName.getText());
            
            client.connect(inputIP.getText(), Integer.parseInt(inputPort.getText()));
            Thread clientReceive = new Thread(client);
            clientReceive.start();
            
            mainFrame.setVisible(true);
        });
        
        this.add(labeledJComponent(inputPort, "ポート"));
        this.add(labeledJComponent(inputIP, "IPアドレス"));
        this.add(connection);
    }
    
    private JPanel labeledJComponent(JComponent j, String str) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 0));
        JLabel label = new JLabel(str);
        panel.add(j, BorderLayout.CENTER);
        panel.add(label, BorderLayout.WEST);
        return panel;
    }
}
