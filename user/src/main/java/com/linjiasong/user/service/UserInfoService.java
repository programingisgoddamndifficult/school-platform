package com.linjiasong.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linjiasong.user.entity.UserInfo;
import com.linjiasong.user.entity.dto.UserInfoDTO;
import com.linjiasong.user.entity.dto.UserLoginDTO;
import com.linjiasong.user.excepiton.UserBaseResponse;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author linjiasong
 * @date 2025/1/13 下午4:33
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 用户注册
     * @param userInfo 用户注册DTO
     * @return UserBaseResponse
     */
    UserBaseResponse signUp(UserInfoDTO userInfo);

    /**
     * 用户注册
     * @param userLoginDTO dto
     * @return UserBaseResponse
     */
    UserBaseResponse login(UserLoginDTO userLoginDTO, HttpServletResponse response);

    /**
     * 获取用户信息
     * @return UserBaseResponse
     */
    UserBaseResponse getUserInfo();
}
