package com.linjiasong.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linjiasong.user.constant.UserInfoContext;
import com.linjiasong.user.entity.UserLike;
import com.linjiasong.user.excepiton.BizException;
import com.linjiasong.user.excepiton.UserBaseResponse;
import com.linjiasong.user.gateway.UserGateway;
import com.linjiasong.user.gateway.UserLikeGateway;
import com.linjiasong.user.mapper.UserLikeMapper;
import com.linjiasong.user.service.UserLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/15 上午10:52
 */
@Service
@Slf4j
public class UserLikeServiceImpl extends ServiceImpl<UserLikeMapper, UserLike> implements UserLikeService {

    @Autowired
    private UserLikeGateway userLikeGateway;

    @Autowired
    private UserGateway userGateway;

    @Override
    public UserBaseResponse<?> like(Long likedId) {
        if (userGateway.selectById(likedId) == null) {
            throw new BizException("被关注用户不存在");
        }

        Long id = UserInfoContext.get().getId();

        if (userLikeGateway.selectByUserIdAndLikedId(id, likedId) != null) {
            if (!deleteLikeInfo(id, likedId)) {
                throw new RuntimeException("服务异常");
            }
            return UserBaseResponse.builder().code("200").msg("success").build();
        }

        if (!likeInsert(id, likedId, (short) 1)) {
            throw new RuntimeException("服务异常");
        }
        return UserBaseResponse.builder().code("200").msg("success").build();
    }

    @Override
    public UserBaseResponse<?> blackList(Long likedId) {
        if (userGateway.selectById(likedId) == null) {
            throw new BizException("被拉黑用户不存在");
        }

        Long id = UserInfoContext.get().getId();

        if (userLikeGateway.selectByUserIdAndLikedId(id, likedId) != null) {
            if (!deleteLikeInfo(id, likedId)) {
                throw new RuntimeException("服务异常");
            }
            return UserBaseResponse.builder().code("200").msg("success").build();
        }

        if (!likeInsert(id, likedId, (short) 2)) {
            throw new RuntimeException("服务异常");
        }
        return UserBaseResponse.builder().code("200").msg("success").build();
    }

    private boolean likeInsert(Long id, Long likedId, short type) {
        return userLikeGateway.insert(UserLike.builder()
                .userId(id)
                .beLikedId(likedId)
                .type(type)
                .build());
    }

    private boolean deleteLikeInfo(Long id, Long likedId) {
        return userLikeGateway.deleteByUserIdAndLikedId(id, likedId);
    }
}
