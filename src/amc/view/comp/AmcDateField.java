package amc.view.comp;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AmcDateField extends JTextField {
    private LocalDate $date = LocalDate.now();

    public LocalDate get$date() {
        return $date;
    }

    public void set$date(LocalDate $date) {
        this.$date = $date;
        this.setText(this.$date.toString());
    }

    public AmcDateField() {
        super();
        this.setText(this.$date.toString());
        this.setFocusable(false);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Window parentWindow = SwingUtilities.getWindowAncestor(AmcDateField.this);
                CalendarDialog calendar = new CalendarDialog(
                    parentWindow instanceof JFrame ? (JFrame) parentWindow : null,
                    AmcDateField.this.$date,
                    AmcDateField.this::set$date
                );

                calendar.setLocationRelativeTo(AmcDateField.this);
                calendar.setVisible(true);
            }
        });
    }
}
