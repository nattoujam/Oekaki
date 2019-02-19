/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import chapter12.Network.NetworkClient;
import chapter12.Packets.LayerClearPacket;
import chapter12.Pens.Pen;
import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author local-nattou
 */
public class DrawPanel extends JPanel {

    private final DrawComponent dCom;
    private final MouseInput mi;
    private final JButton reset;

    public DrawPanel(NetworkClient client) {
        this.setLayout(new BorderLayout(0, 5));

        this.dCom = new DrawComponent();
        this.add(dCom, BorderLayout.CENTER);

        this.mi = new MouseInput(client);
        mi.setDrawComponent(dCom);
        this.addMouseMotionListener(mi);
        this.addMouseListener(mi);

        this.reset = new JButton("レイヤークリア");
        reset.setFocusPainted(false);
        reset.addActionListener(e -> {
            dCom.clear();
            client.aggregation(new LayerClearPacket(client.getMyData()));
        });
        this.add(reset, BorderLayout.SOUTH);
        
        client.getPacketSelector().addHandler(LayerClearPacket.class, p -> dCom.clear());
    }
    
    public void setPen(Pen pen) {
        mi.setPen(pen);
    }

    //入力受付の切り替え
    public void setInputReception(boolean b) {
        if (b) {
            mi.setCanReception(true);
            reset.setEnabled(true);
        }
        else {
            mi.setCanReception(false);
            reset.setEnabled(false);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        //System.out.println(dCom.getGraphics2D());
        if (dCom.getGraphics2D() == null) {
            dCom.clear();
        }
    }
}
