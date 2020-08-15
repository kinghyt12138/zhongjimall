package com.example.zhongjimall.controller.sysuser;

import com.example.zhongjimall.entity.sysuser.SysRole;
import com.example.zhongjimall.repository.SysUser.SysRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 指定url映射和视图 见以下代码
 */
@Controller
@RequestMapping("admin")
public class SysRoleControlller {
    @Autowired
    private SysRoleRepository sysRoleRepository;

    //添加角色 所映射的页面
    @RequestMapping("/role/add")
    public String addRole() {
        return "admin/role/add";
    }

    @RequestMapping("/role")
    public String addRole(SysRole model) {
        String role = "ROLE_" + model.getRole();
        model.setRole(role);
        sysRoleRepository.save(model);
        return "redirect:/admin/";
    }

}
