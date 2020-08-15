package com.example.zhongjimall.service.sys;

import com.example.zhongjimall.entity.sys.SysUserLoginLog;

import java.util.List;

public interface SysUserLoginLogService {
    public List<SysUserLoginLog> getLoginRecordList();
}
