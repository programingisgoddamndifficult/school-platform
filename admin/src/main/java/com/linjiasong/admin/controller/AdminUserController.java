package com.linjiasong.admin.controller;

import com.linjiasong.admin.excepiton.AdminBaseResponse;
import com.linjiasong.admin.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linjiasong
 * @date 2025/01/19 15:25
 */

@RestController
@RequestMapping("/api/admin/user")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("/ban/{id}")
    public AdminBaseResponse banUser(@PathVariable("id") Long id) {
        return adminUserService.banUser(id);
    }

}
