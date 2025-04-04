package com.linjiasong.user.controller;

import com.linjiasong.user.entity.dto.UserInfoDTO;
import com.linjiasong.user.entity.dto.UserInfoUpdateDTO;
import com.linjiasong.user.entity.dto.UserLoginDTO;
import com.linjiasong.user.excepiton.UserBaseResponse;
import com.linjiasong.user.service.UserInfoService;
import com.linjiasong.user.service.UserLikeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author linjiasong
 * @date 2025/1/13 下午6:10
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserLikeService userLikeService;

    @PostMapping("/signup")
    public UserBaseResponse<?> signUp(@RequestBody UserInfoDTO userInfoDTO) {
        return userInfoService.signUp(userInfoDTO);
    }

    @PostMapping("/login")
    public UserBaseResponse<?> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response) {
        return userInfoService.login(userLoginDTO, response);
    }

    @PostMapping("/loginout")
    public UserBaseResponse<?> loginOut() {
        return userInfoService.loginOut();
    }

    @GetMapping
    public UserBaseResponse<?> getUserInfo() {
        return userInfoService.getUserInfo();
    }

    @PostMapping("/like/{id}")
    public UserBaseResponse<?> like(@PathVariable("id") Long id) {
        return userLikeService.like(id);
    }

    @PostMapping("/black/{id}")
    public UserBaseResponse<?> black(@PathVariable("id") Long id) {
        return userLikeService.blackList(id);
    }

    @PostMapping("/delete")
    public UserBaseResponse<?> delete() {
        return userInfoService.userDelete();
    }

    @PostMapping("/update")
    public UserBaseResponse<?> update(@RequestBody UserInfoUpdateDTO updateDTO) {
        return userInfoService.updateUserInfo(updateDTO);
    }

    @PostMapping("/update/pwd")
    public UserBaseResponse<?> update(@RequestBody Map<String, String> map) {
        return userInfoService.updatePassword(map);
    }
}
