package com.linjiasong.article.entity.vo;

import com.linjiasong.article.entity.ArticleComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author linjiasong
 * @date 2025/01/21 20:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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


    public static ArticleCommentVO build(List<ArticleComment> articleComments) {
        List<ArticleCommentInfo> articleCommentInfos = articleComments.stream().map(articleComment -> ArticleCommentInfo.builder()
                .id(articleComment.getId())
                .articleId(articleComment.getArticleId())
                .userId(articleComment.getUserId())
                .comment(articleComment.getComment())
                .level(articleComment.getLevel())
                .createTime(articleComment.getCreateTime())
                .build()).collect(Collectors.toList());

        return ArticleCommentVO.builder()
                .articleCommentInfoList(articleCommentInfos)
                .build();
    }
}
