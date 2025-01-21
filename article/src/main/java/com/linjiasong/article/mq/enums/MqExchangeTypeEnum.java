package com.linjiasong.article.mq.enums;

import com.linjiasong.article.mq.dto.UserDeleteDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author linjiasong
 * @date 2025/1/21 下午4:01
 */
@AllArgsConstructor
@Getter
public enum MqExchangeTypeEnum {

    USER_DELETE("USER_DELETE", UserDeleteDTO.class);

    private final String type;

    private final Class<?> clazz;

}
