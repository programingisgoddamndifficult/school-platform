package com.linjiasong.user.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author linjiasong
 * @date 2025/2/17 下午8:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleCollectDTO {
    long current;

    long size;

    long total;

    private List<CollectData> collectDataList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CollectData {
        private Long id;

        private Long articleId;

        private Long userId;

        private String articleTitle;

        private short tag;

        private LocalDateTime collectTime;
    }
}
