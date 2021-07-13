package org.csu.carecenter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/main")
public class MainWebController {

    //跳转到管理员登陆界面
    @GetMapping("/mainWeb")
    public String signonForm(Model model){
        return "mainWeb/index";
    }
}
