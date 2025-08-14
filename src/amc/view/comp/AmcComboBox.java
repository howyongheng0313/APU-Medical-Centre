package amc.view.comp;

import java.awt.*;
import javax.swing.*;

public class AmcComboBox {
    
    // Custom comboBox style
    public static void styleComboBox(JComboBox comboBox) {
        comboBox.setBorder(BorderFactory.createEmptyBorder()); 
        comboBox.setFocusable(false); 
        comboBox.setBackground(Color.WHITE); 
        comboBox.setForeground(Color.BLACK);
        comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
    }
}
