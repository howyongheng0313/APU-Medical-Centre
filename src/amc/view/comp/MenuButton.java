package amc.view.comp;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

public class MenuButton extends JButton {
    
    private Color defaultBackground = Color.WHITE;
    private Color hoverBackground = new Color(240, 240, 240);
    private Color defaultForeground = Color.BLACK;
    private Color hoverForeground = new Color(0, 153, 153);
    private Color userBackground = Color.WHITE; // Store user's custom background
    
    public MenuButton() {
        super();
        initializeButton();
    }

    public MenuButton(String text) {
        super(text);
        initializeButton();
    }
    
    private void initializeButton() {
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setForeground(defaultForeground);
        setBackground(defaultBackground);
        
        // Add hover effects
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setForeground(hoverForeground);
                setBackground(hoverBackground);
                repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setForeground(defaultForeground);
                // Return to the user's custom background color
                setBackground(userBackground);
                repaint();
            }
        });
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 50); // Menu button size
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
    
    // Getters and setters for customization
    public Color getDefaultBackground() {
        return defaultBackground;
    }
    
    public void setDefaultBackground(Color color) {
        this.defaultBackground = color;
        setBackground(color);
        repaint();
    }
    
    public Color getHoverBackground() {
        return hoverBackground;
    }
    
    public void setHoverBackground(Color color) {
        this.hoverBackground = color;
    }
    
    public Color getDefaultForeground() {
        return defaultForeground;
    }
    
    public void setDefaultForeground(Color color) {
        this.defaultForeground = color;
        setForeground(color);
        repaint();
    }
    
    public Color getHoverForeground() {
        return hoverForeground;
    }
    
    public void setHoverForeground(Color color) {
        this.hoverForeground = color;
    }
    
    @Override
    public void setBackground(Color color) {
        super.setBackground(color);
        // Store the user's custom background color
        if (color != null && !color.equals(hoverBackground)) {
            userBackground = color;
        }
    }
    

} 