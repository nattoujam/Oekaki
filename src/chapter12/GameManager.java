/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author local-nattou
 */
public class GameManager {
    
    private final List<String> players;
    private String theme = "";
    
    public GameManager() {
        players = new ArrayList<>();
    }
    
    public String getNextDrawer() {
        Random rand = new Random();
        String drawer = players.get(rand.nextInt(players.size()));
        return drawer;
    }
    
    public String getNextTheme() {
        theme = "";
        return theme;
    }
    
    public void addPlayer(String name) {
        players.add(name);
    }
    
    public boolean isCollectAnswer(String answer) {
        return theme.equals(answer);
    } 
}
