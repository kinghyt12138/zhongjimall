package com.example.zhongjimall.entity.sysuser;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 权限实体  权限和角色存在多对多关系。一般情况下，权限不会和用户直接关联，它用于存放权限信息，
 * 比如权限的名称、HTTP方法、URL路径。具体见以下代码:
 */
@Data
@Entity
public class SysPermission implements Serializable {
    @Id
    @GeneratedValue
    /**
     * 主键
     *
     */
    private Integer id;
    /**
     * 名称.
     */
    private String name;
    @Column(columnDefinition = "enum('menu','button')")
    /**
     * 资源类型，[menu|button]
     */
    private String resourceType;
    /**
     * 资源路径
     */
    private String url;
    /**
     * 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
     */
    private String permission;
    /**
     * 父编号
     */
    private Long parentId;
    /**
     * 父编号列表
     */
    private String parentIds;
    private Boolean available = Boolean.FALSE;
    @Transient
    private List permissions;
    @ManyToMany
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "permissionId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roles;

    public List getPermissions() {
        return Arrays.asList(this.permission.trim().split("|"));
    }

    public void setPermissions(List permissions) {
        this.permissions = permissions;
    }
}
