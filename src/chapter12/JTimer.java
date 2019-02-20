/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import chapter12.Packets.Packet;
import chapter12.Packets.ResultPacket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author local-nattou
 */
public class JTimer extends JLabel {
    private final Timer timer;
    private final Runnable runnable;
    private long startTime;
    
    public <T> JTimer(Runnable runnable) {
        this.setOpaque(true);
        this.runnable = runnable;
        this.setText(String.valueOf(GameManager.TIME_LIMIT));
        timer = new Timer(1000, new TimerTick());
    }
    
    public void start() {
        this.setText(String.valueOf(GameManager.TIME_LIMIT));
        timer.start();
        startTime = System.currentTimeMillis();
    }
    
    public void stop() {
        setText("0");
        timer.stop();
    }
    
    private boolean putClockForward() {
        long current = System.currentTimeMillis();
        int time = (int) (GameManager.TIME_LIMIT - (current - startTime) / 1000);
        this.setText(String.valueOf(time));
        
        return (time < 0);
    }
    
    private class TimerTick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(putClockForward()) {
                runnable.run();
                timer.stop();
            }
        }
    }
}
