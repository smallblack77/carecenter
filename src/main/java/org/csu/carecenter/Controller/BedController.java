package org.csu.carecenter.Controller;

import org.csu.carecenter.entity.Bed;
import org.csu.carecenter.entity.BedAndCustomer;
import org.csu.carecenter.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String viewBedAndCustomer(Model model, HttpServletRequest req, HttpSession session){
        int id = Integer.parseInt(req.getParameter("bedId"));
        BedAndCustomer bedAndCustomer = bedService.getBedAndCustomer(id);
        session.setAttribute("bedAndCustomer",bedAndCustomer);
        model.addAttribute("bedAndCustomer",bedAndCustomer);
        return "bedManage/bedDetail";
    }

    @GetMapping("/updateBedForm")
    public String updateBedForm(String bedId,Model model){
        Bed bed = bedService.getBedByBedId(Integer.valueOf(bedId));
        model.addAttribute("bed",bed);
        return "bedManage/editBed";
    }

    @RequestMapping("/updateBed")
    public String UpdateBed(Model model,String bedId,String roomNum,boolean bedStatus,String sort,String description,
                            HttpSession session){
        if(bedId!=null && roomNum!=null && String.valueOf(bedStatus) !=null && sort!=null ){
            boolean status;
            Bed bed = new Bed();
            bed.setId(Integer.valueOf(bedId));
            bed.setRoomNum(roomNum);
/*            if(Integer.valueOf(bedStatus)==1){
                status = true;
            }else status = false;*/
            bed.setBedStatus(bedStatus);
            bed.setSort(sort);
            bed.setDescription(description);
            bedService.updateBed(bed);
            model.addAttribute("bed",bed);
            return "bedManage/editBed";
        }else {
            String msg= "输入不能为空";
            session.setAttribute("msg",msg);
            return "bedManage/editBed";
        }

    }

    @GetMapping("/addBedForm")
    public String addBedForm(Model model){
        return "bedManage/addBed";
    }

    @RequestMapping("/addBed")
    public String addBed(Model model,String bedId,String roomNum,boolean bedStatus,String sort,String description,
                         HttpSession session){
        System.out.println("-----------------"+bedId);
        System.out.println(roomNum);
        System.out.println(bedStatus);
        System.out.println("--------------"+description);
        System.out.println(sort);

        if(bedId!=null && roomNum!=null && String.valueOf(bedStatus) !=null && sort!=null ){
            Bed bed = new Bed();
            bed.setId(Integer.valueOf(bedId));
            bed.setRoomNum(roomNum);
/*            if(Integer.valueOf(bedStatus)==1){
                status = true;
            }else status = false;*/
            bed.setBedStatus(bedStatus);
            bed.setSort(sort);
            bed.setDescription(description);
            bedService.insertBed(bed);
        //    model.addAttribute("bed",bed);
            List<Bed> bedList = bedService.getAllBedList();
            model.addAttribute(bedList);
            return "bedManage/bed";
        }else {
            String msg= "输入不能为空";
            session.setAttribute("msg",msg);
            return "redirect:/bed/addBedForm";
        }
    }

    @RequestMapping("deleteBed")
    public String deleteBed(String bedId,Model model,HttpSession session){
        BedAndCustomer bedAndCustomer = bedService.getBedAndCustomer(Integer.valueOf(bedId));
        if(bedAndCustomer != null){
            String msg = "床还住人呢";
            session.setAttribute("msg",msg);
            List<Bed> bedList = bedService.getAllBedList();
            return "bedManage/bed";
        }else {
            bedService.deleteBed(Integer.valueOf(bedId));
            List<Bed> bedList = bedService.getAllBedList();
            model.addAttribute("bedList",bedList);
            return "bedManage/bed";
        }

    }

}
