package org.csu.carecenter.Controller;

import org.csu.carecenter.entity.Admin;
import org.csu.carecenter.entity.RandomValidateCode;
import org.csu.carecenter.entity.User;
import org.csu.carecenter.service.AdminService;
import org.csu.carecenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    //跳转到管理员登陆界面
    @GetMapping("/adminLoginForm")
    public String signonForm(Model model){
        return "manager/login";
    }

    @RequestMapping(value = "/adminLogin")
    public  String login(@RequestParam("adminName")String username,
                         @RequestParam("password")String password,
                         @RequestParam("code")String code,
                         HttpSession session, Model model){

        System.out.println(username+":"+password+":"+code);

        if(!code.contentEquals((String)session.getAttribute("checkcode")))
        {
            String value = "ValidateCode Wrong";
            model.addAttribute("value",value);
            return "manager/login";
        }else{
            Admin admin = adminService.getAdminByNameAndPassword(username, password);
            if(admin == null)
            {
                String value = "Invalid username or password.Signon failed.";
                model.addAttribute("value",value);
                return "manager/login";
            }else
            {
                model.addAttribute("admin",admin);

                return "manager/index";
            }
        }
    }

    //验证码
    @RequestMapping(value="/checkCode")
    public void checkCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //跳转到管理员管理用户界面
    @GetMapping("/manageUser")
    public String manageUser(Model model){
        return "/manager/managerUser";
    }

    //跳转到管理员个人信息展示界面
    @GetMapping("/managerAccount")
    public String managerAccount(Model model){
        return "manager/managerAccount";
    }

    //跳转到管理员个人信息修改界面
    @GetMapping("/editAccountForm")
    public String editAccount(Model model){
        return "manager/editAccount";
    }

    //跳转到管理员修改用户信息界面
    @GetMapping("/editUserForm")
    public String editUser(Model model,String username,String password,String userId){



        return "manager/editUser";
    }

    //跳转到管理员增加个人信息界面
    @GetMapping("/addUserForm")
    public String addUser(Model model){
        return "manager/addUser";
    }

    //修改头像
    @GetMapping("/upload")
    public String upload(){return "uploadProfile";}

    //修改管理员信息
    @RequestMapping("/editAccount")
    public String editAccount(){return "";}

    //删除user
    @RequestMapping("/removeUser")
    public String deleteUser(Model model, int userId){
        userService.deleteSignon(userId);
        userService.deleteUser(userId);

        List<User> userList = userService.getAllUser();
        model.addAttribute("userList",userList);
        return "manager/managerUser";

    }


}
