package com.linjiasong.user.entity.dto;

import lombok.Data;

/**
 * @author linjiasong
 * @date 2025/02/10 17:13
 */
@Data
public class ArticlePageSelectDTO {
    private short tag;

    /**
     * 0默认 1esc 2desc
     */
    private int like;

    private int collect;

    private int read;

    private int current;

    private int size;

    private Condition condition;

    @Data
    public static class Condition {
        private String title;
    }
}
