package com.example.zhongjimall.controller.sysuser;

import com.example.zhongjimall.entity.sysuser.SysPermission;
import com.example.zhongjimall.entity.sysuser.SysRole;
import com.example.zhongjimall.repository.SysUser.SysPermissionRepository;
import com.example.zhongjimall.repository.SysUser.SysRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限控制器
 * 权限管理主要是对权限进行增加、删除、修改和查询操作
 * 全县要和角色对应起来 在进行操作时要附带角色字段
 */
@Controller
@RequestMapping("/admin/permission")
public class SysPermissionControler {
    @Autowired
    private SysPermissionRepository sysPermissionRepository;
    @Autowired
    private SysRoleRepository sysRoleRepository;

    @RequestMapping("/add")
    public String addPermission(Model model) {
        List<SysRole> sysRole = sysRoleRepository.findAll();
        model.addAttribute("sysRole", sysRole);
        return "admin/permission/add";
    }

    //发送请求的方式不同 处理的方法不一样
    @PostMapping("/add")
    public String addPermission(SysPermission sysPermission, String role) {
        List<SysRole> roles = new ArrayList<>();
        SysRole role1 = sysRoleRepository.findByRole(role);
        roles.add(role1);
        sysPermission.setRoles(roles);
        sysPermissionRepository.save(sysPermission);
        return "redirect:/admin/";
    }
}
