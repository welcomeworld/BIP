package com.github.welcomeworld.bangumi.instrumentality.project.model;

import java.util.ArrayList;
import java.util.List;

public class CommentBean {
    public String commentId;
    public long commentLongId;
    public List<CommentDataBean> comments = new ArrayList<>();

    public static class CommentDataBean {
        public String commentDataId;
        public long commentDataLongId;
        public String upperName;
        public String comment;
        public String avatar;
        public long upTime;
        public List<CommentDataBean> subComment;
        public boolean subCommentExpand;
        public long subCommentCount;
        public int currentSubPage = 1;
        public int subCommentPageSize = 10;
        public int subCommentExpandSize = 3;
        public boolean subLoading;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CommentDataBean that = (CommentDataBean) o;
            return commentDataId.equals(that.commentDataId);
        }
    }
}
