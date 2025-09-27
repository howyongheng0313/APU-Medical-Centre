//package amc.view.manager;
//
//import amc.model.entity.AppointmentDTO;
//import amc.model.entity.Role;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.List;
//
//public class AppointmentPanel extends JPanel {
//
//    private JTable tblAppointments;
//    private JLabel lblSubtitle;
//    private JButton btnReturn;
//
//    private boolean isShowingDetails = false;
//    private String selectedAppointmentId = "";
//
//    public AppointmentPanel() {
//        initComponents();
//    }
//
//    // -------------------- Summary --------------------
//    public void showAppointmentSummary(List<AppointmentDTO.AppointmentSummary> summaries) {
//        isShowingDetails = false;
//        btnReturn.setVisible(false);
//
//        String[] columns = {"Recipient", "Type", "Appointment Count"};
//        DefaultTableModel model = new DefaultTableModel(columns, 0) {
//            @Override
//            public boolean isCellEditable(int r, int c) { return false; }
//        };
//
//        for (AppointmentDTO.AppointmentSummary summary : summaries) {
//            model.addRow(new Object[]{
//                    summary.getRecipientName(),
//                    summary.recipientType.name(),
//                    summary.getAppointmentCount()
//            });
//        }
//
//        tblAppointments.setModel(model);
//        tblAppointments.putClientProperty("summaries", summaries);
//        lblSubtitle.setText("Double-click a row to view appointment details");
//    }
//
//    // -------------------- Detail --------------------
//    public void showAppointmentDetails(List<AppointmentDTO.AppointmentDetail> details) {
//        isShowingDetails = true;
//        btnReturn.setVisible(true);
//
//        String[] columns = {"Field", "Value"};
//        DefaultTableModel model = new DefaultTableModel(columns, 0) {
//            @Override
//            public boolean isCellEditable(int r, int c) { return false; }
//        };
//
//        for (AppointmentDTO.AppointmentDetail detail : details) {
//            model.addRow(new Object[]{"Appointment ID", detail.appointmentId});
//            model.addRow(new Object[]{"Customer ID", detail.customerId});
//            model.addRow(new Object[]{"Doctor ID", detail.doctorId != null ? detail.doctorId : ""});
//            model.addRow(new Object[]{"Staff ID", detail.staffId != null ? detail.staffId : ""});
//            model.addRow(new Object[]{"Department", detail.departmentId});
//            model.addRow(new Object[]{"Date", detail.date});
//            model.addRow(new Object[]{"Time", detail.time});
//            model.addRow(new Object[]{"Status", detail.status});
//            model.addRow(new Object[]{"Notes", detail.notes != null ? detail.notes : ""});
//        }
//
//        tblAppointments.setModel(model);
//        lblSubtitle.setText("Appointment Details - Click Return to go back");
//    }
//
//    // -------------------- Selected Appointment --------------------
//    public String getSelectedAppointmentId() {
//        return selectedAppointmentId;
//    }
//
//    // -------------------- UI --------------------
//    private void initComponents() {
//        setLayout(new BorderLayout());
//
//        lblSubtitle = new JLabel("Appointments");
//        lblSubtitle.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
//        add(lblSubtitle, BorderLayout.NORTH);
//
//        tblAppointments = new JTable();
//        tblAppointments.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                if (evt.getClickCount() == 2 && !isShowingDetails) {
//                    int row = tblAppointments.getSelectedRow();
//                    if (row != -1) {
//                        @SuppressWarnings("unchecked")
//                        List<AppointmentDTO.AppointmentSummary> summaries =
//                                (List<AppointmentDTO.AppointmentSummary>) tblAppointments.getClientProperty("summaries");
//                        selectedAppointmentId = summaries.get(row).appointmentID;
//                        firePropertyChange("showDetails", false, true);
//                    }
//                }
//            }
//        });
//
//        JScrollPane scrollPane = new JScrollPane(tblAppointments);
//        add(scrollPane, BorderLayout.CENTER);
//
//        btnReturn = new JButton("Return");
//        btnReturn.addActionListener(e -> {
//            if (isShowingDetails) firePropertyChange("returnToSummary", false, true);
//        });
//        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        footer.add(btnReturn);
//        add(footer, BorderLayout.SOUTH);
//        btnReturn.setVisible(false);
//    }
//}
