package amc.view.manager;

import java.awt.*;

public class ReportPanel extends javax.swing.JPanel {    
    public ReportPanel() {
        initComponents();
        ((CardLayout) reports.getLayout()).show(reports, "IncomeReport" );
    }

    // Get selected year
    public int getSelectedYear() {
        return Integer.parseInt((String) cbxYear.getSelectedItem());
    }
    
    // Get selected report type
    public String getSelectedReportType() {
        return (String) cbxReportType.getSelectedItem();
    }

    public void switchReport(String page) {
        ((CardLayout) reports.getLayout()).show(reports, page);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        filter = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(32767, 0));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 200), new java.awt.Dimension(0, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(32767, 0));
        filterDetails = new javax.swing.JPanel();
        lblSelectReport = new javax.swing.JLabel();
        cbxReportType = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btnGenerate = new javax.swing.JButton();
        cbxYear = new javax.swing.JComboBox<>();
        reports = new javax.swing.JPanel();
        incomeReportPanel1 = new amc.view.manager.IncomeReportPanel();
        patientNumberReportPanel1 = new amc.view.manager.PatientNumberReportPanel();
        doctorPerformanceReportPanel1 = new amc.view.manager.DoctorPerformanceReportPanel();

        setPreferredSize(new java.awt.Dimension(800, 500));
        setLayout(new java.awt.BorderLayout());

        main.setBackground(new java.awt.Color(243, 253, 253));
        main.setLayout(new java.awt.BorderLayout());

        filter.setPreferredSize(new java.awt.Dimension(200, 100));
        filter.setLayout(new java.awt.BorderLayout());

        filler2.setBackground(new java.awt.Color(245, 253, 253));
        filler2.setOpaque(true);
        filter.add(filler2, java.awt.BorderLayout.LINE_END);

        filler3.setBackground(new java.awt.Color(243, 253, 253));
        filler3.setOpaque(true);
        filter.add(filler3, java.awt.BorderLayout.PAGE_END);

        filler5.setBackground(new java.awt.Color(245, 253, 253));
        filler5.setOpaque(true);
        filter.add(filler5, java.awt.BorderLayout.LINE_START);

        filterDetails.setBackground(new java.awt.Color(245, 253, 253));
        filterDetails.setMinimumSize(new java.awt.Dimension(98, 60));

        lblSelectReport.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblSelectReport.setText("Select Report Type");

        cbxReportType.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbxReportType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Income Report", "Patients Number Report", "Doctor Performance Report" }));
        cbxReportType.setPreferredSize(new java.awt.Dimension(110, 22));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setText("Select Year");

        btnGenerate.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnGenerate.setText("Generate Report");

        cbxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2025", "2024", "2023", "2022", "2021" }));

        javax.swing.GroupLayout filterDetailsLayout = new javax.swing.GroupLayout(filterDetails);
        filterDetails.setLayout(filterDetailsLayout);
        filterDetailsLayout.setHorizontalGroup(
            filterDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterDetailsLayout.createSequentialGroup()
                .addGroup(filterDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbxYear, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSelectReport, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxReportType, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGenerate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        filterDetailsLayout.setVerticalGroup(
            filterDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterDetailsLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblSelectReport, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxReportType, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxYear, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        filter.add(filterDetails, java.awt.BorderLayout.CENTER);

        main.add(filter, java.awt.BorderLayout.LINE_START);

        reports.setBackground(new java.awt.Color(243, 253, 253));
        reports.setLayout(new java.awt.CardLayout());
        reports.add(incomeReportPanel1, "incomeReport");

        patientNumberReportPanel1.setBackground(new java.awt.Color(243, 253, 253));
        reports.add(patientNumberReportPanel1, "patientNumberReport");

        doctorPerformanceReportPanel1.setBackground(new java.awt.Color(255, 255, 255));
        reports.add(doctorPerformanceReportPanel1, "doctorPerformanceReport");

        main.add(reports, java.awt.BorderLayout.CENTER);

        add(main, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnGenerate;
    private javax.swing.JComboBox<String> cbxReportType;
    private javax.swing.JComboBox<String> cbxYear;
    public amc.view.manager.DoctorPerformanceReportPanel doctorPerformanceReportPanel1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler5;
    private javax.swing.JPanel filter;
    private javax.swing.JPanel filterDetails;
    public amc.view.manager.IncomeReportPanel incomeReportPanel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblSelectReport;
    private javax.swing.JPanel main;
    public amc.view.manager.PatientNumberReportPanel patientNumberReportPanel1;
    private javax.swing.JPanel reports;
    // End of variables declaration//GEN-END:variables
}
