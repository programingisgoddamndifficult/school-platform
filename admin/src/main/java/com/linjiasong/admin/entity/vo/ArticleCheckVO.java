package com.linjiasong.admin.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/23 下午5:30
 */
@Data
public class ArticleCheckVO {
    private Long id;

    private String title;

    private short tag;

    private String context;

    private List<String> imageUrl;

    private short isOpen;
}
