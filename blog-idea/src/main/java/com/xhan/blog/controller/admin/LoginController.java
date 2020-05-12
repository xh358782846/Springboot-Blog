package com.xhan.blog.controller.admin;


import com.xhan.blog.pojo.TUser;
import com.xhan.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private TUserMapper tUserMapper;

    @GetMapping(value = "toRegister")
    public String registerPage(){
        return "admin/register";
    }

    @PostMapping(value = "register")
    public String register( TUser user,RedirectAttributes redirectAttributes) {
        if (tUserMapper.selectByName(user.getUsername())!=null) {
            redirectAttributes.addFlashAttribute("message", "注册失败,用户名重复");
            return "redirect:toRegister";
        } else {
            userService.insert(user);
            return "admin/login";
        }
    }
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping(value = "login")
    public String login(@RequestParam String username, String password,
                        HttpSession session, RedirectAttributes redirectAttributes){
        TUser tUser=userService.checkUser(username,password);
        if (tUser!=null){
            tUser.setPassword(null);
            session.setAttribute("user",tUser);
            return "admin/index";
        }else {
            redirectAttributes.addFlashAttribute("message","用户名和密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
