/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12.Packets;

import chapter12.UserData;
import java.io.Serializable;

/**
 *
 * @author local-nattou
 */
public abstract class Packet implements Serializable {
    private final UserData sender;
    
    protected Packet(UserData sender) {
        this.sender = sender;
    }

    public UserData getUserData() {
        return sender;
    }
}
