package amc.controller;

import amc.model.DbMan;
import amc.model.db_impl.Db;
import amc.model.entity.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentCtl extends AbstractSubCtl {
    
    public CommentCtl(AmcCtl ROOT){
        super(ROOT);
    }

    // Get comment summaries for both Doctor and Staff
    public List<CommentsDTO.CommentSummary> getCommentSummarys() {
        try {
            // Load all required data
            Map<String, CommentsDTO.CommentSummary> smrMap = new HashMap<>();

            DbMan.Query<Comment> smrCountEach = (Comment model) -> {
                smrMap.computeIfAbsent(model.getTargetId(), k -> new CommentsDTO.CommentSummary(
                    model.getTargetId(),
                    model.getTargetId().startsWith("DOC") ?
                        CommentsDTO.RecipientType.Doctor : CommentsDTO.RecipientType.Staff
                )).addCount(model.getRating());
                return false;
            };

            DbMan.Query<Employee> smrNameEach = (model) -> {
                CommentsDTO.CommentSummary smr = smrMap.get(model.getUserId());
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
    public List<CommentsDTO.CommentDetail> getCommentDetails(String recipientId, CommentsDTO.RecipientType type) {
        try {
            // Load required data
            List<Comment> comments = Db.Comment.select(-1, c -> true);
            List<Appointment> appts = Db.Appointment.select(-1, a -> true);
            List<Customer> customers = Db.Customer.select(-1, c -> true);

            // Build lookup maps
            Map<String, Appointment> apptMap = new HashMap<>();
            for (Appointment apt : appts) {
                apptMap.put(apt.getAppointmentId(), apt);
            }

            Map<String, Customer> custMap = new HashMap<>();
            for (Customer cust : customers) {
                custMap.put(cust.getUserId(), cust);
            }

            // Get recipient name
            String recipientName = getRecipientName(recipientId, type);

            // Collect matching comments
            List<CommentsDTO.CommentDetail> output = new ArrayList<>();
            for (Comment comment : comments) {
                Appointment apt = apptMap.get(comment.getAppointmentId());
                if (apt == null) continue;

                // Check if comment belongs to this recipient
                boolean match = switch (type) {
                    case Doctor -> recipientId.equals(apt.getDoctorId());
                    case Staff  -> recipientId.equals(apt.getStaffId());
                };
                if (!match) continue;

                // Get customer name
                Customer cust = custMap.get(apt.getCustomerId());
                String custName = cust != null ? cust.getUserName() : "Unknown";

                output.add(new CommentsDTO.CommentDetail(
                    comment.getCommentId(),
                    comment.getAppointmentId(),
                    apt.getCustomerId(),
                    custName,
                    recipientId,
                    recipientName,
                    type,
                    comment.getRating(),
                    comment.getContent(),
                    apt.getDateTime().toLocalDate().toString()
                ));
            }

            // Sort by date (latest first)
            output.sort((x, y) -> y.appointmentDate.compareTo(x.appointmentDate));
            return output;
            
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // Helper method to get recipient name
    private String getRecipientName(CommentsDTO.RecipientType type, String id, 
                                   Map<String, Doctor> doctorMap, Map<String, Staff> staffMap) {
        return switch (type) {
            case Doctor -> {
                Doctor d = doctorMap.get(id);
                yield d != null ? d.getUserName() : "Unknown Doctor";
            }
            case Staff -> {
                Staff s = staffMap.get(id);
                yield s != null ? s.getUserName() : "Unknown Staff";
            }
        };
    }

    // Helper method to get recipient name (for details method)
    private String getRecipientName(String recipientId, CommentsDTO.RecipientType type) {
        return switch (type) {
            case Doctor -> {
                List<Doctor> ls = Db.Doctor.select(1, d -> d.getUserId().equals(recipientId));
                yield ls.isEmpty() ? "Unknown Doctor" : ls.get(0).getUserName();
            }
            case Staff -> {
                List<Staff> ls = Db.Staff.select(1, s -> s.getUserId().equals(recipientId));
                yield ls.isEmpty() ? "Unknown Staff" : ls.get(0).getUserName();
            }
        };
    }
}