package com.example.zhongjimall.repository.sys;


import com.example.zhongjimall.entity.sys.SysUserLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserLoginLogRepository extends JpaRepository<SysUserLoginLog,Long> {
    SysUserLoginLog findById(long id);


}
