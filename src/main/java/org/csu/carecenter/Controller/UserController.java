package org.csu.carecenter.Controller;


import org.csu.carecenter.entity.User;
import org.csu.carecenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


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
                String errorValue = "账户或密码有误！";
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
     //   model.addAttribute("newUser",new User());
        return "account/registration";
    }

    //user注册
    @RequestMapping("/registration")
    public String newUser(String username,String password,String sex,String email,String age,String phone,String role,
                          Model model,String repeatedPassword,HttpSession session) {
        User user = new User();
        if(username!=null && password!= null && sex!= null && email!=null && age!=null && phone!=null && role != null
                && repeatedPassword!=null){
            if(!repeatedPassword.equals(password)){
                String msg = "两次密码不一致";
                session.setAttribute("msg",msg);
            }else {
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setSex(sex);
                user.setAge(Integer.valueOf(age));
                user.setRole(role);
                user.setPhoneNumber(phone);
                userService.insertUser(user);
                int userId = userService.getMaxUserId();
                userService.insertSignon(user);
                model.addAttribute("user",user);
            }

        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/getAllUser")
    public List<User> manageUser(Model model){

        List<User> userList = userService.getAllUser();
        return userList;
    }

}

