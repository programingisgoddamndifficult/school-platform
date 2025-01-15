package com.linjiasong.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linjiasong
 * @date 2025/1/15 下午5:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleComment {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long articleId;

    private Long userId;

    private String comment;

    /**
     * 评论的等级，最多二级
     */
    private short level;

    private short is_delete;
}
