/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package amc.view.staff;

/**
 *
 * @author kzy
 */
public class StaffPanel extends javax.swing.JPanel {

    /**
     * Creates new form StaffPanel
     */
    public StaffPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bookingPanel1 = new amc.view.staff.BookingPanel();
        customerLsPanel1 = new amc.view.staff.CustomerLsPanel();
        payLsPanel1 = new amc.view.staff.PayLsPanel();

        setPreferredSize(new java.awt.Dimension(800, 500));
        setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout bookingPanel1Layout = new javax.swing.GroupLayout(bookingPanel1);
        bookingPanel1.setLayout(bookingPanel1Layout);
        bookingPanel1Layout.setHorizontalGroup(
            bookingPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        bookingPanel1Layout.setVerticalGroup(
            bookingPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(bookingPanel1, "Booking");

        javax.swing.GroupLayout customerLsPanel1Layout = new javax.swing.GroupLayout(customerLsPanel1);
        customerLsPanel1.setLayout(customerLsPanel1Layout);
        customerLsPanel1Layout.setHorizontalGroup(
            customerLsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        customerLsPanel1Layout.setVerticalGroup(
            customerLsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(customerLsPanel1, "CustomerLs");

        javax.swing.GroupLayout payLsPanel1Layout = new javax.swing.GroupLayout(payLsPanel1);
        payLsPanel1.setLayout(payLsPanel1Layout);
        payLsPanel1Layout.setHorizontalGroup(
            payLsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        payLsPanel1Layout.setVerticalGroup(
            payLsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(payLsPanel1, "PayLs");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private amc.view.staff.BookingPanel bookingPanel1;
    private amc.view.staff.CustomerLsPanel customerLsPanel1;
    private amc.view.staff.PayLsPanel payLsPanel1;
    // End of variables declaration//GEN-END:variables
}
