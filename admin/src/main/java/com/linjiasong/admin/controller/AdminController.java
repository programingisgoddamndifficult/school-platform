package com.linjiasong.admin.controller;

import com.linjiasong.admin.entity.AdminInfo;
import com.linjiasong.admin.entity.dto.AdminCreateDTO;
import com.linjiasong.admin.entity.dto.AdminLoginDTO;
import com.linjiasong.admin.excepiton.AdminBaseResponse;
import com.linjiasong.admin.service.AdminInfoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linjiasong
 * @date 2025/1/17 下午2:47
 */

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminInfoService adminInfoService;

    @PostMapping("/login")
    public AdminBaseResponse login(@RequestBody AdminLoginDTO loginDTO, HttpServletResponse response) {
        return adminInfoService.login(loginDTO, response);
    }

    @PostMapping
    public AdminBaseResponse createAdmin(@RequestBody AdminCreateDTO adminCreateDTO) {
        return adminInfoService.createAdmin(adminCreateDTO);
    }

}
