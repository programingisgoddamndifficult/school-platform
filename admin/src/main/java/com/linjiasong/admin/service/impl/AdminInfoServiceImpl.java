package com.linjiasong.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linjiasong.admin.entity.AdminInfo;
import com.linjiasong.admin.mapper.AdminInfoMapper;
import com.linjiasong.admin.service.AdminInfoService;
import org.springframework.stereotype.Service;

/**
 * @author linjiasong
 * @date 2025/1/17 下午2:30
 */
@Service
public class AdminInfoServiceImpl extends ServiceImpl<AdminInfoMapper, AdminInfo> implements AdminInfoService {
}
