/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author local-nattou
 */
public class PlayerDataPanel extends JPanel {
    
    private final String[] columnNames = {"色", "名前", "得点"};
    private final JTextField themeField;
    private final JTable dataTable;
    private final DefaultTableModel dataModel;
    
    public PlayerDataPanel() {
        this.setLayout(new BorderLayout());
        themeField = new JTextField();
        themeField.setEditable(false);
        dataModel = new DefaultTableModel(columnNames, 0);
        dataTable = new JTable(dataModel);
        dataTable.setEnabled(false);
        dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        dataTable.getColumn("色").setCellRenderer(new ColorRenderer());
        dataTable.getColumn("色").setMaxWidth(10);
        dataTable.getTableHeader().setResizingAllowed(false);
        
        JScrollPane scroll = new JScrollPane(dataTable);
        
        this.add(Tools.LabeledJComponent("お題", themeField), BorderLayout.NORTH);
        this.add(scroll, BorderLayout.CENTER);
    }
    
    public void addPlayer(UserData userData) {
        Object[] row = {userData.getColor(), userData.getName(), 0};
        dataModel.addRow(row);
    }
    
    public void setTheme(String theme) {
        themeField.setText(theme);
    }
    
    private class ColorRenderer implements TableCellRenderer{
         @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Color c = (Color)value;
            JLabel label = new JLabel();
            label.setBackground(c);
            label.setOpaque(true);
            return label;
        }
    }
}
