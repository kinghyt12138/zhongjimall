package com.example.zhongjimall.service.sys.impl;

import com.example.zhongjimall.entity.sys.SysUserLoginLog;
import com.example.zhongjimall.repository.sys.SysUserLoginLogRepository;
import com.example.zhongjimall.service.sys.SysUserLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysUserLoginLogServiceImpl implements SysUserLoginLogService {
    @Autowired
    private SysUserLoginLogRepository loginRecordRepository;
    @Override
    public List<SysUserLoginLog> getLoginRecordList() {
        return loginRecordRepository.findAll();
    }
}
