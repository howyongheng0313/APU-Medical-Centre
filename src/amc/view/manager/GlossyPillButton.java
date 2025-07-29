package amc.view.manager;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

public class GlossyPillButton extends JButton {
    
     public GlossyPillButton() {
        super();
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    public GlossyPillButton(String text) {
        this();
        setText(text);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(180, 60); // Button size
    }

    @Override
    protected void paintComponent(Graphics g) {
        int arc = getHeight(); // Full pill shape
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw base
        Shape pill = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, arc, arc);
        g2.setColor(getBackground());
        g2.fill(pill);

        // Draw glossy highlight
        GradientPaint gloss = new GradientPaint(
            0, 0, new Color(255,255,255,180),
            0, getHeight()/2, new Color(255,255,255,60)
        );
        g2.setPaint(gloss);
        g2.fill(new RoundRectangle2D.Float(2, 2, getWidth()-5, getHeight()/2, arc-4, arc-4));

        // Draw border
        g2.setColor(new Color(180,180,180));
        g2.setStroke(new BasicStroke(2f));
        g2.draw(pill);

        // Draw text
        FontMetrics fm = g2.getFontMetrics();
        Rectangle stringBounds = fm.getStringBounds(getText(), g2).getBounds();
        int textX = (getWidth() - stringBounds.width) / 2;
        int textY = (getHeight() - stringBounds.height) / 2 + fm.getAscent();
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(getText(), textX, textY);

        g2.dispose();
        // Don't call super.paintComponent(g) to avoid default button painting
    }
}
