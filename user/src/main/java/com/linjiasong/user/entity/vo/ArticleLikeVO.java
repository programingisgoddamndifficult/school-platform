package com.linjiasong.user.entity.vo;

import com.linjiasong.user.entity.dto.ArticleLikeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author linjiasong
 * @date 2025/2/17 下午7:36
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

        private String articleTitle;

        private short tag;

        private LocalDateTime likeTime;

        private UserInfo userInfo;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserInfo {
        private Long id;

        private String username;

        private String image;
    }

    public static ArticleLikeVO build(ArticleLikeDTO articleLikeDTO, Map<Long, com.linjiasong.user.entity.UserInfo> userMap) {
        return ArticleLikeVO.builder()
                .current(articleLikeDTO.getCurrent())
                .size(articleLikeDTO.getSize())
                .total(articleLikeDTO.getTotal())
                .likeDataList(ArticleLikeVO.build(articleLikeDTO.getLikeDataList(), userMap))
                .build();
    }

    public static List<LikeData> build(List<ArticleLikeDTO.LikeData> originLikeDataList, Map<Long, com.linjiasong.user.entity.UserInfo> userMap) {
        List<LikeData> resultLikeDataList = new ArrayList<>();

        originLikeDataList.forEach(likeData -> resultLikeDataList.add(ArticleLikeVO.build(likeData, userMap.get(likeData.getUserId()))));

        return resultLikeDataList;
    }

    public static LikeData build(ArticleLikeDTO.LikeData likeData, com.linjiasong.user.entity.UserInfo userInfo) {
        return LikeData.builder()
                .id(likeData.getId())
                .articleId(likeData.getArticleId())
                .articleTitle(likeData.getArticleTitle())
                .tag(likeData.getTag())
                .likeTime(likeData.getLikeTime())
                .userInfo(ArticleLikeVO.build(userInfo))
                .build();
    }

    public static UserInfo build(com.linjiasong.user.entity.UserInfo userInfo) {
        return UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .image(userInfo.getImage())
                .build();
    }
}
