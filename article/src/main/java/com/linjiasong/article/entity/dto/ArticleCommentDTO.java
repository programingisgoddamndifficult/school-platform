package com.linjiasong.article.entity.dto;

import lombok.Data;

/**
 * @author linjiasong
 * @date 2025/1/21 下午5:54
 */
@Data
public class ArticleCommentDTO {

    private Long articleId;

    private String comment;

    /**
     * 评论等级 最多二级
     */
    private short level;

}
