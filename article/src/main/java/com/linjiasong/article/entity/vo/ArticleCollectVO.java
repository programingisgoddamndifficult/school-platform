package com.linjiasong.article.entity.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.entity.ArticleCollect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author linjiasong
 * @date 2025/2/17 下午8:10
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleCollectVO {
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

    public static ArticleCollectVO build(List<ArticleBasicInfo> articleBasicInfoList, Page<ArticleCollect> page) {
        return ArticleCollectVO.builder()
                .current(page.getCurrent())
                .size(page.getSize())
                .total(page.getTotal())
                .collectDataList(buildCollectDataList(articleBasicInfoList, page.getRecords()))
                .build();
    }

    public static List<ArticleCollectVO.CollectData> buildCollectDataList(List<ArticleBasicInfo> articleBasicInfoList, List<ArticleCollect> articleCollects) {
        /**
         * articleCollectMap key->articleId  value->ArticleCollect
         */
        Map<Long, ArticleCollect> articleCollectMap = articleCollects.stream().collect(Collectors.toMap(ArticleCollect::getArticleId, articleCollect -> articleCollect));

        /**
         * articleBasicInfoMap key->articleId  value->ArticleBasicInfo
         */
        Map<Long, ArticleBasicInfo> articleBasicInfoMap = articleBasicInfoList.stream().collect(Collectors.toMap(ArticleBasicInfo::getId, articleBasicInfo -> articleBasicInfo));

        List<ArticleCollectVO.CollectData> collectDataList = new ArrayList<>();
        articleCollectMap.forEach((articleId, articleCollect) -> collectDataList.add(build(articleBasicInfoMap.get(articleId), articleCollect)));

        return collectDataList;
    }

    public static ArticleCollectVO.CollectData build(ArticleBasicInfo articleBasicInfo, ArticleCollect articleCollect) {
        return ArticleCollectVO.CollectData.builder()
                .id(articleCollect.getId())
                .articleId(articleBasicInfo.getId())
                .userId(articleBasicInfo.getUserId())
                .articleTitle(articleBasicInfo.getArticleTitle())
                .tag(articleBasicInfo.getTag())
                .collectTime(articleCollect.getCollectTime())
                .build();
    }
}
