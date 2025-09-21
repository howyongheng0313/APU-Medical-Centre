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
        this.setText(this.$date.toString()); // ------> yongheng
        this.setFocusable(false);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Window parentWindow = SwingUtilities.getWindowAncestor(AmcDateField.this);
                //---------> yongheng
                if(parentWindow instanceof JFrame){
                    CalendarDialog calendar = new CalendarDialog(
                    (JFrame) SwingUtilities.getWindowAncestor(AmcDateField.this),
                    AmcDateField.this.$date,
                    AmcDateField.this::set$date
                    );
                    calendar.setModal(true);
                    calendar.setLocationRelativeTo(AmcDateField.this);
                    calendar.setVisible(true);
                }else{
                    CalendarDialog calendar = new CalendarDialog(
                        null,
                        AmcDateField.this.$date,
                        AmcDateField.this::set$date
                    );
                    calendar.setModal(true);
                    calendar.setLocationRelativeTo(AmcDateField.this);
                    calendar.setVisible(true);
                }  
                
                /*
                //--------> Zhengyu
                CalendarDialog calendar = new CalendarDialog(
                    (JFrame) SwingUtilities.getWindowAncestor(AmcDateField.this),
                    AmcDateField.this.$date,
                    AmcDateField.this::set$date
                );
                calendar.setVisible(true);
                */
            }
        });
    }
}
