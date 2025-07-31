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
    private Dimension $max_size = new Dimension(32767, 32767);

    public Icon get$image() {
        return $image;
    }

    public void set$image(Icon $image) {
        this.$image = $image;
    }

    public Dimension get$max_size() {
        return $max_size;
    }

    public void set$max_size(Dimension $max_size) {
        this.$max_size = $max_size == null ? new Dimension(0, 0) : $max_size;
    }

    @Override
    public void paintComponent(Graphics grph) {
        super.paintComponent(grph);
        Graphics2D grph2d = (Graphics2D) grph.create();
        grph2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        grph2d.setRenderingHint(RenderingHints.KEY_RENDERING    , RenderingHints.VALUE_RENDER_QUALITY);
        grph2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);

        if (this.$image == null) { return; }

        Image img = ((ImageIcon)this.$image).getImage();
        int img_width  = Math.min(this.$max_size.width , this.getWidth());
        int img_height = Math.min(this.$max_size.height, this.getHeight());
        double w_scale = (double) img_width  / img.getWidth(null);
        double h_scale = (double) img_height / img.getHeight(null);
        if (w_scale < h_scale) {
            img_height = (int) (w_scale * img.getHeight(null));
        } else {
            img_width  = (int) (h_scale * img.getWidth(null));
        }

        grph2d.drawImage(img,
            (this.getWidth()  - img_width ) / 2,
            (this.getHeight() - img_height) / 2,
            img_width, img_height, this
        );
    }
}
