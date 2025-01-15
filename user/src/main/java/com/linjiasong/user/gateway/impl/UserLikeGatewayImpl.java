package com.linjiasong.user.gateway.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linjiasong.user.entity.UserLike;
import com.linjiasong.user.gateway.UserLikeGateway;
import com.linjiasong.user.mapper.UserLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/15 上午10:54
 */

@Service
public class UserLikeGatewayImpl implements UserLikeGateway {

    @Autowired
    private UserLikeMapper userLikeMapper;

    @Override
    public int getLikeNums(Long beLikedId) {
        return userLikeMapper.getLikeNums(beLikedId);
    }

    @Override
    public boolean insert(UserLike userLike) {
        return userLikeMapper.insert(userLike) > 0;
    }

    @Override
    public UserLike selectByUserIdAndLikedId(Long id, Long likedId) {
        return userLikeMapper.selectOne(new QueryWrapper<UserLike>().eq("user_id", id).eq("be_liked_id", likedId));
    }

    @Override
    public boolean deleteByUserIdAndLikedId(Long id, Long likedId) {
        return userLikeMapper.delete(new QueryWrapper<UserLike>().eq("user_id", id).eq("be_liked_id", likedId)) > 0;
    }
}
