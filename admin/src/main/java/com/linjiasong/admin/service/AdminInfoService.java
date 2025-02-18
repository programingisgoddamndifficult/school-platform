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

    /**
     * 创建管理员
     * @param adminCreateDTO adminCreateDTO
     * @return AdminBaseResponse
     */
    AdminBaseResponse createAdmin(AdminCreateDTO adminCreateDTO);

    /**
     * 登陆
     * @param adminLoginDTO adminLoginDTO
     * @param response response
     * @return AdminBaseResponse
     */
    AdminBaseResponse login(AdminLoginDTO adminLoginDTO, HttpServletResponse response);

    /**
     * 登出
     * @return AdminBaseResponse
     */
    AdminBaseResponse loginOut();

    /**
     * 是否超管
     * @return AdminBaseResponse
     */
    AdminBaseResponse isBigAdmin();

    /**
     * 获取管理员列表，仅超管使用
     * @param current current
     * @param size size
     * @return AdminBaseResponse
     */
    AdminBaseResponse getAdminList(int current, int size);
}
