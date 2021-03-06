package com.example.zhongjimall.service.member;

import com.example.zhongjimall.entity.member.User;
import com.example.zhongjimall.repository.member.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
//@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    	 User user = userRepository.findByName(name);
        if (user == null) {
            User mobileUser = userRepository.findByMobile(name);
            if (mobileUser == null) {
                User emailUser= userRepository.findByEmail(name);
                if(emailUser==null)
                {  throw new UsernameNotFoundException("用户名邮箱手机号不存在!");
                }
                else{
                    user=userRepository.findByEmail(name);

                }
            }
            else {
                  user = userRepository.findByMobile(name);
            }



        }
     /*   else if("locked".equals(user.getStatus())) { //被锁定，无法登录
            throw new LockedException("用户被锁定");
        }*/
        return user;

    }
}
