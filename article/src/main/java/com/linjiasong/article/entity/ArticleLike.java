package com.linjiasong.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleLike {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long articleId;

    private Long userId;

    private LocalDateTime likeTime;

    public static ArticleLike build(Long articleId, Long userId){
        return ArticleLike.builder().articleId(articleId).userId(userId).build();
    }
}
