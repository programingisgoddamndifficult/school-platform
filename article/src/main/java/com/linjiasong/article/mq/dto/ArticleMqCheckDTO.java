package com.linjiasong.article.mq.dto;

import lombok.Data;

/**
 * @author linjiasong
 * @date 2025/1/23 下午6:46
 */
@Data
public class ArticleMqCheckDTO {
    private Long articleId;

    private boolean doBan;
}
