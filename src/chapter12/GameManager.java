/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import chapter12.Network.NetworkClient;
import chapter12.Packets.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private static final int TIME_LIMIT = 120;
    private static final int MAX_SCORE = 300;
    
    public GameManager() {
        players = new ArrayList<>();
    }
    
    public String getNextDrawer() {
        Random rand = new Random();
        int index = rand.nextInt(tempPlayers.size());
        drawer = tempPlayers.get(index);
        tempPlayers.remove(index);
        
        return getDrawer();
    }
    
    public String getNextTheme() {
        theme = "あなご";
        return getTheme();
    }
    
    public void addPlayer(String name) {
        players.add(name);
    }
    
    public boolean isCollectAnswer(String answer) {
        return getTheme().equals(answer);
    } 
    
    public void startTimer() {
        init();
        //タイマースタート
        this.startTime = System.currentTimeMillis();
    }
    
    public int getScore(long answeredTime) {
        //いい感じに計算(基礎点)
        int time = (int) (answeredTime - startTime);
        return (time < TIME_LIMIT) ? MAX_SCORE - (MAX_SCORE / TIME_LIMIT) * time : 0;
    } 
    
    public boolean isFinish() {
        return (tempPlayers.size() == 0);
    }
    
    public boolean readyGame(boolean isReady) {
        readyCount += (isReady) ? 1 : -1;
        System.out.println("#####" + readyCount + "#####");
        return (readyCount == players.size());
    }
    
    private void init() {
        tempPlayers = new ArrayList<>(players);
        readyCount = 0;
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
}
