/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author local-nattou
 */
public class UserData implements Serializable {

    private String name = "defaultName";
    private Color color = Color.BLACK;
    
    public UserData(String n, Color c) {
        this.name = n;
        this.color = c;
    }

    public String getName() {
        return this.name;
    }

    public Color getColor() {
        return this.color;
    }
}
