package com.linjiasong.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linjiasong.user.entity.UserLike;
import com.linjiasong.user.excepiton.UserBaseResponse;

/**
 * @author linjiasong
 * @date 2025/1/15 上午10:51
 */
public interface UserLikeService extends IService<UserLike> {

    /**
     * 关注
     *
     * @param likedId 被关注人id
     * @return UserBaseResponse
     */
    UserBaseResponse like(Long likedId);

    /**
     * 拉黑
     *
     * @param likedId 被拉黑人id
     * @return UserBaseResponse
     */
    UserBaseResponse blackList(Long likedId);
}
