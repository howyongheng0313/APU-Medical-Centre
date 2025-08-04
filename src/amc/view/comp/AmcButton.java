package amc.view.comp;

import java.awt.*;
import javax.swing.JButton;
import java.awt.geom.RoundRectangle2D;


public class AmcButton extends JButton {
    
     public AmcButton() {
        super();
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    public AmcButton(String text) {
        this();
        setText(text);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(180, 60); // Button size
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Calculate corner radius for rounder corners
        int cornerRadius = Math.min(getWidth(), getHeight()) / 3;
        
        // Draw base shape with rounded corners
        Shape pill = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, cornerRadius, cornerRadius);
        g2.setColor(getBackground());
        g2.fill(pill);

        // Draw text with current font settings
        FontMetrics fm = g2.getFontMetrics();
        Rectangle stringBounds = fm.getStringBounds(getText(), g2).getBounds();
        int textX = (getWidth() - stringBounds.width) / 2;
        int textY = (getHeight() - stringBounds.height) / 2 + fm.getAscent();
        g2.setColor(getForeground());
        g2.drawString(getText(), textX, textY);

        g2.dispose();
    }
}
