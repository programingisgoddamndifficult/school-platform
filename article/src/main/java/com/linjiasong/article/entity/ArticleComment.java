package com.linjiasong.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.linjiasong.article.constant.ArticleContext;
import com.linjiasong.article.entity.dto.ArticleCommentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleComment {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long articleId;

    private Long userId;

    private String comment;

    /**
     * 评论等级 目前仅一级
     */
    private short level;

    private LocalDateTime createTime;

    @TableLogic
    private short isDelete;

    public static ArticleComment build(ArticleCommentDTO articleCommentDTO){
        return ArticleComment.builder()
                .articleId(articleCommentDTO.getArticleId())
                .userId(ArticleContext.get().getId())
                .comment(articleCommentDTO.getComment())
                .build();
    }
}
