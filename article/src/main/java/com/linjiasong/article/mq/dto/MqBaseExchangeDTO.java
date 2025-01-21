package com.linjiasong.article.mq.dto;

import com.alibaba.fastjson.JSON;
import com.linjiasong.article.mq.enums.MqExchangeTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author linjiasong
 * @date 2025/1/21 下午4:00
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

}
