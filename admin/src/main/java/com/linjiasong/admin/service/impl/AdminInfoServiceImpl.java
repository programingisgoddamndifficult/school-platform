package com.linjiasong.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linjiasong.admin.constant.AdminInfoContext;
import com.linjiasong.admin.entity.AdminInfo;
import com.linjiasong.admin.entity.dto.AdminCreateDTO;
import com.linjiasong.admin.entity.dto.AdminLoginDTO;
import com.linjiasong.admin.excepiton.AdminBaseResponse;
import com.linjiasong.admin.excepiton.BizException;
import com.linjiasong.admin.gateway.AdminGateway;
import com.linjiasong.admin.mapper.AdminInfoMapper;
import com.linjiasong.admin.service.AdminInfoService;
import com.linjiasong.admin.utils.TokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/17 下午2:30
 */
@Service
public class AdminInfoServiceImpl extends ServiceImpl<AdminInfoMapper, AdminInfo> implements AdminInfoService {

    @Autowired
    private AdminGateway adminGateway;

    @Override
    public AdminBaseResponse createAdmin(AdminCreateDTO adminCreateDTO) {
        if(!AdminInfoContext.get().getId().equals(1L)){
            throw new BizException("没有权限");
        }

        if (!adminCreateDTO.checkParam()) {
            throw new BizException("参数不合法");
        }

        if (adminGateway.selectOne(new QueryWrapper<AdminInfo>().eq("username", adminCreateDTO.getUsername())) != null) {
            throw new BizException("admin用户名已存在");
        }

        if (!adminGateway.insert(AdminInfo.builder().username(adminCreateDTO.getUsername()).password(adminCreateDTO.getPassword()).build())) {
            throw new BizException("服务异常");
        }

        return AdminBaseResponse.builder().code("200").msg("success").build();
    }

    @Override
    public AdminBaseResponse login(AdminLoginDTO adminLoginDTO, HttpServletResponse response) {
        if (!adminLoginDTO.checkParam()) {
            throw new BizException("参数不合法");
        }

        AdminInfo adminInfo = adminGateway.selectOne(new QueryWrapper<AdminInfo>().eq("username", adminLoginDTO.getUsername())
                .eq("password", adminLoginDTO.getPassword()));
        if (adminInfo == null) {
            throw new BizException("登陆失败，用户名或者密码不正确");
        }

        response.setHeader("Authorization", "Bearer " + TokenUtil.generateToken(JSON.toJSONString(adminInfo)));
        return AdminBaseResponse.builder().code("200").msg("success").build();
    }

}
