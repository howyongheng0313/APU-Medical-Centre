package amc.view.manager;

import java.awt.*;

public class ManagerPanel extends javax.swing.JPanel {
    private final CardLayout  cardLayout       = new CardLayout();
    private final Dashboard   dashboardPanel   = new Dashboard();
    private final PersonnelLs employeesPanel   = new PersonnelLs();
    private final CommentLs   commentsPanel    = new CommentLs();
    private final ApptLs      appointmentPanel = new ApptLs();
    private final ServiceLs   servicePanel     = new ServiceLs();
    private final MedicineLs  medicinePanel    = new MedicineLs();
    
    public ManagerPanel() {
        initComponents();
        setupCardLayout();
    }
    
    // Add all the panel into ManagerPanel using cardLayout
    private void setupCardLayout(){
       setLayout(cardLayout);
       add(dashboardPanel, "dashboard");
       add(employeesPanel, "employee");
       add(commentsPanel, "comment");
       add(appointmentPanel, "appointment");
       add(servicePanel, "service");
       add(medicinePanel, "medicine");
       
       // Set dashboard as default
       cardLayout.show(this, "dashboard");
    }
    
    public void showCard(String cardName){
        cardLayout.show(this, cardName);
    }
    
    public Dashboard getDashboard(){
        return dashboardPanel;
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(800, 500));
        setLayout(new java.awt.CardLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
