package amc.view.comp;

import amc.view.Theme;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class AmcButton extends JButton {
    private Color $hoverForeground = Theme.C2_FG_SELECT;
    private Color $hoverBackground = Theme.C2_BG_SELECT;
    private int dilation = 1;

    public Color get$hoverForeground() {
        return $hoverForeground;
    }

    public void set$hoverForeground(Color $hoverForeground) {
        this.$hoverForeground = $hoverForeground;
    }

    public Color get$hoverBackground() {
        return $hoverBackground;
    }

    public void set$hoverBackground(Color $hoverBackground) {
        this.$hoverBackground = $hoverBackground;
    }

    public AmcButton() {
        super();
        this.setOpaque(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setForeground(Theme.C2_FG);
        this.setBackground(Theme.C2_BG);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            private Color defaultFg;
            private Color defaultBg;

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                this.defaultFg = AmcButton.this.getForeground();
                this.defaultBg = AmcButton.this.getBackground();
                AmcButton.this.setForeground(AmcButton.this.$hoverForeground);
                AmcButton.this.setBackground(AmcButton.this.$hoverBackground);
                AmcButton.this.dilation = 0;
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AmcButton.this.setForeground(this.defaultFg);
                AmcButton.this.setBackground(this.defaultBg);
                AmcButton.this.dilation = 1;
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                AmcButton.this.dilation = 1;
            }
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AmcButton.this.dilation = 0;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(this.getBackground());
        g2.fillRoundRect(this.dilation, this.dilation, this.getWidth()-(this.dilation<<1), this.getHeight()-(this.dilation<<1), 20, 20);
        g2.dispose();
        super.paintComponent(g);
    }
}
