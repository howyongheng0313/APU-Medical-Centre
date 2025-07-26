package amc.view;

import java.awt.*;
import javax.swing.*;

public class uiUtils {
    
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
    
    public static void styleComboBox(JComboBox comboBox) {
        comboBox.setBorder(BorderFactory.createEmptyBorder()); // remove border
        comboBox.setFocusable(false); // prevent ugly focus border
        comboBox.setBackground(Color.WHITE); // or match your panel color
        comboBox.setForeground(Color.BLACK);
        comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR)); // nice touch

    }
}
