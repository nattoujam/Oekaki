/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author local-nattou
 */
public class ServerForm extends JPanel {
    
    public ServerForm(JFrame initFrame, JTextField inputName) {
        this.setBorder(new TitledBorder(new EtchedBorder(), "サーバー"));
        GridLayout layout = new GridLayout(3, 1);
        this.setLayout(layout);
        layout.setVgap(50);
        
        JTextField inputPort = new JTextField();
        Integer[] select = {1, 2, 3};
        JComboBox numOfPlayer = new JComboBox(select);
        JButton connection = new JButton("開放");
        
        NetworkClient client = new NetworkClient();
        MainFrame mainFrame = new MainFrame(client);
        connection.addActionListener(e -> {
            //SoundEffect se = new SoundEffect();
            //se.StartTestSound();
            
            if(inputName.getText().equals("")) return;
            
            initFrame.setVisible(false);
            mainFrame.init(inputName.getText());
            
            System.out.println((int)numOfPlayer.getSelectedItem());
            
            Thread establishConnection = new Thread(new NetworkServer(Integer.parseInt(inputPort.getText()), (int)numOfPlayer.getSelectedItem()));
            establishConnection.start();
            
            System.out.println(Integer.parseInt(inputPort.getText()));

            client.connect("localhost", Integer.parseInt(inputPort.getText()));
            Thread clientRecieve = new Thread(client);
            clientRecieve.start();
            
            String IP = "";
            try {
                InetAddress testIP = InetAddress.getLocalHost();
                IP = testIP.getHostAddress();
            }
            catch (UnknownHostException ex) {
                Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, IP, "Your IP address", JOptionPane.PLAIN_MESSAGE);
            
            mainFrame.setVisible(true);
        });
        
        this.add(labeledJComponent(inputPort, "ポート"));
        this.add(labeledJComponent(numOfPlayer, "人数"));
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
