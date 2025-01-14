package com.linjiasong.user.controller;

import com.linjiasong.user.entity.dto.UserInfoDTO;
import com.linjiasong.user.entity.dto.UserLoginDTO;
import com.linjiasong.user.excepiton.UserBaseResponse;
import com.linjiasong.user.service.UserInfoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public UserBaseResponse signUp(@RequestBody UserInfoDTO userInfoDTO){
        return userInfoService.signUp(userInfoDTO);
    }

    @PostMapping("/login")
    public UserBaseResponse login(@RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response){
        return userInfoService.login(userLoginDTO, response);
    }

}
