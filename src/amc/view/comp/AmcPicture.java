package amc.view.comp;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Dimension;

public class AmcPicture extends JComponent {
    private Icon $image;
    private Dimension $maxSize = new Dimension(32767, 32767);

    public Icon get$image() {
        return this.$image;
    }

    public void set$image(Icon $image) {
        this.$image = $image;
    }

    public Dimension get$maxSize() {
        return this.$maxSize;
    }

    public void set$maxSize(Dimension $maxSize) {
        this.$maxSize = $maxSize == null ? new Dimension(0, 0) : $maxSize;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING    , RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);

        if (this.$image == null) { return; }

        Image img = ((ImageIcon)this.$image).getImage();
        int img_width, img_height;
        img_width  = Math.min(this.$maxSize.width , this.getWidth());
        img_height = Math.min(this.$maxSize.height, this.getHeight());

        double w_scale = (double) img_width  / img.getWidth(null);
        double h_scale = (double) img_height / img.getHeight(null);
        if (w_scale < h_scale) {
            img_height = (int) (w_scale * img.getHeight(null));
        } else {
            img_width  = (int) (h_scale * img.getWidth(null));
        }

        g2.drawImage(img,
            (this.getWidth()  - img_width ) / 2,
            (this.getHeight() - img_height) / 2,
            img_width, img_height, this
        );
        g2.dispose();
    }
}
