package com.linjiasong.article.entity.vo;

import com.linjiasong.article.entity.ArticleUserWatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author linjiasong
 * @date 2025/1/24 上午11:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleUserWatchVO {
    private Long id;

    private Long articleId;

    private Long userId;

    private String articleTitle;

    private short tag;

    private LocalDateTime watchTime;

    public static ArticleUserWatchVO build(ArticleUserWatch articleUserWatch, String title) {
        return ArticleUserWatchVO.builder()
                .id(articleUserWatch.getId())
                .articleId(articleUserWatch.getArticleId())
                .userId(articleUserWatch.getUserId())
                .articleTitle(title)
                .tag(articleUserWatch.getTag())
                .watchTime(articleUserWatch.getWatchTime())
                .build();
    }
}
