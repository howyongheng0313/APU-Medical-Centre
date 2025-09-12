package amc.controller;

import amc.view.doctor.DoctorPanel;
import amc.view.share.BarButton;
import amc.view.share.BarComp;
import javax.swing.JPanel;

public class DoctorCtl extends UserCtl {
    private final BarButton appointmentsBarBtn = new BarButton("Appointments", "Appointments");
    private final DoctorPanel viewBody = new DoctorPanel();
    private final BarComp     viewBar  = new BarComp(viewBody,
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
    public BarComp getViewBar() { return viewBar; }
}
