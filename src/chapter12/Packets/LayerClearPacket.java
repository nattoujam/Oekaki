/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12.Packets;

import chapter12.UserData;

/**
 *
 * @author local-nattou
 */
public class LayerClearPacket extends Packet {
    
    public LayerClearPacket(UserData sender) {
        super(sender);
    }
}
