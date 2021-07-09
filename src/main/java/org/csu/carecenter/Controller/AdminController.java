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
        Admin admin = (Admin)model.getAttribute("admin");
        String name = null;
        if(admin != null){
            name = admin.getAdminName();
        }
        model.addAttribute("name",name);
        return "manager/managerAccount";
    }

    //跳转到管理员个人信息修改界面
    @GetMapping("/editAccountForm")
    public String editAccount(Model model){
        Admin admin = (Admin)model.getAttribute("admin");
        model.addAttribute("name",admin.getAdminName());
        model.addAttribute("password",admin.getPassword());
        return "manager/editAccount";
    }

    //修改用户信息界面
    @GetMapping("/editUserForm")
    public String editUser(Model model,Integer userId){
        User user = userService.getUserByUserId(userId);
        model.addAttribute("user",user);
        return "manager/editUser";
    }

    //修改信息显示
    @RequestMapping("/editUser")
    public String viewUser(Model model, @RequestParam("userId")Integer userId,
                           @RequestParam("username")String username,
                           @RequestParam("sex")String sex,
                           @RequestParam("email")String email,
                           @RequestParam("age") String age,
                           HttpSession session,
                           @RequestParam("phoneNumber") String phoneNumber,
                           @RequestParam("role") String role,
                           @RequestParam("password") String password){
        System.out.println(sex);
        if(username!=null  && email !=null && age !=null && phoneNumber !=null && role !=null ){
           if(Integer.valueOf(sex) == 1){
                sex = "女";
            }else {
                sex = "男";
            }
            System.out.println(username);
            User user = new User();
            user.setUsername(username);
            user.setUserId(userId);
            user.setSex(sex);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setAge(Integer.valueOf(age));
            user.setRole(role);
            user.setPassword(password);
            userService.updateUser(user);
            userService.updateSignon(user);
            List<User> userList = userService.getAllUser();
            model.addAttribute("userList",userList);
            return "manager/managerUser";
        }else {
            String msg = "输入不能为空";
            session.setAttribute("mag",msg);
            return "redirect:/admin/editUserForm";
        }
    }

    //跳转到管理员增加个人信息界面
    @GetMapping("/addUserForm")
    public String addUserForm(Model model){
        User newUser = new User();
        userService.insertUser(newUser);
        int userId = userService.getMaxUserId();
        model.addAttribute("userId",userId);
        return "manager/addUser";
    }

    @RequestMapping("/addUser")
    public String addUser(Model model, String username,String sex,String age,String phoneNumber,String role,String email,
                          String password, String userId,HttpSession session){
        if(username!= null && sex != null && age != null && password!= null && phoneNumber!= null && email!=null && role!=null ){
            User newUser = new User();
            newUser.setUserId(Integer.valueOf(userId));
            newUser.setPassword(password);
            newUser.setRole(role);
            newUser.setAge(Integer.valueOf(age));
            newUser.setUsername(username);
            newUser.setPhoneNumber(phoneNumber);
            newUser.setEmail(email);
            newUser.setSex(sex);
            userService.updateUser(newUser);
            userService.insertSignon(newUser);
            List<User> userList = userService.getAllUser();
            model.addAttribute(userList);
            return "/manager/managerUser";
        }else {
            String msg = "输入不能为空";
            session.setAttribute("msg",msg);
            return null;
        }

    }

    //修改头像
    @GetMapping("/upload")
    public String upload(){return "uploadProfile";}

    //修改管理员信息
    @RequestMapping("/editAccount")
    public String editAccount(Model model,String name,String password){
        Admin admin = (Admin) model.getAttribute("admin");
        String oldName = admin.getAdminName();
        admin.setPassword(password);
        admin.setAdminName(name);
        System.out.println(password);
        System.out.println(name);
        System.out.println(oldName);

        adminService.updateAdminn(admin,oldName);
        model.addAttribute("admin",admin);
        return "/manager/managerAccount";
    }

    //删除user
    @RequestMapping("/removeUser")
    public String deleteUser(Model model,Integer userId){
        userService.deleteSignon(userId);
        userService.deleteUser(userId);

        List<User> userList = userService.getAllUser();
        model.addAttribute("userList",userList);
        return "manager/managerUser";

    }

    @ResponseBody
    @RequestMapping("/getAccount")
    public String getAccount(Model model)
    {
        Admin admin = (Admin)model.getAttribute("admin");
        String name = null;
        if(admin != null){
            name = admin.getAdminName();
        }
        model.addAttribute("name",name);
        return name;
    }


}
