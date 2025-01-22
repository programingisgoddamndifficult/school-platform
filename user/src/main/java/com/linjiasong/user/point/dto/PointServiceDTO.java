package com.linjiasong.user.point.dto;

import com.linjiasong.user.point.service.enums.PointTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linjiasong
 * @date 2025/1/22 上午10:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointServiceDTO {
    private PointTypeEnum pointType;

    private Runnable task;

    public static PointServiceDTO build(PointTypeEnum pointType, Runnable task) {
        return PointServiceDTO.builder().pointType(pointType).task(task).build();
    }
}
