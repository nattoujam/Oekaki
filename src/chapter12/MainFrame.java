/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import chapter12.Network.NetworkClient;
import chapter12.Packets.*;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author local-nattou
 */
public class MainFrame extends JFrame {
    
    private final SoundEffect se;
    private final NetworkClient client;
    private final JTextField themeField;
    private final AnswerPanel answerPanel;
    private final PlayerDataPanel pdPanel;
    private final DrawPanel drawPanel;
    private final PalettePanel palettePanel;
    private final JToggleButton startButton;
    private final JTimer jTimer;
    
    public MainFrame(NetworkClient client, SoundEffect se) {
        this.client = client;
        this.se = se;
        this.setTitle("おえか木");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(50, 50, 1025, 650);
        this.setLayout(null);
        this.setResizable(false);
        
        themeField = new JTextField("???");
        themeField.setEditable(false);
        themeField.setBorder(new EtchedBorder());
        themeField.setHorizontalAlignment(JTextField.CENTER);
        this.jTimer = new JTimer();
        JPanel gameInfoPanel = new JPanel();
        gameInfoPanel.setLayout(new BorderLayout(5, 5));
        gameInfoPanel.add(Tools.LabeledJComponent("お題", themeField), BorderLayout.CENTER);
        gameInfoPanel.add(Tools.LabeledJComponent("残り", jTimer), BorderLayout.EAST);
        gameInfoPanel.setBounds(5, 5, 300, 20);
        
        this.answerPanel = new AnswerPanel(client, se);
        answerPanel.setBounds(5, 140, 300, 460);
        
        this.drawPanel = new DrawPanel(client, se);
        drawPanel.setBounds(310, 5, 690, 540);
        drawPanel.setInputReception(true);
        
        this.palettePanel = new PalettePanel(se);
        palettePanel.setBounds(310, 550, 500, 50);
        palettePanel.addPenUpdater(drawPanel::setPen);
        
        this.pdPanel = new PlayerDataPanel();
        pdPanel.setBounds(5, 30, 300, 105);
        
        this.startButton = new JToggleButton("ゲーム開始申請");
        startButton.addActionListener(e -> {
            se.acceptSE();
            String text;
            if(startButton.isSelected()) text = "ゲーム開始待ち";
            else text = "ゲーム開始申請";
            startButton.setText(text);
            client.aggregation(new GameReadyPacket(client.getMyData(), startButton.isSelected()));
        });
        startButton.setBounds(815, 550, 185, 50);
        
        this.add(gameInfoPanel);
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
            jTimer.start();
            if(data.getName().equals(p.getDrawer())) {
                themeField.setText(p.getTheme());
                drawPanel.setInputReception(true);
                
                //JOptionPane.showMessageDialog(null, "あなたは描き手です。", client.getMyData().getName() + "さんへ", JOptionPane.PLAIN_MESSAGE);
            }
            else {
                themeField.setText("お題を予想しよう！！");
                drawPanel.setInputReception(false);
                //JOptionPane.showMessageDialog(null, "あなたは解答者です。", client.getMyData().getName() + "さんへ", JOptionPane.PLAIN_MESSAGE);
            }
        });
        client.getPacketSelector().addHandler(ResultPacket.class, p -> {
            //得点の付与
            pdPanel.setScore(p.getRespondent(), p.getScore());
            pdPanel.setScore(p.getDrawer(), p.getScore());
        });
        client.getPacketSelector().addHandler(GameFinishPacket.class, p -> {
            //ゲーム終了
            se.fanfareSE();
            drawPanel.setInputReception(true);
            jTimer.stop();
            startButton.setEnabled(true);
            startButton.setSelected(false);
            JOptionPane.showMessageDialog(null, "優勝は、" + pdPanel.getWinner() + "です！！！", "業務連絡", JOptionPane.PLAIN_MESSAGE);
            themeField.setText("");
        });
    }

    /**
     * @return the pdPanel
     */
    public PlayerDataPanel getPlayerDataPanel() {
        return pdPanel;
    }
}
