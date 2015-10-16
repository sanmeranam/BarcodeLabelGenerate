/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.san;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author M1028704
 */
public class FontComboBoxRenderer extends JLabel implements ListCellRenderer {
 
    public FontComboBoxRenderer() {
        setOpaque(true);
        setFont(new Font("Arial", Font.PLAIN, 14));
    }
     
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setFont(new Font(value.toString(), Font.PLAIN, 14));
        setText(value.toString());
        return this;
    }
 
}
