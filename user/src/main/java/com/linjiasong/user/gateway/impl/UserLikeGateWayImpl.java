package com.linjiasong.user.gateway.impl;

import com.linjiasong.user.gateway.UserLikeGateWay;
import com.linjiasong.user.mapper.UserLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/15 上午10:54
 */

@Service
public class UserLikeGateWayImpl implements UserLikeGateWay {

    @Autowired
    private UserLikeMapper userLikeMapper;

    @Override
    public int getLikeNums(Long beLikedId) {
        return userLikeMapper.getLikeNums(beLikedId);
    }
}
