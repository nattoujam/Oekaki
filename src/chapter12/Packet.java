/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.io.Serializable;

/**
 *
 * @author local-nattou
 */
public abstract class Packet implements Serializable {
    private final UserData userData;
    
    protected Packet(UserData u) {
        this.userData = u;
    }

    public UserData getUserData() {
        return userData;
    }
}
