package org.csu.carecenter.Controller;

import org.csu.carecenter.entity.Admin;
import org.csu.carecenter.entity.RandomValidateCode;
import org.csu.carecenter.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@SessionAttributes("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //跳转到管理员登陆界面
    @GetMapping("/adminLoginForm")
    public String signonForm(Model model){
        return "manager/login";
    }

    @ResponseBody
    @RequestMapping(value = "/adminLogin",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String login(Map<String ,Object> map,HttpSession session,Model model){

        String username = (String)map.get("adminName");
        String password = (String)map.get("password");
        String code = (String)map.get("code");
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
}
