/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author local-nattou
 */
public class Palette extends JPanel {
    private final ArrayList<PenButton> penButtons;
    private Pen pen = new CirclePen();
    private Color color = Color.BLACK;
    private int radius = 5;
    private final Drawer drawer;
  
    public Palette(Drawer d) {
        penButtons = new ArrayList<>();
        
        //消しゴム
        PenButton eraser = createPenButton(Color.WHITE);
        eraser.setText("けしごむ");
        eraser.setFocusPainted(false);
        
        //ペン色作成
        PenButton black = createPenButton(Color.BLACK);
        createPenButton(Color.RED);
        createPenButton(Color.BLUE);
        createPenButton(Color.GREEN);
        createPenButton(Color.YELLOW);
        
        //初期色は黒
        black.isPressed(true);
        
        GridLayout layout = new GridLayout(2, penButtons.size() + 2);
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
    
    private PenButton createPenButton(Color c) {
        PenButton p = new PenButton(c);
        
        penButtons.add(p);
        p.addActionListener(e -> {
            color = p.getColor();
            updatePen();
            System.out.println(p.getColor());
            for(PenButton b : penButtons) {
                b.isPressed(false);
            }
            p.isPressed(true);
        });
        this.add(p);
        
        return p;
    }
    
    private int createPenButtons() {
        ArrayList<PenButton> list = new ArrayList<>();
        
        //消しゴム
        PenButton eraser = new PenButton(Color.WHITE);
        eraser.setText("けしごむ");
        eraser.setFocusPainted(false);
        
        //ペン色の生成
        PenButton black = new PenButton(Color.BLACK);
        PenButton red = new PenButton(Color.RED);
        PenButton blue = new PenButton(Color.BLUE);
        PenButton yellow = new PenButton(Color.YELLOW);
        PenButton green = new PenButton(Color.GREEN);
        
        //初期値は黒
        black.isPressed(true);
        
        list.add(eraser);
        list.add(black);
        list.add(red);
        list.add(blue);
        list.add(yellow);
        list.add(green);
        
        for(PenButton p : list) {
            p.addActionListener(e -> {
                color = p.getColor();
                updatePen();
                System.out.println(p.getColor());
                for(PenButton b : list) {
                    b.isPressed(false);
                }
                p.isPressed(true);
            });
            this.add(p);
        }
        
        return list.size();
    }
    
    private void createPenTypeSelecter() {
        Vector<Pen> penTypes = new Vector<>();
        
        penTypes.add(new CirclePen());
        penTypes.add(new RectanglePen());
        
        JComboBox cb = new JComboBox(penTypes);
        cb.addItemListener(e -> {
            pen = (Pen)e.getItem();
            updatePen();
        });
        cb.setSelectedIndex(0);
        
        this.add(cb);
    }
    
    private void createPenRadiusSelecter() {
        Vector<Integer> penSizes = new Vector<>();
        
        penSizes.add(1);
        penSizes.add(5);
        penSizes.add(10);
        penSizes.add(15);
        penSizes.add(20);
        
        JComboBox cb = new JComboBox(penSizes);
        cb.addItemListener(e -> {
            radius = (int)e.getItem();
            updatePen();
        });
        cb.setSelectedIndex(1);
        
        this.add(cb);
    }
}
