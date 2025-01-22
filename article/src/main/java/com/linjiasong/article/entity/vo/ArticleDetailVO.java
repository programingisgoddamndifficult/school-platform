package com.linjiasong.article.entity.vo;

import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.entity.ArticleDetail;
import com.linjiasong.article.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/21 下午1:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDetailVO {

    private Long articleId;

    private String title;

    private Long likesNum;

    private Long collectNum;

    private Long readNum;

    private short tag;

    private String content;

    private List<String> imageUrl;

    private UserInfo userInfo;

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

    public static ArticleDetailVO build(ArticleBasicInfo basicInfo, ArticleDetail articleDetail, com.linjiasong.article.entity.UserInfo userInfo) {
        return ArticleDetailVO.builder()
                .articleId(basicInfo.getId())
                .likesNum(basicInfo.getLikesNum())
                .collectNum(basicInfo.getCollectNum())
                .readNum(basicInfo.getReadNum())
                .tag(basicInfo.getTag())
                .content(articleDetail.getContent())
                .imageUrl(ArticleDetail.getImageUrls(articleDetail.getImageUrl()))
                .userInfo(UserInfo.builder()
                        .userId(userInfo.getId())
                        .username(userInfo.getUsername())
                        .image(userInfo.getImage())
                        .build())
                .title(basicInfo.getArticleTitle())
                .createTime(basicInfo.getCreateTime())
                .updateTime(basicInfo.getUpdateTime())
                .build();
    }

}
