package com.linjiasong.user.gateway;

import org.apache.ibatis.annotations.Param;

/**
 * @author linjiasong
 * @date 2025/1/15 上午10:53
 */
public interface UserLikeGateWay {

    int getLikeNums(@Param("beLikedId") Long beLikedId);

}
