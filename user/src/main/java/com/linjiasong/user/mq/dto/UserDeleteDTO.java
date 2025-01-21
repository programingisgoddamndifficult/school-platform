package com.linjiasong.user.mq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linjiasong
 * @date 2025/1/21 下午3:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDeleteDTO {
    private Long userId;

    public static UserDeleteDTO build(Long userId){
        return UserDeleteDTO.builder().userId(userId).build();
    }
}
