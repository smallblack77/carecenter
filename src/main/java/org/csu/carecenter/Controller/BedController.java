package org.csu.carecenter.Controller;

import org.csu.carecenter.entity.Bed;
import org.csu.carecenter.entity.BedAndCustomer;
import org.csu.carecenter.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/bed")
public class BedController {

    @Autowired
    private BedService bedService;

    //跳转到bedmanage页面
    @GetMapping("/bedForm")
    private String bedForm(Model model){
        List<Bed> bedList = bedService.getAllBedList();
        model.addAttribute("bedList",bedList);
        return "bedManage/bed";
    }


    //返回床的列表
    @RequestMapping("/viewBedList")
    public String viewBedList(Model model){
        List<Bed> bedList = bedService.getAllBedList();
        model.addAttribute("bedList",bedList);
        return null;
    }

    @RequestMapping("/viewBedDetail")
    //传递床和客户的关系
    public String viewBedAndCustomer( Model model, HttpServletRequest req){

        int id = Integer.parseInt(req.getParameter("bedId"));
        BedAndCustomer bedAndCustomer = bedService.getBedAndCustomer(id);
        model.addAttribute("bedAndCustomer",bedAndCustomer);
        return "bedManage/bedDetail";
    }

}
