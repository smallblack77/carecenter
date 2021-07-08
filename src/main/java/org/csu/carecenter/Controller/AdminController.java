package org.csu.carecenter.Controller;

import org.csu.carecenter.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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

    @GetMapping("/adminLogin")
    public String login(Model model){
        return "account/userLogin";
    }
}
