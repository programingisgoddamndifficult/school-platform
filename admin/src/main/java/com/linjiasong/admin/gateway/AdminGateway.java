package com.linjiasong.admin.gateway;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linjiasong.admin.entity.AdminInfo;

/**
 * @author linjiasong
 * @date 2025/1/17 下午2:39
 */
public interface AdminGateway {

    boolean insert(AdminInfo adminInfo);

    AdminInfo selectOne(QueryWrapper<AdminInfo> queryWrapper);

    Page<AdminInfo> selectPage(Page<AdminInfo> page, QueryWrapper<AdminInfo> queryWrapper);
}
