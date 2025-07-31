package amc.view.manager;

import java.awt.*;
import javax.swing.*;

public class UiUtils {
    
    // Custom menu style
    public static void applyFlatHoverStyle(JButton button) {
        button.setBorderPainted(false); 
        button.setFocusPainted(false); 
        button.setContentAreaFilled(false); 
        button.setOpaque(false); 
        button.setForeground(Color.BLACK); 
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); //cursor turns hand shape when touching the button

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(new Color(0,153,153));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.BLACK);
            }
        });
    }
    
    // Custom comboBox style
    public static void styleComboBox(JComboBox comboBox) {
        comboBox.setBorder(BorderFactory.createEmptyBorder()); 
        comboBox.setFocusable(false); 
        comboBox.setBackground(Color.WHITE); 
        comboBox.setForeground(Color.BLACK);
        comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR)); 

    }
}
