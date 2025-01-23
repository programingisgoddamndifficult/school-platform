package com.linjiasong.admin.mq.enums;

import com.linjiasong.admin.mq.dto.ArticleMqCheckDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author linjiasong
 * @date 2025/1/23 下午6:05
 */
@AllArgsConstructor
@Getter
public enum MqExchangeTypeEnum {

    ARTICLE_CHECK("ARTICLE_CHECK", ArticleMqCheckDTO.class);

    private final String type;

    private final Class<?> clazz;

}
