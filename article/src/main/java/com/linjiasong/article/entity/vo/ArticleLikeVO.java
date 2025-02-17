package com.linjiasong.article.entity.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.entity.ArticleLike;
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
 * @date 2025/2/17 下午7:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleLikeVO {

    long current;

    long size;

    long total;

    private List<LikeData> likeDataList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class LikeData {
        private Long id;

        private Long articleId;

        private Long userId;

        private String articleTitle;

        private short tag;

        private LocalDateTime likeTime;
    }

    public static ArticleLikeVO build(List<ArticleBasicInfo> articleBasicInfoList, Page<ArticleLike> page) {
        return ArticleLikeVO.builder()
                .current(page.getCurrent())
                .size(page.getSize())
                .total(page.getTotal())
                .likeDataList(buildLikeDataList(articleBasicInfoList, page.getRecords()))
                .build();
    }

    public static List<LikeData> buildLikeDataList(List<ArticleBasicInfo> articleBasicInfoList, List<ArticleLike> articleLikes) {
        /**
         * articleLikeMap key->articleId  value->ArticleLike
         */
        Map<Long, ArticleLike> articleLikeMap = articleLikes.stream().collect(Collectors.toMap(ArticleLike::getArticleId, articleLike -> articleLike));

        /**
         * articleBasicInfoMap key->articleId  value->ArticleBasicInfo
         */
        Map<Long, ArticleBasicInfo> articleBasicInfoMap = articleBasicInfoList.stream().collect(Collectors.toMap(ArticleBasicInfo::getId, articleBasicInfo -> articleBasicInfo));

        List<LikeData> likeDataList = new ArrayList<>();
        articleLikeMap.forEach((articleId, articleLike) -> likeDataList.add(build(articleBasicInfoMap.get(articleId), articleLike)));

        return likeDataList;
    }

    public static LikeData build(ArticleBasicInfo articleBasicInfo, ArticleLike articleLike) {
        return LikeData.builder()
                .id(articleLike.getId())
                .articleId(articleBasicInfo.getId())
                .userId(articleBasicInfo.getUserId())
                .articleTitle(articleBasicInfo.getArticleTitle())
                .tag(articleBasicInfo.getTag())
                .likeTime(articleLike.getLikeTime())
                .build();
    }

}
