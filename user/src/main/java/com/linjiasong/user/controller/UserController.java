package com.linjiasong.user.controller;

import com.linjiasong.user.entity.dto.UserInfoDTO;
import com.linjiasong.user.service.UserInfoService;
import excepiton.UserBaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linjiasong
 * @date 2025/1/13 下午6:10
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/signup")
    public UserBaseResponse signUp(UserInfoDTO userInfoDTO){
        return userInfoService.signUp(userInfoDTO);
    }

}
