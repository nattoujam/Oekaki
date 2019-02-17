/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.Color;

/**
 *
 * @author local-nattou
 */
public class ServerUserData extends UserData {
    
    private static final String SERVER_NAME = "server";
    private static final Color SERVER_COLOR = new Color(200, 0, 255);
    
    public ServerUserData() {
        super(SERVER_NAME, SERVER_COLOR);
    }
}
