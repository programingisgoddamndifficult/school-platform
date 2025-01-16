package com.linjiasong.user.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/01/16 21:24
 */
@Data
public class ArticleUpdateDTO {
    private Long id;

    private Long userId;

    private String title;

    private short tag;

    private String context;

    private List<String> imageUrl;
}
