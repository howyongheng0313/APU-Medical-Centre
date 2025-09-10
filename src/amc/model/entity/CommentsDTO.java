package amc.model.entity;

public final class CommentsDTO {
    private CommentsDTO() {}

    // Defines the type of recipient that a comment can be addressed to
    public enum RecipientType { Doctor, Staff }

    // Represents an aggregated summary of comments for a specific recipient (Doctor or Staff)
    public static class CommentSummary {
        public final String recipientId;
        public final String recipientName;
        public final RecipientType recipientType;
        public final int commentCount;
        public final double averageRating;

        public CommentSummary(String recipientId, String recipientName, RecipientType recipientType, int commentCount, double averageRating) {
            this.recipientId = recipientId;
            this.recipientName = recipientName;
            this.recipientType = recipientType;
            this.commentCount = commentCount;
            this.averageRating = averageRating;
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
        public final RecipientType recipientType;
        public final Comment.Rating rating;
        public final String content;
        public final String appointmentDate;

        public CommentDetail(
            String commentId,
            String appointmentId,
            String customerId,
            String customerName,
            String recipientId,
            String recipientName,
            RecipientType recipientType,
            Comment.Rating rating,
            String content,
            String appointmentDate
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
        }
    }
}