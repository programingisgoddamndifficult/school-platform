package com.linjiasong.user.entity.vo;

import com.linjiasong.user.entity.dto.ArticleCollectDTO;
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
 * @date 2025/2/17 下午8:17
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

        private String articleTitle;

        private short tag;

        private LocalDateTime collectTime;

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

    public static ArticleCollectVO build(ArticleCollectDTO articleCollectDTO, Map<Long, com.linjiasong.user.entity.UserInfo> userMap) {
        return ArticleCollectVO.builder()
                .current(articleCollectDTO.getCurrent())
                .size(articleCollectDTO.getSize())
                .total(articleCollectDTO.getTotal())
                .collectDataList(ArticleCollectVO.build(articleCollectDTO.getCollectDataList(), userMap))
                .build();
    }

    public static List<CollectData> build(List<ArticleCollectDTO.CollectData> originCollectDataList, Map<Long, com.linjiasong.user.entity.UserInfo> userMap) {
        List<CollectData> resultCollectDataList = new ArrayList<>();

        originCollectDataList.forEach(collectData -> resultCollectDataList.add(ArticleCollectVO.build(collectData, userMap.get(collectData.getUserId()))));

        return resultCollectDataList;
    }

    public static CollectData build(ArticleCollectDTO.CollectData collectData, com.linjiasong.user.entity.UserInfo userInfo) {
        return CollectData.builder()
                .id(collectData.getId())
                .articleId(collectData.getArticleId())
                .articleTitle(collectData.getArticleTitle())
                .tag(collectData.getTag())
                .collectTime(collectData.getCollectTime())
                .userInfo(ArticleCollectVO.build(userInfo))
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
