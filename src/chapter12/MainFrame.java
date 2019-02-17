/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import javax.swing.JFrame;

/**
 *
 * @author local-nattou
 */
public class MainFrame extends JFrame {
    
    private final NetworkClient client;
    private final AnswerPanel answerPanel;
    private final PlayerDataPanel pdPanel;
    
    public MainFrame(NetworkClient client) {
        this.client = client;
        
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(50, 50, 1000, 700);
        this.setLayout(null);
        
        Drawer drawer = new Drawer();
        this.answerPanel = new AnswerPanel(client);
        answerPanel.setBounds(5, 110, 300, 500);
        Palette palette = new Palette(drawer);
        palette.setBounds(310, 560, 500, 50);
        DrawPanel drawPanel = new DrawPanel(drawer);
        drawPanel.setBounds(310, 5, 500, 550);
        drawPanel.changeMauseInputReception(true);
        this.pdPanel = new PlayerDataPanel();
        pdPanel.setBounds(5, 5, 300, 100);

        this.add(answerPanel);
        this.add(drawPanel);
        this.add(palette);
        this.add(pdPanel);
    }
    
    public void init(String name) {
        client.getPacketSelector().addHandler(UserDataPacket.class, p -> {
            pdPanel.addPlayer(p.getUserData());
        });
        client.getPacketSelector().addHandler(ColorPacket.class, p -> {
            UserData you = new UserData(name, p.getColor());
            answerPanel.setUserData(you);
            client.aggregation(new UserDataPacket(you));
        });
        client.getPacketSelector().addHandler(GameStartPacket.class, p -> {
            if(name.equals(p.getDrawer())) {
                pdPanel.setTheme(p.getTheme());
            }
            else {
                pdPanel.setTheme("お題を予想しよう！！");
            }
        });
    }
}
