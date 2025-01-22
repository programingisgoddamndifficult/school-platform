package com.linjiasong.user.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/22 下午5:13
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
}
