package com.linjiasong.article.entity;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDetail {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long articleId;

    private String content;

    private String imageUrl;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ImageUrl {
        private List<String> imageUrls;
    }

    public static List<String> getImageUrls(String imageUrl) {
        return JSON.parseObject(imageUrl, ImageUrl.class).getImageUrls();
    }
}
