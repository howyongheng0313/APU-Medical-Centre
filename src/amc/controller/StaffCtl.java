package amc.controller;

import amc.view.share.BarButton;
import amc.view.share.BarPanel;
import amc.view.staff.StaffPanel;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class StaffCtl extends UserCtl {
    private final BarButton bookingsBarBtn  = new BarButton("Bookings");
    private final BarButton customersBarBtn = new BarButton("Customers");
    private final BarButton payingsBarBtn   = new BarButton("Payings");
    private final StaffPanel viewBody = new StaffPanel();
    private final BarPanel   viewBar  = new BarPanel(
        bookingsBarBtn,
        customersBarBtn,
        payingsBarBtn
    );

    public StaffCtl(AmcCtl ROOT) {
        super(ROOT);
        bookingsBarBtn.addActionListener((ActionEvent e) -> {
        });

        customersBarBtn.addActionListener(((ActionEvent e) -> {
        }));

        payingsBarBtn.addActionListener((ActionEvent e) -> {
        });
    }

    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarPanel getViewBar() { return viewBar; }
}
