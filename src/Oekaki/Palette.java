/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oekaki;

import Oekaki.Pens.Pen;
import java.util.function.Consumer;

/**
 *
 * @author local-nattou
 */
public interface Palette {
    public <T> void addPenUpdater(Consumer<Pen> consumer); 
}
