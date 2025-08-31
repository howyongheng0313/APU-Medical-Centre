package amc.controller;

import amc.view.customer.CustomerPanel;
import amc.view.share.BarButton;
import amc.view.share.BarPanel;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class CustomerCtl extends UserCtl {
    private final BarButton appointmentsBarBtn = new BarButton("Appointments");
    private final BarButton bookApptBarBtn     = new BarButton("Book Appt");
    private final CustomerPanel viewBody = new CustomerPanel(); 
    private final BarPanel      viewBar  = new BarPanel(
        appointmentsBarBtn,
        bookApptBarBtn
    );

    public CustomerCtl(AmcCtl ROOT) {
        super(ROOT);
        appointmentsBarBtn.addActionListener((ActionEvent e) -> {
        });

        bookApptBarBtn.addActionListener((ActionEvent e) -> {
        });
    }

    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarPanel getViewBar() { return viewBar; }
}
