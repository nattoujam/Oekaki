/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

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
        this.drawer = d;
        
        this.setLayout(new BorderLayout(5, 0));
        this.add(createColorPalette(), BorderLayout.CENTER);
        this.add(createPenPanel(), BorderLayout.EAST);
    }
    
    public Pen getSelectedPen() {
        return this.pen;
    }
    
    private void updatePen() {
        pen.setColor(color);
        pen.setRadius(radius);
        drawer.setPen(pen);
    }
    
    private JPanel createColorPalette() {
        JPanel panel = new JPanel();
        ArrayList<PenButton> list = new ArrayList<>();
        
        //消しゴム
        PenButton eraser = new PenButton(Color.WHITE);
        eraser.setText("消");
        eraser.setFocusPainted(false);
        
        //ペン色の生成
        PenButton black = new PenButton(Color.BLACK);
        PenButton red = new PenButton(Color.RED);
        PenButton blue = new PenButton(Color.BLUE);
        PenButton yellow = new PenButton(Color.YELLOW);
        PenButton green = new PenButton(Color.GREEN);
        PenButton temp1 = new PenButton(Color.GREEN);
        PenButton temp2 = new PenButton(Color.GREEN);
        PenButton temp3 = new PenButton(Color.GREEN);
        PenButton temp4 = new PenButton(Color.GREEN);
        
        //初期値は黒
        black.isPressed(true);
        
        list.add(eraser);
        list.add(black);
        list.add(red);
        list.add(blue);
        list.add(yellow);
        list.add(green);
        list.add(temp1);
        list.add(temp2);
        list.add(temp3);
        list.add(temp4);
        
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
            panel.add(p);
        }
        
        GridLayout layout = new GridLayout(2, list.size() + 2);
        System.out.println(layout.getColumns());
        System.out.println(layout.getRows());
        layout.setVgap(5);
        layout.setHgap(5);
        panel.setLayout(layout);
        
        return panel;
    }
    
    private JComboBox createPenTypeSelecter(JComponent place) {
        Vector<Pen> penTypes = new Vector<>();
        
        penTypes.add(new CirclePen());
        penTypes.add(new RectanglePen());
        
        JComboBox cb = new JComboBox(penTypes);
        cb.addItemListener(e -> {
            pen = (Pen)e.getItem();
            updatePen();
        });
        cb.setSelectedIndex(0);
        
        place.add(cb);
        
        return cb;
    }
    
    private JComboBox createPenRadiusSelecter(JComponent place) {
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
        
        place.add(cb);
        
        return cb;
    }
    
    private JPanel createPenPanel() {
        JPanel panel = new JPanel();
        
        panel.setLayout(new BorderLayout());
        panel.add(createPenTypeSelecter(panel), BorderLayout.NORTH);
        panel.add(createPenRadiusSelecter(panel), BorderLayout.SOUTH);
        
        return panel;
    }
}
