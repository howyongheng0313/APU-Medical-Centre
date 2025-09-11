package amc.controller;

import amc.model.db_impl.Db;
import amc.model.entity.*;
import amc.model.entity.CommentsDTO.CommentDetail;
import amc.model.entity.CommentsDTO.CommentSummary;
import amc.model.entity.CommentsDTO.RecipientType;
import java.util.*;
import java.util.stream.Collectors;

```
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

```