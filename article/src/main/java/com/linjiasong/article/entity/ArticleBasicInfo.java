package com.linjiasong.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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

    private Long readNum;

    /**
     * 文章分类
     */
    private short tag;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private short isCheck;

    private short isOpen;

    private short isBan;

    @TableLogic
    private short isDelete;

    public boolean isOpen(){
        return isOpen == 1;
    }

    public boolean isCheck(){
        return isCheck == 1;
    }

    public boolean isBan(){
        return isBan == 1;
    }
}
