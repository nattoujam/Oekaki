/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import chapter12.Network.NetworkServer;
import chapter12.Network.NetworkClient;
import chapter12.Packets.ClosedPacket;
import chapter12.Packets.ColorPacket;
import chapter12.Packets.SEPacket;
import chapter12.Packets.UserDataPacket;
import java.awt.Color;
import java.awt.GridLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
    
    public ServerForm(JFrame initFrame, JTextField inputName, SoundEffect se) {
        //System.setProperty("jdk.tls.client.protocols", "TLSv1");
        
        this.setBorder(new TitledBorder(new EtchedBorder(), "サーバー"));
        GridLayout layout = new GridLayout(3, 1);
        this.setLayout(layout);
        layout.setVgap(10);
        
        JTextField inputPort = new JTextField("100");
        Integer[] select = {1, 2, 3, 4};
        JComboBox numOfPlayer = new JComboBox(select);
        JButton connection = new JButton("開放");
        
        
        NetworkClient client = new NetworkClient();
        MainFrame mainFrame = new MainFrame(client, se);
        connection.addActionListener(e -> {
            se.acceptSE();
            
            if(inputName.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "名前を入力してください。", "業務連絡", JOptionPane.OK_OPTION);
                return;
            }
            client.setName(inputName.getText());
            GameManager gm = new GameManager();
            
            initFrame.setVisible(false);
            client.getPacketSelector().addHandler(ColorPacket.class, p -> {
                UserData you = new UserData(inputName.getText(), p.getColor());
                client.setMyData(you);
                mainFrame.init(you);
                client.aggregation(new UserDataPacket(you));
            });
            client.getPacketSelector().addHandler(SEPacket.class, se::playSE);
            
            NetworkServer server = new NetworkServer(Integer.parseInt(inputPort.getText()), (int)numOfPlayer.getSelectedItem(), gm);
            Thread establishConnection = new Thread(server);
            establishConnection.start();

            client.connect("localhost", Integer.parseInt(inputPort.getText()));
            Thread clientRecieve = new Thread(client);
            clientRecieve.start();
            
            //JOptionPane.showMessageDialog(null, getIP(), "Your IP address", JOptionPane.PLAIN_MESSAGE);
            
            WindowClosing wc = new WindowClosing(() -> {
                client.aggregation(new ClosedPacket(new ServerUserData()));
                //client.close();
                //server.close();
            });
            mainFrame.addWindowListener(wc);
            
            mainFrame.setVisible(true);
            se.stopBGM();
            se.mainFrameBGM();
        });
        
        this.add(Tools.LabeledJComponent("ポート", inputPort));
        this.add(Tools.LabeledJComponent("人数", numOfPlayer));
        this.add(connection);
    }
    
    private String getIP() {
        String IP = "";
        try {
            InetAddress testIP = InetAddress.getLocalHost();
            IP = testIP.getHostAddress();
        }
        catch (UnknownHostException ex) {
            Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IP;
    }
}
