/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import chapter12.Pens.Pen;
import java.util.function.Consumer;

/**
 *
 * @author local-nattou
 */
public interface Palette {
    public <T> void addPenUpdater(Consumer<Pen> consumer); 
}
