package com.linjiasong.user.point.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author linjiasong
 * @date 2025/1/22 上午10:33
 */
@AllArgsConstructor
@Getter
public enum PointTypeEnum {

    POINT_ARTICLE("POINT_ARTICLE", "用户点击文章"),
    USER_ARTICLE_TAG("USER_ARTICLE_TAG", "用户点击的文章类型"),
    ARTICLE_LIKE("ARTICLE_LIKE", "最受欢迎文章"),
    ;

    private final String type;

    private final String desc;

}
