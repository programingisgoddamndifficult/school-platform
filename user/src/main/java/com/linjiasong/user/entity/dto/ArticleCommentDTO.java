package com.linjiasong.user.entity.dto;

import lombok.Data;

/**
 * @author linjiasong
 * @date 2025/1/21 下午6:10
 */
@Data
public class ArticleCommentDTO {

    private Long articleId;

    private String comment;

    /**
     * 评论等级 目前仅一级
     */
    private short level;

}