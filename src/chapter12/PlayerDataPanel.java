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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author local-nattou
 */
public class PlayerDataPanel extends JPanel {
    
    private final String[] columnNames = {"色", "名前", "得点"};
    private final JTable dataTable;
    private final DefaultTableModel dataModel;
    
    public PlayerDataPanel() {
        this.setLayout(new BorderLayout(5, 5));
        
        dataModel = new DefaultTableModel(columnNames, 0);
        dataTable = new JTable(dataModel);
        dataTable.setEnabled(false);
        dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        dataTable.getColumn("色").setCellRenderer(new ColorRenderer());
        dataTable.getColumn("色").setMaxWidth(10);
        dataTable.getTableHeader().setResizingAllowed(false);
        
        JScrollPane scroll = new JScrollPane(dataTable);
        
        this.add(scroll, BorderLayout.CENTER);
    }
    
    public void addPlayer(UserData userData) {
        Object[] row = {userData.getColor(), userData.getDispName(), 0};
        dataModel.addRow(row);
    }
    
    public int getNumOfPlayers() {
        return dataModel.getRowCount();
    }
    
    public void setScore(String name, int score) {
        for(int i = 0; i < dataTable.getRowCount(); i++) {
            if(dataModel.getValueAt(i, dataTable.getColumn("名前").getModelIndex()).equals(name)) {
                int currentScore = (int) dataModel.getValueAt(i, dataTable.getColumn("得点").getModelIndex());
                dataModel.setValueAt(currentScore + score, i, dataTable.getColumn("得点").getModelIndex());
                System.out.println(currentScore + " → " + (currentScore + score));
            }
        }
    }
    
    public void resetScore() {
        for(int i = 0; i < dataTable.getRowCount(); i++) {
            dataModel.setValueAt(0, i, dataTable.getColumn("得点").getModelIndex());
        }
    }
    
    public String getWinner() {
        int bestScore = 0;
        int bestScoreIndex = 0;
        for(int i = 0; i < dataTable.getRowCount(); i++) {
            int score  = (int) dataModel.getValueAt(i, dataTable.getColumn("得点").getModelIndex());
            if(bestScore < score) {
                bestScore = score;
                bestScoreIndex = i;
            }
        }
        return (String) dataModel.getValueAt(bestScoreIndex, dataTable.getColumn("名前").getModelIndex());
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
