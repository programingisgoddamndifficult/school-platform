package com.linjiasong.user.point.service.impl;

import com.linjiasong.user.config.SpringContext;
import com.linjiasong.user.point.config.PointConfig;
import com.linjiasong.user.point.service.PointService;
import com.linjiasong.user.point.enums.PointTypeEnum;
import com.linjiasong.user.point.service.point.AbstractPointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/22 上午10:26
 */
@Service
@Slf4j
public class PointServiceImpl implements PointService {

    @Override
    public void execute(Runnable task) {
        PointConfig.execute(task);
    }

    @Override
    public <T> void execute(PointTypeEnum pointType, T dto) {
        AbstractPointService pointService = SpringContext.getBean(pointType.getType(), AbstractPointService.class);
        pointService.point(pointType, dto);
    }
}
