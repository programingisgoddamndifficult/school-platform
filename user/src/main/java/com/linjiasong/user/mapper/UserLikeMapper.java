package com.linjiasong.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linjiasong.user.entity.UserLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author linjiasong
 * @date 2025/1/15 上午10:51
 */
@Mapper
public interface UserLikeMapper extends BaseMapper<UserLike> {

    int getLikeNums(@Param("beLikedId") Long beLikedId);

}
