package com.example.zhongjimall.service.SysUser;


import com.example.zhongjimall.entity.sysuser.SysUser;
import com.example.zhongjimall.repository.SysUser.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//@Service
public class SysSecurityService implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        SysUser user = sysUserRepository.findByName(name);
        if (user == null) {

            throw new UsernameNotFoundException("用户名不存在");

        } else if (!user.getEnabled()) { //被锁定，无法登录
            throw new LockedException("用户被锁定");
        }
        System.out.println(user.getEnabled());
        return user;
    }
}
