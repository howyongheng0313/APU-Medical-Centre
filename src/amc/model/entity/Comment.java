package amc.model.entity;

public class Comment {
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

    private String commentId;
    private String appointmentId;
    private String targetId;
    private Rating rating;
    private String content;

    public Comment(
        String commentId,
        String appointmentId,
        String targetId,
        Rating rating,
        String content) {
        this.commentId = commentId;
        this.appointmentId = appointmentId;
        this.targetId = targetId;
        this.rating = rating;
        this.content = content;
    }

    public String getCommentId() { return commentId; }
    public String getAppointmentId() { return appointmentId; }
    public String getTargetId() { return targetId; }
    public Rating getRating() { return rating; }
    public String getContent() { return content; }

    public void setCommentId(String commentId) { this.commentId = commentId; }
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }
    public void setTargetId(String targetId) { this.targetId = targetId; }
    public void setRating(Rating rating) { this.rating = rating; }
    public void setContent(String content) { this.content = content; }
}
