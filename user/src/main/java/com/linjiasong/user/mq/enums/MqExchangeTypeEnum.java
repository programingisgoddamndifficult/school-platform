package com.linjiasong.user.mq.enums;

import com.linjiasong.user.mq.dto.UserDeleteDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author linjiasong
 * @date 2025/1/21 下午3:52
 */
@AllArgsConstructor
@Getter
public enum MqExchangeTypeEnum {

    USER_DELETE("USER_DELETE", UserDeleteDTO.class);

    private final String type;

    private final Class<?> clazz;

}
