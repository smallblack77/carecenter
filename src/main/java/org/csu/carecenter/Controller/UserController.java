package org.csu.carecenter.Controller;


import org.csu.carecenter.entity.User;
import org.csu.carecenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/account")
@SessionAttributes("user")
public class UserController {

    @Autowired
    private UserService userService;

    //跳转到user登录界面
    @GetMapping("/userLoginForm")
    public String signonForm(Model model){
        return "account/userLogin";
    }

    //user登录
    @RequestMapping("/login")
    public String login(String userId,String password,Model model){
        if(userId != null && password != null){
            User user = userService.getUserByUserIdAndPassword(userId,password);
            if (user == null){
                String errorValue = "Invalid username or password.  Signon failed.";
                model.addAttribute("errorValue",errorValue);
                return "account/userLogin";
            }else {
                user.setPassword(null);
                model.addAttribute("user", user);
                return "account/index";
            }

      }else {
            return null;
        }
    }

    //跳转到注册页面
    @RequestMapping("/registrationForm")
    public String registrationFrom(Model model){
        model.addAttribute("newUser",new User());
        return "account/registration";
    }

    //user注册
    @RequestMapping("/registration")
    public String newUser(@ModelAttribute(value = "newUser")User user, HttpSession session,String repeatedPassword,Model model){
        if(user.getUserId()==null || user.getPassword()==null || user.getPassword().length()==0 || repeatedPassword == null || repeatedPassword.length()==0){
            String msg = "密码或用户名不能为空";
            session.setAttribute("msg",msg);
            return "redirect:/account/registrationForm";
        }else if(!user.getPhoneNumber().equals(repeatedPassword)){
            String msg = "两次密码不一致";
            session.setAttribute("msg",msg);
            return "redirect:/account/registrationForm";
        }else {
            userService.insertUser(user);
            int userId = userService.getMaxUserId();
            userService.insertSignon(user);
            session.setAttribute("userId",userId);
            model.addAttribute("user",user);
            return "account/index";
        }

    }

}

