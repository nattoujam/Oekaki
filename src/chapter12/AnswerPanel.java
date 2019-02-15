/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private final JTextPane logArea;
    private JTextField answerArea;
    private final UserData myData;
    private final DefaultStyledDocument doc;

    public AnswerPanel(UserData d) {
        this.setLayout(new BorderLayout(0, 5));

        this.myData = d;
        this.logArea = new JTextPane();
        logArea.setEditable(false);
        StyleContext sc = new StyleContext();
        doc = new DefaultStyledDocument(sc);
        logArea.setDocument(doc);

        JScrollPane scrollPane = new JScrollPane(logArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        logAppend(new Date(), new UserData("GM", new Color(200, 0, 255)), "Welcome to \"おえか木\" !!");

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
    public void logAppend(Object time, UserData d, String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("[kk:mm.ss]");

        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr, d.getColor());

        String log = sdf.format(time) + "<" + d.getName() + ">" + str + "\r\n";

        try {
            doc.insertString(doc.getLength(), log, attr);
        }
        catch (BadLocationException ex) {
            Logger.getLogger(AnswerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (answerArea.getText().equals("")) {
                return;
            }
            logAppend(e.getWhen(), myData, answerArea.getText());
            answerArea.setText("");
        }
    }
}