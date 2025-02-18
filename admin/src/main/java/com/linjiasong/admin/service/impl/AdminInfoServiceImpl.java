package com.linjiasong.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linjiasong.admin.constant.AdminInfoContext;
import com.linjiasong.admin.constant.RedisKeyEnum;
import com.linjiasong.admin.entity.AdminInfo;
import com.linjiasong.admin.entity.dto.AdminCreateDTO;
import com.linjiasong.admin.entity.dto.AdminLoginDTO;
import com.linjiasong.admin.entity.vo.AdminInfoListVo;
import com.linjiasong.admin.excepiton.AdminBaseResponse;
import com.linjiasong.admin.excepiton.BizException;
import com.linjiasong.admin.gateway.AdminGateway;
import com.linjiasong.admin.mapper.AdminInfoMapper;
import com.linjiasong.admin.service.AdminInfoService;
import com.linjiasong.admin.utils.TokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author linjiasong
 * @date 2025/1/17 下午2:30
 */
@Service
public class AdminInfoServiceImpl extends ServiceImpl<AdminInfoMapper, AdminInfo> implements AdminInfoService {

    @Autowired
    private AdminGateway adminGateway;

    @Autowired
    private RedissonClient redissonClient;

    private static final Long SUPER_ADMIN_ID = 1L;

    @Override
    public AdminBaseResponse createAdmin(AdminCreateDTO adminCreateDTO) {
        checkOnlySuperAdminCanDo();

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

        String token = "Bearer " + TokenUtil.generateToken(JSON.toJSONString(adminInfo));

        RBucket<String> bucket = redissonClient.getBucket(String.format(RedisKeyEnum.ADMIN_LOGIN.getKey(), adminInfo.getId()));
        if (bucket.isExists()) {
            bucket.delete();
        }
        bucket.set(token, RedisKeyEnum.ADMIN_LOGIN.getExpiryTime(), RedisKeyEnum.ADMIN_LOGIN.getTimeUnit());

        response.setHeader("Authorization", token);
        return AdminBaseResponse.builder().code("200").msg("success").build();
    }

    @Override
    public AdminBaseResponse loginOut() {
        RBucket<String> bucket = redissonClient.getBucket(String.format(RedisKeyEnum.ADMIN_LOGIN.getKey(), AdminInfoContext.get().getId()));
        bucket.delete();
        return AdminBaseResponse.success();
    }

    @Override
    public AdminBaseResponse isBigAdmin() {
        return AdminBaseResponse.success(Map.of("isBigAdmin", AdminInfoContext.get().getId().equals(SUPER_ADMIN_ID)));
    }

    @Override
    public AdminBaseResponse getAdminList(int current, int size) {
        checkOnlySuperAdminCanDo();

        return AdminBaseResponse.success(AdminInfoListVo.build(adminGateway.selectPage(new Page<>(current, size),
                new QueryWrapper<AdminInfo>().notIn("id", SUPER_ADMIN_ID).orderByAsc("id"))));
    }

    @Override
    public AdminBaseResponse deleteAdmin(Long adminId) {
        checkOnlySuperAdminCanDo();

        if(!adminGateway.deleteById(adminId)){
            throw new BizException("服务异常");
        }

        return AdminBaseResponse.success();
    }

    private void checkOnlySuperAdminCanDo(){
        if (!AdminInfoContext.get().getId().equals(SUPER_ADMIN_ID)) {
            throw new BizException("无权限");
        }
    }
}
