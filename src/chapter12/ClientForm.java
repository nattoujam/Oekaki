/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import chapter12.Network.NetworkClient;
import chapter12.Packets.ColorPacket;
import chapter12.Packets.UserDataPacket;
import java.awt.BorderLayout;
import java.awt.GridLayout;
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
public class ClientForm extends JPanel {
    public ClientForm(JFrame initFrame, JTextField inputName) {
        this.setBorder(new TitledBorder(new EtchedBorder(), "クライアント"));
        GridLayout layout = new GridLayout(3, 1);
        this.setLayout(layout);
        layout.setVgap(10);
        
        JTextField inputPort = new JTextField("100");
        JTextField inputIP = new JTextField("localhost");
        JButton connection = new JButton("接続");
        
        NetworkClient client = new NetworkClient();
        MainFrame mainFrame = new MainFrame(client);
        connection.addActionListener(e -> {
            if(inputName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "名前を入力してください。", "業務連絡", JOptionPane.OK_OPTION);
                return;
            }
            
            initFrame.setVisible(false);
            client.setName(inputName.getText());
            
            client.getPacketSelector().addHandler(ColorPacket.class, p -> {
                UserData you = new UserData(inputName.getText(), p.getColor());
                client.setMyData(you);
                mainFrame.init(you);
                client.aggregation(new UserDataPacket(you));
            });
            
            client.connect(inputIP.getText(), Integer.parseInt(inputPort.getText()));
            Thread clientReceive = new Thread(client);
            clientReceive.start();
            
            mainFrame.setVisible(true);
        });
        
        this.add(Tools.LabeledJComponent("ポート", inputPort));
        this.add(Tools.LabeledJComponent("IPアドレス", inputIP));
        this.add(connection);
    }
}
