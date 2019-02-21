/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oekaki;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author local-nattou
 */
public class UserData implements Serializable {

    protected final String realName;
    private final String dispName;
    protected final Color color;
    
    public UserData(String n, Color c) {
        String[] splits = n.split("-", -1);
        String temp = "";
        for(int i = 0; i < splits.length - 1; i++) {
            temp += splits[i];
        }
                
        this.dispName = temp;
        this.realName = n;
        this.color = c;
    }
    
    public String getDispName() {
        return this.dispName;
    }

    public String getName() {
        return this.realName;
    }

    public Color getColor() {
        return this.color;
    }
}
