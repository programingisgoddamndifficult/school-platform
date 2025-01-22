package com.linjiasong.user.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/01/16 21:01
 */
@Data
public class ArticleCreateDTO {
    private String title;

    private short tag;

    private String context;

    private List<String> imageUrl;

    private short isOpen;
}
