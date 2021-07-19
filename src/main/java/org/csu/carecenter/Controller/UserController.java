package org.csu.carecenter.Controller;


import org.csu.carecenter.entity.User;
import org.csu.carecenter.service.CustomerService;
import org.csu.carecenter.service.NurseContentService;
import org.csu.carecenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/account")
@SessionAttributes(value = {"user"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private NurseContentService nurseContentService;

    //跳转到user登录界面
    @GetMapping("/userLoginForm")
    public String signonForm(Model model){
        return "account/userLogin";
    }

    //跳转到主页界面
    @GetMapping("/main")
    public String mainPage(Model model){
        int count = customerService.getCount();
        int nurCount = nurseContentService.getCount();
        model.addAttribute("count",count);
        model.addAttribute("nurCount",nurCount);
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat formatter2= new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        model.addAttribute("date",formatter.format(date));
        model.addAttribute("calendar",formatter2.format(date));
        return "account/index";
    }

    //user登录
    @RequestMapping("/login")
    public String login(Integer userId,String password,Model model,HttpSession session){
        if(String.valueOf(userId) != null && password != null){
            SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat formatter2= new SimpleDateFormat("yyyy:MM:dd");
            Date date = new Date(System.currentTimeMillis());
            User user = userService.getUserByUserIdAndPassword(userId,password);
            int count = customerService.getCount();
            int nurCount = nurseContentService.getCount();
            if (user == null){
                String errorValue = "账户或密码有误！";
                model.addAttribute("errorValue",errorValue);
                return "account/userLogin";
            }else {
             //   user.setPassword(null);
                model.addAttribute("user", user);
                model.addAttribute("count", count);
                model.addAttribute("nurCount",nurCount);
                model.addAttribute("date",formatter.format(date));
                model.addAttribute("calendar",formatter2.format(date));
                session.setAttribute("user",user);
                return "account/index";
            }
        }else {
            String errorValue = "输出不能为空！";
            model.addAttribute("errorValue",errorValue);
            return "account/userLogin";
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
                user.setUserId(userId);
                userService.insertSignon(user);
                model.addAttribute("user",user);
                model.addAttribute("userId",userId);
            }
        }
        return "account/registration";
    }

    @ResponseBody
    @RequestMapping("/getAllUser")
    public List<User> manageUser(Model model){
        List<Integer> idList = userService.getNullInfo();
        for(int i = 0 ; i < idList.size(); i++){
            System.out.println(idList.get(i));
            userService.deleteUser(idList.get(i));

        }
        List<User> userList = userService.getAllUser();
        return userList;
    }

    @RequestMapping("/ViewIncludeAccountFieldsbyphone")
    public String ViewIncludeAccountFieldsbyphone(HttpServletRequest req, Model model){
        String verifyCode = (String) req.getSession().getAttribute("verifyCode");
        String inputverifyCode = req.getParameter("inputverifyCode");
        String phonenumber = req.getParameter("phonenumber");
        HttpSession session = req.getSession();
        boolean flag = false;
        if(inputverifyCode.equals(verifyCode))
            flag=true;
        if(flag){
            return "/account/userLogin";
        }else {
            return "/account/index";
        }
    }
}

