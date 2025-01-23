package com.linjiasong.admin.mq.dto;

import com.alibaba.fastjson.JSON;
import com.linjiasong.admin.mq.enums.MqExchangeTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linjiasong
 * @date 2025/1/23 下午6:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MqBaseExchangeDTO {

    private MqExchangeTypeEnum exchangeType;

    private String jsonData;

    public static String objToString(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static MqBaseExchangeDTO build(MqExchangeTypeEnum exchangeType, Object obj) {
        return MqBaseExchangeDTO.builder()
                .exchangeType(exchangeType)
                .jsonData(objToString(obj))
                .build();
    }
}
