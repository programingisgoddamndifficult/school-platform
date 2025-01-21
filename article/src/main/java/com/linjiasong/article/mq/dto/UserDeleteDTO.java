package com.linjiasong.article.mq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linjiasong
 * @date 2025/1/21 下午4:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDeleteDTO {
    private Long userId;
}
