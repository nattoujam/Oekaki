/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oekaki;

import Oekaki.Network.NetworkClient;
import Oekaki.Packets.LogPacket;
import Oekaki.Packets.Packet;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.text.*;

/**
 *
 * @author local-nattou
 */
public class AnswerPanel extends JPanel {

    private final SoundEffect se;
    private final JTextPane logArea;
    private JTextField answerArea;
    private final DefaultStyledDocument doc;
    private final NetworkClient client;

    public AnswerPanel(NetworkClient client, SoundEffect se) {
        this.client = client;
        this.se = se;
        client.getPacketSelector().addHandler(LogPacket.class, p -> logAppend(p.getTime(), p.getUserData(), p.getLog()));
        this.setLayout(new BorderLayout(0, 5));

        this.logArea = new JTextPane();
        logArea.setEditable(false);
        //logArea.setCaretPosition(logArea.getText().length());
        StyleContext sc = new StyleContext();
        doc = new DefaultStyledDocument(sc);
        logArea.setDocument(doc);

        JScrollPane scrollPane = new JScrollPane(logArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        logAppend(System.currentTimeMillis(), new ServerUserData(), "ようこそ　おえか木　へ！\r\n解答はひらがなで行ってください。");

        JPanel inputArea = createInputTextField();

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(inputArea, BorderLayout.SOUTH);
    }

    private JPanel createInputTextField() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout(5, 0));

        answerArea = new JTextField();

        JButton sendButton = new JButton("送る");
        sendButton.addActionListener(new Action());
        answerArea.addActionListener(new Action());

        p.add(answerArea, BorderLayout.CENTER);
        p.add(sendButton, BorderLayout.EAST);

        return p;
    }

    //ログテキスト追加
    private void logAppend(long time, UserData d, String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("[kk:mm.ss]");

        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr, d.getColor());

        String log = sdf.format(time) + "<" + d.getDispName() + ">" + str + "\r\n";

        try {
            if(!d.getName().equals(ServerUserData.SERVER_NAME)) se.receivetSE();
            doc.insertString(doc.getLength(), log, attr);
            logArea.setCaretPosition(doc.getLength());
        }
        catch (BadLocationException ex) {
            Logger.getLogger(AnswerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String log = answerArea.getText();
            if (log.equals("")) {
                return;
            }
            logAppend(e.getWhen(), client.getMyData(), log);
            answerArea.setText("");
            Packet packet = new LogPacket(client.getMyData(), e.getWhen(), log);
            client.aggregation(packet);
        }
    }
}
