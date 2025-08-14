package amc.view.manager;
import java.awt.*;

public class Dashboard extends javax.swing.JPanel {

    public Dashboard() {
        initComponents();
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
        jButton1 = new javax.swing.JButton();
        reports = new javax.swing.JPanel();
        patientNumberReport = new javax.swing.JPanel();
        incomeReport = new javax.swing.JPanel();
        doctorRanking = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(800, 500));
        setLayout(new java.awt.BorderLayout());

        main.setBackground(new java.awt.Color(255, 255, 255));
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

        jButton1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout filterDetailsLayout = new javax.swing.GroupLayout(filterDetails);
        filterDetails.setLayout(filterDetailsLayout);
        filterDetailsLayout.setHorizontalGroup(
            filterDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterDetailsLayout.createSequentialGroup()
                .addGroup(filterDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSelectReport, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxReportType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addGap(55, 55, 55)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        filter.add(filterDetails, java.awt.BorderLayout.CENTER);

        main.add(filter, java.awt.BorderLayout.LINE_START);

        reports.setBackground(new java.awt.Color(255, 255, 255));
        reports.setLayout(new java.awt.CardLayout());

        patientNumberReport.setBackground(new java.awt.Color(0, 255, 102));

        javax.swing.GroupLayout patientNumberReportLayout = new javax.swing.GroupLayout(patientNumberReport);
        patientNumberReport.setLayout(patientNumberReportLayout);
        patientNumberReportLayout.setHorizontalGroup(
            patientNumberReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 612, Short.MAX_VALUE)
        );
        patientNumberReportLayout.setVerticalGroup(
            patientNumberReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 535, Short.MAX_VALUE)
        );

        reports.add(patientNumberReport, "card3");

        incomeReport.setBackground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout incomeReportLayout = new javax.swing.GroupLayout(incomeReport);
        incomeReport.setLayout(incomeReportLayout);
        incomeReportLayout.setHorizontalGroup(
            incomeReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 612, Short.MAX_VALUE)
        );
        incomeReportLayout.setVerticalGroup(
            incomeReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 535, Short.MAX_VALUE)
        );

        reports.add(incomeReport, "incomeReport");

        doctorRanking.setBackground(new java.awt.Color(0, 255, 204));
        reports.add(doctorRanking, "card4");

        main.add(reports, java.awt.BorderLayout.CENTER);

        add(main, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String selected = (String) cbxReportType.getSelectedItem();
        String cardName = "";

        // Map selection to card name
        switch (selected) {
            case "Income Report" -> cardName = "incomeReport";
            case "Patients Number Report" -> cardName = "card3";
            case "Doctor Performance Report" -> cardName = "card4";
        }

        // Show the correct card
        CardLayout cl = (CardLayout)(reports.getLayout());
        cl.show(reports, cardName);
    }//GEN-LAST:event_jButton1ActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxReportType;
    private javax.swing.JPanel doctorRanking;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler5;
    private javax.swing.JPanel filter;
    private javax.swing.JPanel filterDetails;
    private javax.swing.JPanel incomeReport;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblSelectReport;
    private javax.swing.JPanel main;
    private javax.swing.JPanel patientNumberReport;
    private javax.swing.JPanel reports;
    // End of variables declaration//GEN-END:variables
}
