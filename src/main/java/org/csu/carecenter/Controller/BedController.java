package org.csu.carecenter.Controller;

import org.csu.carecenter.entity.Bed;
import org.csu.carecenter.entity.BedAndCustomer;
import org.csu.carecenter.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class BedController {

    @Autowired
    private BedService bedService;

    //返回床的列表
    public String viewBedList(Model model){
        List<Bed> bedList = bedService.getBedList();
        model.addAttribute("bedList",bedList);
        return null;
    }

    //传递床和客户的关系
    public String viewBedAndCustomer(int bedId,Model model){
        BedAndCustomer bedAndCustomer = bedService.getBedAndCustomer(bedId);
        model.addAttribute("bedAndCustomer",bedAndCustomer);
        return null;
    }

}
