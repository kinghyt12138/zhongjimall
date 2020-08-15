package com.example.zhongjimall.repository.SysUser;

import com.example.zhongjimall.entity.sysuser.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SysPermissionRepository extends JpaRepository<SysPermission, Long> {
    SysPermission findById(long id);
}

