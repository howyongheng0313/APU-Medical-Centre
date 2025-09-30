package amc.view.manager;

import amc.model.entity.AppointmentDTO;
import amc.view.share.ApptViewPanel;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsPanel extends javax.swing.JPanel {

    private final List<ApptViewPanel> appointmentPanels = new ArrayList<>();

    public AppointmentsPanel() {
        initComponents();
        this.jScrollPane1.getVerticalScrollBar().setUnitIncrement(8);
    }
    
     public void updateAppointments(List<AppointmentDTO> appointments) {
        this.clearAppointmentPanels();
        
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        gridBagConstraints.weightx = 1.0;
        
        for (int i = 0; i < appointments.size(); i++) {
            AppointmentDTO appointment = appointments.get(i);
            ApptViewPanel appointmentPanel = new ApptViewPanel();
            appointmentPanel.setAppointmentData(appointment);
            
            gridBagConstraints.gridy = i;
            gridBagConstraints.weighty = 0.0;
            jPanel3.add(appointmentPanel, gridBagConstraints);
            appointmentPanels.add(appointmentPanel);
        }
        
        // Add filler at the end
        gridBagConstraints.gridy = appointments.size();
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        jPanel3.add(filler1, gridBagConstraints);
        
        // Refresh display
        jPanel3.revalidate();
        jPanel3.repaint();
        jScrollPane1.revalidate();
        jScrollPane1.repaint();
    }
    
    private void clearAppointmentPanels() {
        for (ApptViewPanel panel : appointmentPanels) {
            jPanel3.remove(panel);
        }
        appointmentPanels.clear();
        jPanel3.remove(filler1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        jPanel1 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(243, 253, 253));
        setPreferredSize(new java.awt.Dimension(800, 500));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 200, 0};
        layout.rowHeights = new int[] {0, 50, 0};
        layout.columnWeights = new double[] {0.2, 0.6, 0.2};
        layout.rowWeights = new double[] {1.0, 0.0};
        setLayout(layout);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportView(null);

        jPanel3.setBackground(new java.awt.Color(245, 253, 253));
        jPanel3.setPreferredSize(new java.awt.Dimension(0, 1000));
        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWeights = new double[] {1.0};
        jPanel3.setLayout(jPanel3Layout);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(filler1, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        add(jScrollPane1, gridBagConstraints);

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 50));
        jPanel1.setLayout(new javax.swing.OverlayLayout(jPanel1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
