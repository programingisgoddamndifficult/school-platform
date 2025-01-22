package com.linjiasong.user.point.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linjiasong
 * @date 2025/1/22 上午11:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointArticleDTO {
    private Long articleId;

    public static PointArticleDTO build(Long articleId) {
        return PointArticleDTO.builder().articleId(articleId).build();
    }
}
