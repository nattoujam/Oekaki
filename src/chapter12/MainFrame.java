/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import chapter12.Network.NetworkClient;
import chapter12.Packets.GameFinishPacket;
import chapter12.Packets.GameReadyPacket;
import chapter12.Packets.UserDataPacket;
import chapter12.Packets.GameStartPacket;
import chapter12.Packets.ResultPacket;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

/**
 *
 * @author local-nattou
 */
public class MainFrame extends JFrame {
    
    private final NetworkClient client;
    private final AnswerPanel answerPanel;
    private final PlayerDataPanel pdPanel;
    private final DrawPanel drawPanel;
    private final PalettePanel palettePanel;
    private final JToggleButton startButton;
    
    public MainFrame(NetworkClient client) {
        this.client = client;
        
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(50, 50, 1000, 700);
        this.setLayout(null);
        
        this.answerPanel = new AnswerPanel(client);
        answerPanel.setBounds(5, 110, 300, 500);
        this.drawPanel = new DrawPanel(client);
        drawPanel.setBounds(310, 5, 500, 550);
        this.palettePanel = new PalettePanel();
        palettePanel.setBounds(310, 560, 500, 50);
        palettePanel.addPenUpdater(drawPanel::setPen);
        drawPanel.setInputReception(true);
        this.pdPanel = new PlayerDataPanel();
        pdPanel.setBounds(5, 5, 300, 100);
        this.startButton = new JToggleButton("ゲーム開始申請");
        startButton.addActionListener(e -> {
            String text;
            if(startButton.isSelected()) text = "ゲーム開始待ち";
            else text = "ゲーム開始申請";
            startButton.setText(text);
            client.aggregation(new GameReadyPacket(client.getMyData(), startButton.isSelected()));
        });
        startButton.setBounds(815, 5, 150, 500);

        this.add(answerPanel);
        this.add(drawPanel);
        this.add(palettePanel);
        this.add(pdPanel);
        this.add(startButton);
    }
    
    public void init(UserData data) {
        pdPanel.addPlayer(data);
        client.getPacketSelector().addHandler(UserDataPacket.class, p -> {
            pdPanel.addPlayer(p.getUserData());
        });
        client.getPacketSelector().addHandler(GameStartPacket.class, p -> {
            startButton.setEnabled(false);
            //タイマースタート
            if(data.getName().equals(p.getDrawer())) {
                pdPanel.setTheme(p.getTheme());
                drawPanel.setInputReception(true);
                JOptionPane.showMessageDialog(null, "あなたは描き手です。", client.getMyData().getName() + "さんへ", JOptionPane.PLAIN_MESSAGE);
            }
            else {
                pdPanel.setTheme("お題を予想しよう！！");
                drawPanel.setInputReception(false);
                JOptionPane.showMessageDialog(null, "あなたは解答者です。", client.getMyData().getName() + "さんへ", JOptionPane.PLAIN_MESSAGE);
            }
        });
        client.getPacketSelector().addHandler(ResultPacket.class, p -> {
            //得点の付与
        });
        client.getPacketSelector().addHandler(GameFinishPacket.class, p -> {
            //ゲーム終了
            startButton.setEnabled(true);
            startButton.setSelected(false);
            JOptionPane.showMessageDialog(null, "優勝は、です！！！", "業務連絡", JOptionPane.PLAIN_MESSAGE);
        });
    }

    /**
     * @return the pdPanel
     */
    public PlayerDataPanel getPlayerDataPanel() {
        return pdPanel;
    }
}
