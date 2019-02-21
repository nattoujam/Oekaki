/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import chapter12.Network.NetworkClient;
import chapter12.Packets.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author local-nattou
 */
public class GameManager {
    
    private final List<String> players;
    private List<String> tempPlayers;
    private String theme = "";
    private String drawer;
    private long startTime;
    private int readyCount = 0;
    private Timer timer;
    private Runnable runnable;
    public static final int TIME_LIMIT = 120;
    private static final int MAX_SCORE = 300;
    
    public GameManager() {
        players = new ArrayList<>();
        timer = new Timer(1000, new TimerTick());
    }
    
    public String getNextDrawer() {
        startTime = System.currentTimeMillis();
        Random rand = new Random();
        int index = rand.nextInt(tempPlayers.size());
        drawer = tempPlayers.get(index);
        tempPlayers.remove(index);
        
        return getDrawer();
    }
    
    public String getNextTheme() {
        Themes[] themes = Themes.values();
        Random rand = new Random();
        theme = themes[rand.nextInt(themes.length)].toString();
        return getTheme();
    }
    
    public void addPlayer(String name) {
        players.add(name);
    }
    
    public void init() {
        tempPlayers = new ArrayList<>(players);
        tempPlayers.addAll(players);
        readyCount = 0;
    }
    
    public boolean isCollectAnswer(String answer) {
        return getTheme().equals(answer);
    } 
    
    public void startTimer() {
        //タイマースタート
        this.startTime = System.currentTimeMillis();
        timer.start();
    }
    
    public int getScore(long answeredTime) {
        //いい感じに計算(基礎点)
        int time = (int) (answeredTime - startTime) / 1000;
        return (time < TIME_LIMIT) ? MAX_SCORE - (MAX_SCORE / TIME_LIMIT) * time : 0;
    } 
    
    public boolean isTimeLimit() {
        return (System.currentTimeMillis() - startTime) / 1000 >= TIME_LIMIT;
    }
    
    public boolean isFinish() {
        if(tempPlayers.isEmpty()) {
            theme = "";
            timer.stop();
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean readyGame(boolean isReady) {
        readyCount += (isReady) ? 1 : -1;
        System.out.println("#####" + readyCount + "#####");
        return (readyCount == players.size());
    }
    
    public void addTimeLimitMethid(Runnable runnable) {
        this.runnable = runnable;
    }
    
    private class TimerTick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("GM:" + (System.currentTimeMillis() - startTime) / 1000);
            if(isTimeLimit()) {
                timer.stop();
                runnable.run();
            }
        }
    }

    /**
     * @return the drawer
     */
    public String getDrawer() {
        return drawer;
    }

    /**
     * @return the theme
     */
    public String getTheme() {
        return theme;
    }
    
    enum Themes {
        あなご,
        りんご,
        しらす,
        たまねぎ,
        こんにゃく,
        ばなな,
        うなぎ,
        ほうれんそう,
        きゃべつ,
        はくさい,
        ぴーまん,
        にんじん,
        じゃがいも,
        しらたき,
        かれー,
        しちゅー,
        ぱすた,
        ゆーふぉー,
        とらんぷ,
        なっとう,
        じゃむ,
        さくら,
        うめ,
        なす,
        とまと,
        ばたー,
        きゅうり,
        きーぼーど,
        まうす,
        まいく,
        てれび,
        かめら,
        ぽめらにあん,
        まんちかん,
        かいちゅうでんとう,
        はいぽーしょん
    }

    public GameManager(List<String> players) {
        this.players = players;
    }
}
