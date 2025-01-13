package com.linjiasong.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linjiasong.user.entity.UserInfo;
import com.linjiasong.user.mapper.UserInfoMapper;
import com.linjiasong.user.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/13 下午4:34
 */

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
