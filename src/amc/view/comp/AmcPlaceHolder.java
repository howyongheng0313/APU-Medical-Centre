package amc.view.comp;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JTextField;

public class AmcPlaceHolder extends JTextField {
    private String $hint;
    private Color $holderColor;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getText().isEmpty() && !isFocusOwner()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor($holderColor);
            Insets ins = getInsets();
            FontMetrics fm = g2.getFontMetrics();
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            int x;
            switch (getHorizontalAlignment()) {
                case CENTER -> {
                    int textWidth = fm.stringWidth($hint);
                    x = (getWidth() - textWidth) / 2;
                }
                case RIGHT -> {
                    int textWidth = fm.stringWidth($hint);
                    x = getWidth() - ins.right - textWidth;
                }
                default -> x = ins.left;
            }

            g2.drawString($hint, x, y);
            g2.dispose();
        }
    }

    public String get$hint() { return $hint; }
    public Color get$holderColor() { return $holderColor; }

    public void set$hint(String $hint) { this.$hint = $hint; }
    public void set$holderColor(Color $holderColor) { this.$holderColor = $holderColor; }
}
