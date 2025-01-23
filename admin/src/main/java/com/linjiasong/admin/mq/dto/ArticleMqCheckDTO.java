package com.linjiasong.admin.mq.dto;

import com.linjiasong.admin.entity.dto.ArticleCheckDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linjiasong
 * @date 2025/1/23 下午6:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleMqCheckDTO {
    private Long articleId;

    private boolean doBan;

    public static ArticleMqCheckDTO build(ArticleCheckDTO articleCheckDTO){
        return ArticleMqCheckDTO.builder().articleId(articleCheckDTO.getArticleId()).doBan(articleCheckDTO.isDoBan()).build();
    }
}
