package com.example.zhongjimall.repository.SysUser;


import com.example.zhongjimall.entity.sysuser.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRoleRepository extends JpaRepository<SysRole,Long> {
	SysRole findByRole(String name);
}
