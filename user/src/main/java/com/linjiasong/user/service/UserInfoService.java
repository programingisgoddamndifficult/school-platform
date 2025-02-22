package com.linjiasong.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linjiasong.user.entity.UserInfo;
import com.linjiasong.user.entity.dto.UserInfoDTO;
import com.linjiasong.user.entity.dto.UserInfoUpdateDTO;
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
    UserBaseResponse<?> signUp(UserInfoDTO userInfo);

    /**
     * 用户登陆
     * @param userLoginDTO dto
     * @return UserBaseResponse
     */
    UserBaseResponse<?> login(UserLoginDTO userLoginDTO, HttpServletResponse response);

    /**
     * 用户登出
     * @return UserBaseResponse
     */
    UserBaseResponse<?> loginOut();

    /**
     * 获取用户信息
     * @return UserBaseResponse
     */
    UserBaseResponse<?> getUserInfo();

    /**
     * 封禁用户
     * @param userId userId
     * @return UserBaseResponse
     */
    UserBaseResponse<?> banUser(Long userId);

    /**
     * 用户注销
     * @return UserBaseResponse
     */
    UserBaseResponse<?> userDelete();

    /**
     * TODO 用户更新信息
     * 用户信息更新
     * @param updateDTO UserInfoUpdateDTO
     * @return UserBaseResponse
     */
    UserBaseResponse<?> updateUserInfo(UserInfoUpdateDTO updateDTO);

}
