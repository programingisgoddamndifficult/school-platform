package com.linjiasong.article.entity.vo;

import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.entity.ArticleDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author linjiasong
 * @date 2025/2/14 下午6:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleSelfDetailVO {
    private Long articleId;

    private String title;

    private Long likesNum;

    private Long collectNum;

    private Long readNum;

    private short tag;

    private String content;

    private short isBan;

    private short isCheck;

    private short isOpen;

    private List<String> imageUrl;

    private ArticleDetailVO.UserInfo userInfo;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserInfo {
        private Long userId;

        private String username;

        private String image;
    }

    public static ArticleSelfDetailVO build(ArticleBasicInfo basicInfo, ArticleDetail articleDetail) {
        return ArticleSelfDetailVO.builder()
                .articleId(basicInfo.getId())
                .likesNum(basicInfo.getLikesNum())
                .collectNum(basicInfo.getCollectNum())
                .readNum(basicInfo.getReadNum())
                .tag(basicInfo.getTag())
                .content(articleDetail.getContent())
                .imageUrl(ArticleDetail.getImageUrls(articleDetail.getImageUrl()))
                .userInfo(ArticleDetailVO.UserInfo.builder()
                        .userId(basicInfo.getUserId())
                        .build())
                .isBan(basicInfo.getIsBan())
                .isCheck(basicInfo.getIsCheck())
                .isOpen(basicInfo.getIsOpen())
                .title(basicInfo.getArticleTitle())
                .createTime(basicInfo.getCreateTime())
                .updateTime(basicInfo.getUpdateTime())
                .build();
    }
}
