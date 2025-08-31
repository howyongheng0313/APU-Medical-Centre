package amc.model.db_impl;

import amc.model.DbAdapter;
import amc.model.entity.Comment;
import java.util.List;

public class CommentAdapt extends DbAdapter<Comment> {
    protected CommentAdapt() {}

    @Override
    public Comment getMod(List<String> row) {
        Comment model = new Comment(
            row.get(0),
            row.get(1),
            row.get(2),
            Comment.Rating.valueOf(Integer.parseInt(row.get(3))),
            row.get(4)
        );
        return model;
    }

    @Override
    public List<String> getRow(Comment model) {
        List<String> row = List.of(
            model.getCommentId(),
            model.getAppointmentId(),
            model.getTargetId(),
            String.valueOf(model.getRating().getLevel()),
            model.getContent()
        );
        return row;
    }
}
