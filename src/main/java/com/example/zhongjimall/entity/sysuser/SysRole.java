package com.example.zhongjimall.entity.sysuser;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 角色实体 角色是用户和权限的中间代理表。用户(user）和权限( permission)之间没有直接的关系，
 * 用户(user）需要通过角色作为代理（中间人）来获取拥有的权限，见以下代码:
 */
@Data
@Entity
public class SysRole {
    @Id
    @GeneratedValue
    /**
     *  编号
     */
    private Integer id;
    private String cnname;
    /**
     * 角色标识 程序中判断使用,如"sys",这个是唯一的
     */
    private String role;
    /**
     * 角色描述,UI界面显示使用
     */
    private String description;
    /**
     * 是否可用,如果不可用将不会添加给用户
     */
    private Boolean available = Boolean.FALSE;

    /**
     * 角色-权限关系：多对多关系;
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<SysPermission> permissions;
    /**
     * 用户-角色关系定义;
     */
    @ManyToMany
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "uid")})
    /**
     * 一个角色对应多个用户
     */
    private List<SysUser> userInfos;
}
