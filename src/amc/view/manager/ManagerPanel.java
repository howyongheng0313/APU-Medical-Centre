package amc.view.manager;

public class ManagerPanel extends javax.swing.JPanel {

    public ManagerPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dashboard1 = new amc.view.manager.Dashboard();

        setPreferredSize(new java.awt.Dimension(800, 500));
        setLayout(new java.awt.CardLayout());
        add(dashboard1, "card2");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private amc.view.manager.Dashboard dashboard1;
    // End of variables declaration//GEN-END:variables
}
