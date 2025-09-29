package amc.controller.manager;

import amc.controller.AbstractSubCtl;
import amc.controller.AmcCtl;
import amc.model.DbMan;
import amc.model.db_impl.Db;
import amc.model.entity.*;
import amc.view.manager.CommentsPanel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CommentsCtl extends AbstractSubCtl {
    private final CommentsPanel viewComments = new CommentsPanel();
    
    public CommentsCtl(AmcCtl ROOT){
        super(ROOT);
        setupCommentFeature();
        loadCommentSummary();
    }

    // Setup comment feature: Handles switching between summary and details view
    private void setupCommentFeature() {
        // From summary → show details
        viewComments.addPropertyChangeListener("showDetails", evt -> {
            String id = viewComments.getSelectedRecipientId();
            Role type = viewComments.getSelectedRecipientType();
            if (id != null && !id.isEmpty()) loadCommentDetails(id, type);
        });

        // From details → return to summary
        viewComments.addPropertyChangeListener("returnToSummary", evt -> {
            loadCommentSummary();
        });
    }

    // Load and display comment summaries
    private void loadCommentSummary() {
        try {
            var summaries = this.getCommentSummarys();
            viewComments.showCommentSummary(summaries);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                viewComments, "Error loading comment summary: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Load and display detailed comments for a specific recipient.
    private void loadCommentDetails(String recipientId, Role recipientType) {
        try {
            var details = this.getCommentDetails(recipientId, recipientType);
            String name = details.isEmpty() ? "Unknown" : details.get(0).recipientName;
            viewComments.showCommentDetails(name, details);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                viewComments, "Error loading comment details: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Get comment summaries for both Doctor and Staff
    public List<CommentsDTO.CommentSummary> getCommentSummarys() {
        try {
            // Load all required data
            Map<String, CommentsDTO.CommentSummary> smrMap = new HashMap<>();

            DbMan.Query<Comment> smrCountEach = (Comment model) -> {
                smrMap.computeIfAbsent(model.getTargetId(), k -> new CommentsDTO.CommentSummary(
                    model.getTargetId(),
                    model.getTargetId().startsWith("DOC") ? Role.Doctor : Role.Staff
                )).addCount(model.getRating());
                return false;
            };

            DbMan.Query<Employee> smrNameEach = (model) -> {
                CommentsDTO.CommentSummary smr = smrMap.get(model.getId());
                if (smr != null) smr.setRecipientName(model.getUserName());
                return false;
            };

            Db.Comment.select(1, smrCountEach);
            Db.Doctor.select(1, smrNameEach);
            Db.Staff.select(1, smrNameEach);

            return smrMap.values().stream().sorted((a, b) -> {
                int cmp = Integer.compare(a.getCommentCount(), b.getCommentCount());
                if (cmp != 0) return cmp;
                return a.getRecipientName().compareToIgnoreCase(b.getRecipientName());
            }).toList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // Get detailed comments for a specific recipient
    public List<CommentsDTO.CommentDetail> getCommentDetails(String recipientId, Role type) {
        try {
            // Load required data
            List<Comment> comments = Db.Comment.select(-1, c -> true);
            List<Appointment> appts = Db.Appointment.select(-1, a -> true);
            List<Customer> customers = Db.Customer.select(-1, c -> true);

            // Build lookup maps
            Map<String, Appointment> apptMap = new HashMap<>();
            for (Appointment apt : appts) {
                apptMap.put(apt.getId(), apt);
            }

            Map<String, Customer> custMap = new HashMap<>();
            for (Customer cust : customers) {
                custMap.put(cust.getId(), cust);
            }

            // Get recipient name
            String recipientName = getRecipientName(recipientId, type);

            // Collect matching comments
            List<CommentsDTO.CommentDetail> output = new ArrayList<>();
            for (Comment comment : comments) {
                Appointment appt = apptMap.get(comment.getAppointmentId());
                if (appt == null) continue;

                // Check if comment belongs to this recipient
                String checkId = type == Role.Doctor ? appt.getDoctorId() : appt.getStaffId();
                if (!recipientId.equals(checkId)) continue;

                // Get customer name
                Customer cust = custMap.get(appt.getCustomerId());
                String custName = cust != null ? cust.getUserName() : "Unknown";
                
                String doctorFeedback = appt.getFeedback();
                if(doctorFeedback == null || doctorFeedback.trim().isEmpty()){
                    doctorFeedback = "No feedback provided";
                }

                output.add(new CommentsDTO.CommentDetail(
                    comment.getId(),
                    comment.getAppointmentId(),
                    appt.getCustomerId(),
                    custName,
                    recipientId,
                    recipientName,
                    type,
                    comment.getRating(),
                    comment.getContent(),
                    appt.getDateTime().toLocalDate().toString(),
                    doctorFeedback
                ));
            }

            // Sort by date (latest first)
            output.sort((x, y) -> y.appointmentDate.compareTo(x.appointmentDate));
            return output;
            
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // Helper method to get recipient name (for details method)
    private String getRecipientName(String recipientId, Role type) {
        return switch (type) {
            case Doctor -> {
                List<Doctor> ls = Db.Doctor.select(1, d -> d.getId().equals(recipientId));
                yield ls.isEmpty() ? "Unknown Doctor" : ls.get(0).getUserName();
            }
            case Staff -> {
                List<Staff> ls = Db.Staff.select(1, s -> s.getId().equals(recipientId));
                yield ls.isEmpty() ? "Unknown Staff" : ls.get(0).getUserName();
            }
            default -> null;
        };
    }

    public JPanel getView() { return viewComments; }
}