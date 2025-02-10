package com.linjiasong.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linjiasong
 * @date 2025/02/10 16:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleUserRecommend {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long bigArticleId;
}
