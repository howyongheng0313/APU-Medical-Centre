package amc.controller;

import amc.model.DbMan;
import amc.model.db_impl.Db;
import amc.model.entity.*;
import amc.model.entity.CommentsDTO.CommentDetail;
import amc.model.entity.CommentsDTO.CommentSummary;
import amc.model.entity.CommentsDTO.RecipientType;
import amc.model.entity.CommentsDTO._CommentSummary;
import java.util.*;
import java.util.stream.Collectors;

public class CommentCtl extends AbstractSubCtl {
    
    // Controller
    public CommentCtl(AmcCtl ROOT){
        super(ROOT);
    }

    // Get comment summaries for both Doctor and Staff.
    public List<CommentSummary> getCommentSummary() {
        try {
            // Load all required data from Db
            List<Comment> comments = Db.Comment.select(-1, comment -> true);
            List<Appointment> appts = Db.Appointment.select(-1, appt -> true);
            Map<String, Appointment> apptMap = appts.stream()
                .collect(Collectors.toMap(Appointment::getAppointmentId, appt -> appt, (a,b)->a));

            List<Doctor> doctors = Db.Doctor.select(-1, doctor -> true);
            Map<String, Doctor> doctorMap = doctors.stream()
                .collect(Collectors.toMap(Doctor::getUserId, doctor -> doctor, (a,b)->a));

            List<Staff> staffs = Db.Staff.select(-1, staff -> true);
            Map<String, Staff> staffMap = staffs.stream()
                .collect(Collectors.toMap(Staff::getUserId, staff -> staff, (a,b)->a));

            // Group comments by recipient (Doctor or Staff). Key format: "Doctor|id" or "Staff|id"
            Map<String, List<Comment>> bucket = new HashMap<>();

            for (Comment comment : comments) {
                Appointment a = apptMap.get(comment.getAppointmentId());
                if (a == null) continue;

                if (a.getDoctorId() != null) {
                    String key = "Doctor|" + a.getDoctorId();
                    bucket.computeIfAbsent(key, k -> new ArrayList<>()).add(comment);
                }
                if (a.getStaffId() != null) {
                    String key = "Staff|" + a.getStaffId();
                    bucket.computeIfAbsent(key, k -> new ArrayList<>()).add(comment);
                }
            }

            // Build output list
            List<CommentSummary> output = new ArrayList<>();
            for (var e : bucket.entrySet()) {
                String[] parts = e.getKey().split("\\|", 2);
                RecipientType type = RecipientType.valueOf(parts[0]);
                String id = parts[1];
                List<Comment> list = e.getValue();

                // Resolve recipient name
                String name = switch (type) {
                    case Doctor -> {
                        Doctor d = doctorMap.get(id);
                        yield d != null ? d.getUserName() : "Unknown Doctor";
                    }
                    case Staff -> {
                        Staff s = staffMap.get(id);
                        yield s != null ? s.getUserName() : "Unknown Staff";
                    }
                };

                // Calculate average rating
                double avg = list.stream()
                        .mapToInt(c -> c.getRating().getLevel())
                        .average()
                        .orElse(0.0);
                
                output.add(new CommentSummary(
                        id, 
                        name, 
                        type, 
                        list.size(), 
                        avg)
                );
            }

            // Sort by comment count (desc), then by name
            output.sort((a, b) -> {
                int cmp = Integer.compare(b.commentCount, a.commentCount);
                if (cmp != 0) return cmp;
                return a.recipientName.compareToIgnoreCase(b.recipientName);
            });
            return output;
            
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<_CommentSummary> getCommentSummarys() {
        Map<String, _CommentSummary> smrMap = new HashMap<>();

        DbMan.Query<Comment> smrCountEach = (Comment model) -> {
            smrMap.computeIfAbsent(model.getTargetId(), k -> new _CommentSummary(
                model.getTargetId(),
                model.getTargetId().startsWith("DOC") ?
                    RecipientType.Doctor : RecipientType.Staff
            )).addCount(model.getRating());
            return false;
        };

        DbMan.Query<Employee> smrNameEach = (model) -> {
            _CommentSummary smr = smrMap.get(model.getUserId());
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
    }

    // Get detailed comments for a specific recipient (Doctor or Staff)
    public List<CommentDetail> getCommentDetails(String recipientId, RecipientType type) {
        try {
            // Load required data
            List<Comment> comments = Db.Comment.select(-1, c -> true);
            List<Appointment> appts = Db.Appointment.select(-1, a -> true);
            Map<String, Appointment> apptMap = appts.stream()
                .collect(Collectors.toMap(Appointment::getAppointmentId, a -> a, (a,b)->a));

            List<Customer> customers = Db.Customer.select(-1, c -> true);
            Map<String, Customer> custMap = customers.stream()
                .collect(Collectors.toMap(Customer::getUserId, c -> c, (a,b)->a));

            // Resolve recipient name
            String recipientName = switch (type) {
                case Doctor -> {
                    List<Doctor> ls = Db.Doctor.select(1, d -> d.getUserId().equals(recipientId));
                    yield ls.isEmpty() ? "Unknown Doctor" : ls.get(0).getUserName();
                }
                case Staff -> {
                    List<Staff> ls = Db.Staff.select(1, s -> s.getUserId().equals(recipientId));
                    yield ls.isEmpty() ? "Unknown Staff" : ls.get(0).getUserName();
                }
            };

            // Collect details
            List<CommentDetail> output = new ArrayList<>();
            for (Comment c : comments) {
                Appointment a = apptMap.get(c.getAppointmentId());
                if (a == null) continue;

                // Check if this comment belongs to the recipient
                boolean match = switch (type) {
                    case Doctor -> recipientId.equals(a.getDoctorId());
                    case Staff  -> recipientId.equals(a.getStaffId());
                };
                if (!match) continue;

                // Resolve customer name
                Customer cust = custMap.get(a.getCustomerId());
                String custName = cust != null ? cust.getUserName() : "Unknown";

                output.add(new CommentDetail(
                    c.getCommentId(),
                    c.getAppointmentId(),
                    a.getCustomerId(),
                    custName,
                    recipientId,
                    recipientName,
                    type,
                    c.getRating(),
                    c.getContent(),
                    a.getDateTime().toLocalDate().toString()
                ));
            }

            // Sort by appointment date (latest first)
            output.sort((x, y) -> y.appointmentDate.compareTo(x.appointmentDate));
            return output;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}