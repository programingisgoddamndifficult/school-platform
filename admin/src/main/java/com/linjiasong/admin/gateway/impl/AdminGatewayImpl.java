package com.linjiasong.admin.gateway.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linjiasong.admin.entity.AdminInfo;
import com.linjiasong.admin.gateway.AdminGateway;
import com.linjiasong.admin.mapper.AdminInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/17 下午2:39
 */
@Service
public class AdminGatewayImpl implements AdminGateway {

    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @Override
    public boolean insert(AdminInfo adminInfo) {
        return adminInfoMapper.insert(adminInfo) > 0;
    }

    @Override
    public AdminInfo selectOne(QueryWrapper<AdminInfo> queryWrapper) {
        return adminInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public Page<AdminInfo> selectPage(Page<AdminInfo> page, QueryWrapper<AdminInfo> queryWrapper) {
        return adminInfoMapper.selectPage(page, queryWrapper);
    }
}
