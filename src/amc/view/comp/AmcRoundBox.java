package amc.view.comp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class AmcRoundBox extends JPanel {
    private Color $borderColor = null;
    private int $borderWidth = 1;
    private int $cornerRound = 0;

    public int get$borderWidth() {
        return $borderWidth;
    }

    public void set$borderWidth(int $borderWidth) {
        this.$borderWidth = $borderWidth;
    }

    public int get$cornerRound() {
        return $cornerRound;
    }

    public void set$cornerRound(int $cornerRound) {
        this.$cornerRound = $cornerRound;
    }

    public Color get$borderColor() {
        return $borderColor;
    }

    public void set$borderColor(Color $borderColor) {
        this.$borderColor = $borderColor;
    }

    public AmcRoundBox() {
        super();
        this.setBorder(null);
        this.setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(this.getBackground());
        g2.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), this.$cornerRound, this.$cornerRound);
        if (this.$borderColor != null && this.$borderWidth > 0) {
            g2.setColor(this.$borderColor);
            g2.setStroke(new BasicStroke(this.$borderWidth));
            g2.drawRoundRect(this.$borderWidth, this.$borderWidth, this.getWidth()-(this.$borderWidth<<1), this.getHeight()-(this.$borderWidth<<1), this.$cornerRound, this.$cornerRound);
        }
        g2.dispose();
        super.paintComponent(g);
    }
}
