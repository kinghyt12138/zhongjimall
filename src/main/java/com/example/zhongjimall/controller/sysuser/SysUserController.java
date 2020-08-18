package com.example.zhongjimall.controller.sysuser;
import com.example.zhongjimall.entity.sysuser.SysRole;
import com.example.zhongjimall.entity.sysuser.SysUser;
import com.example.zhongjimall.repository.SysUser.SysRoleRepository;
import com.example.zhongjimall.repository.SysUser.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("admin")
public class SysUserController {


    @Autowired
    private SysUserRepository adminUserRepository;

    @Autowired
    private SysRoleRepository sysRoleRepository;


    //添加用户操作
    @RequestMapping("/user/add")
    public String toAddUser(Model model) {
        List<SysRole> adminrole = sysRoleRepository.findAll();
        model.addAttribute("adminrole", adminrole);
        return "admin/user/add";
    }



    @PostMapping("/user")
    public String addUser(String name, String password, String role) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(password);
//        String encodePassword =  MD5Util.encode(password);
        SysUser user = new SysUser(name, encodePassword);
        List<SysRole> roles = new ArrayList<>();
        SysRole role1 = sysRoleRepository.findByRole(role);
        roles.add(role1);
        user.setRoles(roles);
        adminUserRepository.save(user);
        return "redirect:/admin/";
    }

    @RequestMapping("/whoim")
    @ResponseBody
    public Object whoIm() {
        Set<String> urls = new HashSet<>();
        System.out.println(urls.toString());
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    @RequestMapping("/")
    public String index(HttpServletResponse response) {
        response.addHeader("x-frame-options", "SAMEORIGIN");
        return "admin/index";
    }


    //删除
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String del(@PathVariable("id") long id) {
        adminUserRepository.deleteById(id);
        return "redirect:";
    }

    //编辑
    @RequestMapping("/user/edit")
    public ModelAndView useredit(long id) {
        SysUser model = adminUserRepository.findById(id);
        ModelAndView mav = new ModelAndView("admin/user/edit");
        mav.addObject("adminuser", model);
        return mav;
    }

    /**
     * 更新操作
     *
     * @param model
     * @return
     */
    @RequestMapping("/user/update")
    public String userupdate(SysUser model) {
        adminUserRepository.save(model);

        return "redirect:";
    }

    //查找
    @RequestMapping("/user/{id}")
    public ModelAndView testPathVariable(@PathVariable("id") Integer id) {
        SysUser adminUser = adminUserRepository.findById(id);
        ModelAndView mav = new ModelAndView("sys/user/show");
        mav.addObject("adminUser", adminUser);
        return mav;
    }


    @RequestMapping(value = "/system/user/{id}", method = RequestMethod.PUT)
    public String categoryupdates(SysUser model) {
        String preUrl = "/";
        adminUserRepository.save(model);
        return "redirect:";

    }

    //显示用户列表 分页
    @GetMapping("/user")

    public ModelAndView userlist(@RequestParam(value = "start", defaultValue = "0") Integer start,
                                 @RequestParam(value = "limit", defaultValue = "5") Integer limit) {
        start = start < 0 ? 0 : start;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, limit, sort);

        Page<SysUser> page = adminUserRepository.findAll(pageable);

        ModelAndView mav = new ModelAndView("admin/user/list");
        mav.addObject("page", page);
        return mav;
    }


}
