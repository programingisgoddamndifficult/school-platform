package com.linjiasong.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linjiasong.admin.entity.AdminInfo;
import com.linjiasong.admin.entity.dto.AdminCreateDTO;
import com.linjiasong.admin.entity.dto.AdminLoginDTO;
import com.linjiasong.admin.excepiton.AdminBaseResponse;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author linjiasong
 * @date 2025/1/17 下午2:30
 */
public interface AdminInfoService extends IService<AdminInfo> {

    AdminBaseResponse createAdmin(AdminCreateDTO adminCreateDTO);

    AdminBaseResponse login(AdminLoginDTO adminLoginDTO, HttpServletResponse response);
}
