package com.linjiasong.user.entity.vo;

import com.linjiasong.user.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author linjiasong
 * @date 2025/01/21 20:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleUserCommentVO {

    private ArticleCommentInfo articleCommentInfo;

    private UserCommentInfo userCommentInfo;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ArticleCommentInfo {
        private Long id;

        private Long articleId;

        private Long userId;

        private String comment;

        private short level;

        private LocalDateTime createTime;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserCommentInfo {
        private Long userId;

        private String username;

        private String image;
    }

    public static List<ArticleUserCommentVO> build(List<ArticleCommentVO.ArticleCommentInfo> articleCommentList, List<UserInfo> userInfos) {
        Map<Long, UserInfo> userInfoMap = userInfos.stream().collect(Collectors.toMap(UserInfo::getId, userInfo -> userInfo));

        return articleCommentList.stream().map(articleCommentVO -> {
            UserInfo userInfo = userInfoMap.get(articleCommentVO.getUserId());
            return ArticleUserCommentVO.builder()
                    .articleCommentInfo(ArticleCommentInfo.builder()
                            .id(articleCommentVO.getId())
                            .articleId(articleCommentVO.getArticleId())
                            .userId(articleCommentVO.getUserId())
                            .comment(articleCommentVO.getComment())
                            .level(articleCommentVO.getLevel())
                            .createTime(articleCommentVO.getCreateTime())
                            .build())
                    .userCommentInfo(UserCommentInfo.builder()
                            .userId(userInfo.getId())
                            .username(userInfo.getUsername())
                            .image(userInfo.getImage())
                            .build())
                    .build();
        }).collect(Collectors.toList());
    }

}
