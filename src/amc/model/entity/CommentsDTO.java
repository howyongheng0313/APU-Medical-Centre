package amc.model.entity;

public final class CommentsDTO {
    private CommentsDTO() {}

    // Defines the type of recipient that a comment can be addressed to
//    public enum RecipientType { Doctor, Staff }

    // Represents an aggregated summary of comments for a specific recipient (Doctor or Staff)
    public static class CommentSummary {
        public final String recipientId;
        public final Role   recipientType;
        private String recipientName = null;
        private int    commentCount  = 0;
        private int    totalRating   = 0;

        public CommentSummary(
            String recipientId,
            Role   recipientType
        ) {
            this.recipientId   = recipientId;
            this.recipientType = recipientType;
        }

        public String getRecipientName() { return recipientName; }
        public int    getCommentCount() { return commentCount; }
        public int    getTotalRating() { return totalRating; }
        public double getAverageRating() {
            return ((double) totalRating) / commentCount;
        }

        public void setRecipientName(String name) {
            if (recipientName == null) recipientName = name;
        }

        public void addCount(Comment.Rating rating) {
            totalRating += rating.getLevel();
            commentCount ++;
        }
    }

    // Represents a detailed view of a single comment
    public static class CommentDetail {
        public final String commentId;
        public final String appointmentId;
        public final String customerId;
        public final String customerName;
        public final String recipientId;
        public final String recipientName;
        public final Role   recipientType;
        public final Comment.Rating rating;
        public final String content;
        public final String appointmentDate;
        public final String doctorFeedback;

        public CommentDetail(
            String commentId,
            String appointmentId,
            String customerId,
            String customerName,
            String recipientId,
            String recipientName,
            Role   recipientType,
            Comment.Rating rating,
            String content,
            String appointmentDate,
            String doctorFeedback
        ){
            this.commentId = commentId;
            this.appointmentId = appointmentId;
            this.customerId = customerId;
            this.customerName = customerName;
            this.recipientId = recipientId;
            this.recipientName = recipientName;
            this.recipientType = recipientType;
            this.rating = rating;
            this.content = content;
            this.appointmentDate = appointmentDate;
            this.doctorFeedback = doctorFeedback;
        }
    }
}