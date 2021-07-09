package org.csu.carecenter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/diet")
@SessionAttributes("diet")
public class DietController {

    //跳转到膳食信息展示界面
    @GetMapping("/diets")
    public String viewDiets(Model model){
        return "dietManage/diet";
    }

    //跳转到膳食信息展示界面
    @GetMapping("/calendar")
    public String viewCalendar(Model model){
        return "dietManage/dietCalendar";
    }

    //跳转到膳食信息展示界面
    @GetMapping("/addDietForm")
    public String addDietForm(Model model){
        return "dietManage/addDiet";
    }

    //跳转到膳食信息展示界面
    @GetMapping("/editDietForm")
    public String editDietForm(Model model){
        return "dietManage/editDiet";
    }
}
