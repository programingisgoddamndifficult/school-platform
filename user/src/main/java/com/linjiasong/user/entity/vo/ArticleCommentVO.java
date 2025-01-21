package com.linjiasong.user.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author linjiasong
 * @date 2025/01/21 20:32
 */
@Data
public class ArticleCommentVO {
    private List<ArticleCommentInfo> articleCommentInfoList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ArticleCommentInfo {
        private Long id;

        private Long articleId;

        private Long userId;

        private String comment;

        private short level;

        private LocalDateTime createTime;
    }
}
