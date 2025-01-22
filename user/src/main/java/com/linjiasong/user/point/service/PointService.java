package com.linjiasong.user.point.service;

import com.linjiasong.user.point.enums.PointTypeEnum;

/**
 * @author linjiasong
 * @date 2025/1/22 上午10:24
 */
public interface PointService {

    /**
     * execute
     * @param pointType pointType
     */
     <T> void execute(PointTypeEnum pointType, T dto);

    /**
     * execute
     * @param task task
     */
    void execute(Runnable task);

}
