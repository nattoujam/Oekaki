/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import chapter12.Pens.RectanglePen;
import chapter12.Pens.Pen;
import chapter12.Pens.CirclePen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author local-nattou
 */
public class PalettePanel extends JPanel implements Palette {

    private final SoundEffect se;
    private Pen pen = new CirclePen();
    private Color color = Color.BLACK;
    private int radius = 5;
    private Consumer<Pen> penUppdater;

    public PalettePanel(SoundEffect se) {
        this.se = se;
        this.setLayout(new BorderLayout(5, 0));
        this.add(createColorPalette(), BorderLayout.CENTER);
        this.add(createPenPanel(), BorderLayout.EAST);
    }

    @Override
    public <T> void addPenUpdater(Consumer<Pen> consumer) {
        this.penUppdater = consumer;
        updatePen();
    }

    public Pen getSelectedPen() {
        return this.pen;
    }
    
    private void updatePen() {
        pen.setColor(color);
        pen.setRadius(radius);
        //if挿入:isDrawer
        penUppdater.accept(pen);
    }

    private JPanel createColorPalette() {
        JPanel panel = new JPanel();
        ArrayList<PenButton> list = new ArrayList<>();

        //ペン色の生成
        Function<Color, PenButton> makePenButton = c -> {
            PenButton b = new PenButton(c);
            list.add(b);
            panel.add(b);
            return b;
        };

        //消しゴム
        PenButton eraser = makePenButton.apply(Color.WHITE);
        eraser.setText("消");
        eraser.setFocusPainted(false);
        
        makePenButton.apply(Color.GRAY);
        makePenButton.apply(Color.RED);
        makePenButton.apply(new Color(255, 128, 0)); //オレンジ
        makePenButton.apply(Color.GREEN);
        makePenButton.apply(Color.BLUE);
        makePenButton.apply(Color.MAGENTA);
        makePenButton.apply(Color.BLACK).isPressed(true); //初期値は黒
        makePenButton.apply(Color.LIGHT_GRAY);
        makePenButton.apply(Color.PINK);
        makePenButton.apply(Color.YELLOW);
        makePenButton.apply(new Color(128, 255, 0)); //黄緑
        makePenButton.apply(new Color(0, 255, 255)); //水色
        makePenButton.apply(new Color(33, 0, 4));

        for (PenButton p : list) {
            p.addActionListener(e -> {
                se.acceptSE();
                color = p.getColor();
                updatePen();
                for (PenButton b : list) {
                    b.isPressed(false);
                }
                p.isPressed(true);
            });
        }

        GridLayout layout = new GridLayout(2, list.size() + 2);
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
            pen = (Pen) e.getItem();
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
        cb.setSelectedIndex(0);
        cb.addItemListener(e -> {
            radius = (int) e.getItem();
            updatePen();
        });

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
