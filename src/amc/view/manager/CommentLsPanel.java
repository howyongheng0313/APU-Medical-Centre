package amc.view.manager;

import amc.model.entity.CommentsDTO;
import amc.model.entity.Role;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class CommentLsPanel extends javax.swing.JPanel {

    private boolean isShowingDetails      = false;
    private String  currentRecipientName  = "";
    private String  selectedRecipientId   = "";
    private Role    selectedRecipientType = Role.Doctor;

    // Constructor
    public CommentLsPanel() {
        initComponents();
        btnReturn.setVisible(false);
        showEmptyState();
    }

    // Show summary of comments (List of recipients)
    public void showCommentSummary(List<CommentsDTO.CommentSummary> summaries) {
        isShowingDetails = false;
        currentRecipientName = "";
        btnReturn.setVisible(false);
        
        String[] columns = {"Recipient", "Type", "Comment Count", "Avg Rating"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        for (CommentsDTO.CommentSummary summary : summaries) {
            model.addRow(new Object[]{
                summary.getRecipientName(),
                summary.recipientType.name(),
                summary.getCommentCount(),
                String.format("%.1f", summary.getAverageRating())
            });
        }
        tblComments.setModel(model);
        lblSubtitle.setText("Double click a row to view details");
        // Store summaries in table client property for lookup on double-click
        tblComments.putClientProperty("summaries", summaries);
    }

    // Show details of single recipient
    public void showCommentDetails(String recipientName, List<CommentsDTO.CommentDetail> details) {
        isShowingDetails = true;
        currentRecipientName = recipientName;
        btnReturn.setVisible(true);

        String[] columns = {"Date", "Customer", "Rating", "Comment"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        for (CommentsDTO.CommentDetail detail : details) {
            model.addRow(new Object[]{
                detail.appointmentDate,
                detail.customerName,
                detail.rating.name() + " (" + detail.rating.getLevel() + ")",
                detail.content
            });
        }
        tblComments.setModel(model);
        lblSubtitle.setText("Comments for " + currentRecipientName + " - Click Return to go back");
    }

    // Show empty state when no comments available
    private void showEmptyState() {
        String[] columns = {"Recipient", "Type", "Comment Count", "Avg Rating"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tblComments.setModel(model);
        lblSubtitle.setText("No comment data available");
    }

    // Open details view for the selected row in summary mode.
    private void openCommentDetails(int selectedRow) {
        @SuppressWarnings("unchecked")
        var summaries = (List<CommentsDTO.CommentSummary>) tblComments.getClientProperty("summaries");
        if (summaries == null) return;

        if (selectedRow >= 0 && selectedRow < summaries.size()) {
            var summary = summaries.get(selectedRow);
            selectedRecipientId = summary.recipientId;
            selectedRecipientType = summary.recipientType;
            firePropertyChange("showDetails", false, true);
        }
    }

    // Getters
    public String getSelectedRecipientId() { return selectedRecipientId; } 
    public Role getSelectedRecipientType() { return selectedRecipientType; }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        header = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(32767, 0));
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(0, 0));
        jScrollPane1 = new javax.swing.JScrollPane();
        tblComments = new javax.swing.JTable();
        footer = new javax.swing.JPanel();
        btnReturn = new amc.view.comp.AmcButton();

        setMaximumSize(new java.awt.Dimension(600, 200));
        setPreferredSize(new java.awt.Dimension(800, 500));
        setLayout(new java.awt.BorderLayout());

        header.setBackground(new java.awt.Color(245, 253, 253));
        header.setPreferredSize(new java.awt.Dimension(800, 100));
        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {40, 0};
        jPanel3Layout.columnWeights = new double[] {0.0, 1.0};
        header.setLayout(jPanel3Layout);

        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        lblTitle.setText("Comments");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 95, 0, 0);
        header.add(lblTitle, gridBagConstraints);

        lblSubtitle.setText("Please double click the table to check the details of comment");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 0);
        header.add(lblSubtitle, gridBagConstraints);

        add(header, java.awt.BorderLayout.PAGE_START);

        filler3.setBackground(new java.awt.Color(245, 253, 253));
        filler3.setOpaque(true);
        add(filler3, java.awt.BorderLayout.LINE_END);

        filler1.setBackground(new java.awt.Color(245, 253, 253));
        filler1.setOpaque(true);
        add(filler1, java.awt.BorderLayout.LINE_START);

        tblComments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCommentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblComments);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        footer.setBackground(new java.awt.Color(245, 253, 253));
        footer.setPreferredSize(new java.awt.Dimension(100, 62));

        btnReturn.setText("Return");
        btnReturn.setPreferredSize(new java.awt.Dimension(95, 50));
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });
        footer.add(btnReturn);

        add(footer, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void tblCommentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCommentsMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            int selectedRow = tblComments.getSelectedRow();
            if (selectedRow != -1){
                if (!isShowingDetails){
                    openCommentDetails(selectedRow);
                }
            }
        }
    }//GEN-LAST:event_tblCommentsMouseClicked

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
        if (isShowingDetails) {
            firePropertyChange("returnToSummary", false, true);
        }   
    }//GEN-LAST:event_btnReturnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private amc.view.comp.AmcButton btnReturn;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel header;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblComments;
    // End of variables declaration//GEN-END:variables
}
