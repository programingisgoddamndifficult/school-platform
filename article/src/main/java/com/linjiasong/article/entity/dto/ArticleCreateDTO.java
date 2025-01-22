package com.linjiasong.article.entity.dto;

import com.linjiasong.article.enums.ArticleTagEnum;
import lombok.Data;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/15 下午6:04
 */

@Data
public class ArticleCreateDTO {
    private String title;

    private short tag;

    private String context;

    private List<String> imageUrl;

    private short isOpen;

    public boolean checkParam() {
        if (this.title == null || this.title.isBlank() || this.title.length() > 50) {
            return false;
        }

        if (this.context == null || this.context.isBlank() || this.context.length() > 200) {
            return false;
        }

        if(isOpen != 1 && isOpen != 0){
            isOpen = 0;
        }

        return ArticleTagEnum.typeInvalid(this.tag);
    }
}
