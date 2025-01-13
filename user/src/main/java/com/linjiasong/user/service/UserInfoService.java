package com.linjiasong.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linjiasong.user.entity.UserInfo;
import com.linjiasong.user.entity.dto.UserInfoDTO;
import excepiton.UserBaseResponse;

/**
 * @author linjiasong
 * @date 2025/1/13 下午4:33
 */
public interface UserInfoService extends IService<UserInfo> {

    UserBaseResponse signUp(UserInfoDTO userInfo);

}
