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
 * @date 2025/1/24 上午10:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleUserWatch {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long articleId;

    private Long userId;

    private short tag;

    private LocalDateTime watchTime;
}
