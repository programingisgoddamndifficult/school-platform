package com.linjiasong.user.controller;

import com.linjiasong.user.excepiton.UserBaseResponse;
import com.linjiasong.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author linjiasong
 * @date 2025/01/19 15:09
 */
@RestController
@RequestMapping("/api/user/admin")
public class AdminUserController {

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/ban/{id}")
    public UserBaseResponse<?> banUser(@PathVariable("id") Long id) {
        return userInfoService.banUser(id);
    }

}
