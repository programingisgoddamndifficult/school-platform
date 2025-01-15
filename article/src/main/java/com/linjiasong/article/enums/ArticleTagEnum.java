package com.linjiasong.article.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author linjiasong
 * @date 2025/1/15 下午6:12
 */

@AllArgsConstructor
@Getter
public enum ArticleTagEnum {

    MAKE_FRIENDS("交友", (short) 1),
    IN_LOVA("恋爱", (short) 2),
    SECOND_HAND("二手", (short) 3),
    SUBSTITUTE("代课", (short) 4),
    QUESTION("提问", (short) 5),
    SHARE("分享", (short) 6),
    ;

    private final String tagDesc;

    private final short tagType;

    public static boolean typeInvalid(short type) {
        for (ArticleTagEnum tag : ArticleTagEnum.values()) {
            if (tag.getTagType() == type) {
                return false;
            }
        }
        return true;
    }
}
