package com.linjiasong.article.entity.vo;

import com.linjiasong.article.entity.ArticleBasicInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author linjiasong
 * @date 2025/01/15 21:05
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleBasicVO {
    private Long id;

    private String title;

    private Long likesNum;

    private Long collectNum;

    private Long readNum;

    private short tag;

    private short isOpen;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public static List<ArticleBasicVO> build(List<ArticleBasicInfo> articleBasicInfos) {
        return articleBasicInfos.stream().map(basic -> ArticleBasicVO.builder()
                .id(basic.getId())
                .title(basic.getArticleTitle())
                .likesNum(basic.getLikesNum())
                .collectNum(basic.getCollectNum())
                .readNum(basic.getReadNum())
                .tag(basic.getTag())
                .isOpen(basic.getIsOpen())
                .createTime(basic.getCreateTime())
                .updateTime(basic.getUpdateTime()).build()).collect(Collectors.toList());
    }
}
