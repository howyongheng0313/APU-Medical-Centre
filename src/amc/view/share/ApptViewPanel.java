package amc.view.share;

import amc.model.entity.AppointmentDTO;
import amc.view.Theme;
import java.time.format.DateTimeFormatter;

public class ApptViewPanel extends javax.swing.JPanel {

    public ApptViewPanel() {
        initComponents();
    }
    
public void setAppointmentData(AppointmentDTO appointmentDTO) {
        if (appointmentDTO == null) return;
        
        // Set appointment ID
        lblApptId.setText(appointmentDTO.getAppointmentId());
        
        // Set date and time
        if (appointmentDTO.getDateTime() != null) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            lblApptDate.setText(appointmentDTO.getDateTime().format(dateFormatter));
            
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            lblApptTime.setText(appointmentDTO.getDateTime().format(timeFormatter));
        }
        
        // Set patient name
        lblPatient.setText(appointmentDTO.getCustomerName() != null ? 
            appointmentDTO.getCustomerName() : "Unknown Patient");
        
        // Set doctor name  
        lblDoctor.setText(appointmentDTO.getDoctorName() != null ? 
            appointmentDTO.getDoctorName() : "No Doctor");
        
        // Set staff name
        lblStaff.setText(appointmentDTO.getStaffName() != null ? 
            appointmentDTO.getStaffName() : "No Staff");
        
        System.out.println("Set data for card: " + appointmentDTO.getAppointmentId() + 
            " - " + appointmentDTO.getCustomerName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblApptId = new javax.swing.JLabel();
        lblApptDate = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        lblPatient = new javax.swing.JLabel();
        lblDoctor = new javax.swing.JLabel();
        lblStaff = new javax.swing.JLabel();
        imgStaff = new amc.view.comp.AmcPicture();
        imgDoctor = new amc.view.comp.AmcPicture();
        imgPatient = new amc.view.comp.AmcPicture();
        lblApptTime = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        imgStatus = new amc.view.comp.AmcPicture();

        setBackground(Theme.C1_INTER);
        setPreferredSize(new java.awt.Dimension(554, 200));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.rowHeights = new int[] {30, 30, 5, 0, 0, 0};
        layout.columnWeights = new double[] {0.5, 0.5};
        layout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.5, 0.5, 0.5};
        setLayout(layout);

        lblApptId.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblApptId.setText("APT-001");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        add(lblApptId, gridBagConstraints);

        lblApptDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblApptDate.setText("27 July 2025");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        add(lblApptDate, gridBagConstraints);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        add(jSeparator1, gridBagConstraints);

        jPanel1.setOpaque(false);
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {30, 0};
        jPanel1Layout.columnWeights = new double[] {0.0, 1.0};
        jPanel1Layout.rowWeights = new double[] {0.5, 0.5, 0.5};
        jPanel1.setLayout(jPanel1Layout);

        lblPatient.setText("Patient Steve");
        lblPatient.setPreferredSize(new java.awt.Dimension(210, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel1.add(lblPatient, gridBagConstraints);

        lblDoctor.setText("Doctor Gan");
        lblDoctor.setMaximumSize(new java.awt.Dimension(68, 16));
        lblDoctor.setMinimumSize(new java.awt.Dimension(68, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel1.add(lblDoctor, gridBagConstraints);

        lblStaff.setText("Staff Kuek");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel1.add(lblStaff, gridBagConstraints);

        imgStaff.set$image(new javax.swing.ImageIcon(getClass().getResource("/amc/image/role_staff_30.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel1.add(imgStaff, gridBagConstraints);

        imgDoctor.set$image(new javax.swing.ImageIcon(getClass().getResource("/amc/image/role_doctor_30.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(imgDoctor, gridBagConstraints);

        imgPatient.set$image(new javax.swing.ImageIcon(getClass().getResource("/amc/image/role_patient_30.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(imgPatient, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 5, 1);
        add(jPanel1, gridBagConstraints);

        lblApptTime.setText("09 : 27 am");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        add(lblApptTime, gridBagConstraints);

        jPanel2.setOpaque(false);
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 30};
        jPanel2Layout.columnWeights = new double[] {1.0, 0.0};
        jPanel2Layout.rowWeights = new double[] {1.0};
        jPanel2.setLayout(jPanel2Layout);

        imgStatus.set$image(new javax.swing.ImageIcon(getClass().getResource("/amc/image/status_pending_30.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(imgStatus, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 0);
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private amc.view.comp.AmcPicture imgDoctor;
    private amc.view.comp.AmcPicture imgPatient;
    private amc.view.comp.AmcPicture imgStaff;
    private amc.view.comp.AmcPicture imgStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblApptDate;
    private javax.swing.JLabel lblApptId;
    private javax.swing.JLabel lblApptTime;
    private javax.swing.JLabel lblDoctor;
    private javax.swing.JLabel lblPatient;
    private javax.swing.JLabel lblStaff;
    // End of variables declaration//GEN-END:variables
}
