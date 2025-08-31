package amc.controller;

import amc.view.doctor.DoctorPanel;
import amc.view.share.BarButton;
import amc.view.share.BarPanel;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class DoctorCtl extends UserCtl {
    private final BarButton appointmentsBarBtn = new BarButton("Appointments");
    private final DoctorPanel viewBody = new DoctorPanel();
    private final BarPanel    viewBar  = new BarPanel(
        appointmentsBarBtn
    );

    public DoctorCtl(AmcCtl ROOT) {
        super(ROOT);
        appointmentsBarBtn.addActionListener((ActionEvent e) -> {
        });
    }

    @Override
    public JPanel getViewBody() { return viewBody; }

    @Override
    public BarPanel getViewBar() { return viewBar; }
}
