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
 * @date 2025/1/15 下午5:03
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleBasicInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String articleTitle;

    private Long userId;

    private Long likesNum;

    private Long collectNum;

    /**
     * 文章分类
     */
    private int tag;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private short isBan;

    private short isDelete;
}
