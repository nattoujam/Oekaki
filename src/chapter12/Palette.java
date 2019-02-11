/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author local-nattou
 */
public class Palette extends JPanel {
    private Pen pen = new CirclePen();
    private Color color = Color.BLACK;
    private int radius = 5;
    private final Drawer drawer;
  
    public Palette(Drawer d) {
        GridLayout layout = new GridLayout(2, createPenButtons() + 2);
        System.out.println(layout.getColumns());
        System.out.println(layout.getRows());
        layout.setVgap(5);
        layout.setHgap(5);
        this.setLayout(layout);
        
        this.drawer = d;
        createPenTypeSelecter();
        createPenRadiusSelecter();
    }
    
    public Pen getSelectedPen() {
        return this.pen;
    }
    
    private void updatePen() {
        pen.setColor(color);
        pen.setRadius(radius);
        drawer.setPen(pen);
    }
    
    private int createPenButtons() {
        ArrayList<PenButton> list = new ArrayList<PenButton>();
        
        //消しゴム
        PenButton eraser = new PenButton(Color.WHITE);
        
        //ペン色の生成
        PenButton black = new PenButton(Color.BLACK);
        PenButton red = new PenButton(Color.RED);
        PenButton blue = new PenButton(Color.BLUE);
        PenButton yellow = new PenButton(Color.YELLOW);
        
        list.add(eraser);
        list.add(black);
        list.add(red);
        list.add(blue);
        list.add(yellow);
        
        for(PenButton p : list) {
            p.addActionListener(e -> {
                color = p.getColor();
                updatePen();
                System.out.println(p.getColor());
            });
            this.add(p);
        }
        
        return list.size();
    }
    
    private void createPenTypeSelecter() {
        Vector<Pen> pens = new Vector<>();
        
        pens.add(new CirclePen());
        pens.add(new RectanglePen());
        
        JComboBox cb = new JComboBox(pens);
        cb.addItemListener(e -> {
            pen = (Pen)e.getItem();
            updatePen();
        });
        cb.setSelectedIndex(0);
        
        this.add(cb);
    }
    
    private void createPenRadiusSelecter() {
        Vector<Integer> radiuses = new Vector<>();
        
        radiuses.add(5);
        radiuses.add(10);
        radiuses.add(20);
        radiuses.add(30);
        
        JComboBox cb = new JComboBox(radiuses);
        cb.addItemListener(e -> {
            radius = (int)e.getItem();
            updatePen();
        });
        
        this.add(cb);
    }
}
