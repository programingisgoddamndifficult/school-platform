package com.linjiasong.user.point.service.point;

import com.linjiasong.user.point.service.PointService;
import com.linjiasong.user.point.service.enums.PointTypeEnum;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/22 上午10:53
 */
@Service
public abstract class AbstractPointService {

    @Autowired
    protected PointService pointService;

    @Autowired
    protected RedissonClient redissonClient;

    abstract public <T> void point(PointTypeEnum pointTypeEnum, T dto);
}
