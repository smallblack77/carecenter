package org.csu.carecenter.Controller;

import org.csu.carecenter.entity.NurseContent;
import org.csu.carecenter.service.NurseContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("nurseContent")
public class NurseContentController {
    @Autowired
    NurseContentService nurseContentService;

    @GetMapping("viewList")
    public String viewList(Model model){
        List<NurseContent> nurseContentList = nurseContentService.getAllNurseContentList();
        model.addAttribute("nurseList",nurseContentList);
        return "nurManage/nurse";
    }


    @GetMapping("addNurContentForm")
    public String addNurContentForm(){
        return "nurManage/addNurse";
    }


    @RequestMapping("addNurContent")
    public String addNurContent(Model model, HttpSession session,String nurid,String name,String sex,String age,
                                String price,String description,String levelid){
        if(nurid!=null && name!=null && sex != null && age != null &&description!=null&&price!=null&&levelid!=null){
            NurseContent nurseContent = new NurseContent();
            nurseContent.setNurseId(nurid);
            nurseContent.setName(name);
            nurseContent.setSex(sex);
            nurseContent.setAge(Integer.valueOf(age));
            nurseContent.setDescription(description);
            nurseContent.setPrice(Integer.valueOf(price));
            nurseContent.setLevelId(Integer.valueOf(levelid));
            nurseContentService.addNurseContent(nurseContent);
            List<NurseContent> nurseContentList = nurseContentService.getAllNurseContentList();
            model.addAttribute("nurseList",nurseContentList);
            return "nurManage/nurse";
        }else {
            String msg = "输出不能为空";
            session.setAttribute("msg",msg);
            return "redirect:/nurseContent/addNurContentForm";
        }

    }

    @RequestMapping("deleteNurContent")
    public String deleteNurContent(String nurid,Model model){
        nurseContentService.deleteNurContent(nurid);
        List<NurseContent> nurseContentList = nurseContentService.getAllNurseContentList();
        model.addAttribute("nurseList",nurseContentList);
        return "nurManage/nurse";

    }

}
