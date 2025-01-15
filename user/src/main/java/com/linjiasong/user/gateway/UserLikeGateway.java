package com.linjiasong.user.gateway;

import com.linjiasong.user.entity.UserLike;
import org.apache.ibatis.annotations.Param;

/**
 * @author linjiasong
 * @date 2025/1/15 上午10:53
 */
public interface UserLikeGateway {

    int getLikeNums(@Param("beLikedId") Long beLikedId);

    boolean insert(UserLike userLike);

    UserLike selectByUserIdAndLikedId(Long id, Long likedId);

    boolean deleteByUserIdAndLikedId(Long id, Long likedId);
}
