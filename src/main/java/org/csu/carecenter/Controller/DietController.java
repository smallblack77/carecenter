package org.csu.carecenter.Controller;

import org.csu.carecenter.entity.Diets;
import org.csu.carecenter.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/diet")
@SessionAttributes("diets")
public class DietController {

    @Autowired
    private DietService dietService;

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
    public String editDietForm(Model model,int id){
        return "dietManage/editDiet";
    }

    @RequestMapping("/getAllDiet")
    public List<Diets> getAllDiet(Model model)
    {
        List<Diets> dietsList = dietService.getAllDiet();
        return dietsList;
    }

    @RequestMapping("/addDiet")
    public String addDiet(Model model)
    {
        return "";
    }
    @RequestMapping("/editDiet")
    public String editDiet(Model model)
    {
        return "";
    }
    @RequestMapping("/deleteDiet")
    public String deleteDiet(Model model,int id)
    {
        return "";
    }

}
