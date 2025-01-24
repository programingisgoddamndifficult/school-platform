package com.linjiasong.article.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/24 下午2:34
 */
@Data
public class ArticleDeleteUserWatchDTO {
    private List<Long> ids;

    public boolean hasData() {
        return ids != null && !ids.isEmpty();
    }
}
