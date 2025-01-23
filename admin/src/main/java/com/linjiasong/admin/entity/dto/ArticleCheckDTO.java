package com.linjiasong.admin.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linjiasong
 * @date 2025/1/23 下午5:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleCheckDTO {
    private Long articleId;

    private boolean doBan;
}
