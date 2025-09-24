package amc.model.entity;

import amc.model.DbWithId;
import amc.model.db_impl.Db;

public class Comment extends WithId {
    public enum Rating {
        Excellent(5),
        Good(4),
        Fair(3),
        Poor(2),
        Bad(1);

        private final int level;
        private Rating(int level) { this.level = level; }
        public static Rating valueOf(int level) {
            for (Rating r: Rating.values()) {
                if (r.getLevel() == level) return r;
            }
            return Rating.Fair;
        }
        public int getLevel() { return level; }
    }

    private String appointmentId;
    private String targetId;
    private Rating rating;
    private String content;

    private Appointment appointment = null;
    private Employee    target      = null;

    public Comment(
        String id,
        String appointmentId,
        String targetId,
        Rating rating,
        String content
    ) {
        super(id);
        this.appointmentId = appointmentId;
        this.targetId = targetId;
        this.rating = rating;
        this.content = content;
    }

    public String getAppointmentId() { return appointmentId; }
    public String getTargetId() { return targetId; }
    public Rating getRating() { return rating; }
    public String getContent() { return content; }

    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }
    public void setTargetId(String targetId) { this.targetId = targetId; }
    public void setRating(Rating rating) { this.rating = rating; }
    public void setContent(String content) { this.content = content; }

    public Appointment getAppointment() {
        if (appointment == null) appointment = Db.Appointment.getById(appointmentId);
        return appointment;
    }

    public Employee getTarget() {
        if (target == null) {
            DbWithId<? extends Employee> empDb = targetId.startsWith("DOC") ? Db.Doctor : Db.Staff;
            target = empDb.getById(targetId);
        }
        return target;
    }

    public void setAppointment(Appointment appointment) {
        if (appointmentId == null || !appointmentId.equals(appointment.getId())) return;
        this.appointment = appointment;
    }

    public void setTarget(Employee target) {
        if (targetId == null || !targetId.equals(target.getId())) return;
        this.target = target;
    }
}
