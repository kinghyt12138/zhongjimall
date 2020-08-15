package com.example.zhongjimall.service.sys.impl;


import com.example.zhongjimall.entity.sys.MemberLoginLog;
import com.example.zhongjimall.repository.sys.MemberLoginLogRepository;
import com.example.zhongjimall.service.sys.MemberLonginLogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class MemberLoginLogServiceImpl implements MemberLonginLogService {
    @Autowired
    private MemberLoginLogRepository memberLoginLogRepository;
    @Override
    public List<MemberLoginLog> getMemberLoginLogList() {
        return memberLoginLogRepository.findAll();
    }
}
