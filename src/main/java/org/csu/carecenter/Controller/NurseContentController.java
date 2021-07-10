package org.csu.carecenter.Controller;

import org.csu.carecenter.entity.NurseContent;
import org.csu.carecenter.entity.NurseRecord;
import org.csu.carecenter.service.NurseContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("nurseContent")
public class NurseContentController {
    @Autowired
    NurseContentService nurseContentService;

    //跳转到护工信息页面并显示信息
    @GetMapping("viewList")
    public String viewList(Model model){
        List<NurseContent> nurseContentList = nurseContentService.getAllNurseContentList();
        model.addAttribute("nurseList",nurseContentList);
        return "nurManage/nurse";
    }

    //跳转到护工添加页面
    @GetMapping("addNurContentForm")
    public String addNurContentForm(){
        return "nurManage/addNurse";
    }

    //添加护工信息
    @RequestMapping("addNurContent")
    public String addNurContent(Model model, HttpSession session,String nurId,String name,String sex,String age,
                                String price,String description,String levelId){
        if(nurId!=null && name!=null && sex != null && age != null &&description!=null&&price!=null&&levelId!=null){
            NurseContent nurseContent = new NurseContent();
            nurseContent.setNurseId(nurId);
            nurseContent.setName(name);
            nurseContent.setSex(sex);
            nurseContent.setAge(Integer.valueOf(age));
            nurseContent.setDescription(description);
            nurseContent.setPrice(Integer.valueOf(price));
            nurseContent.setLevelId(Integer.valueOf(levelId));
            nurseContentService.addNurseContent(nurseContent);
            List<NurseContent> nurseContentList = nurseContentService.getAllNurseContentList();
            model.addAttribute("nurseList",nurseContentList);
            return "nurManage/nurse";
        }else {
            String msg = "输入不能为空";
            session.setAttribute("msg",msg);
            return "redirect:/nurseContent/addNurContentForm";
        }

    }

    //删除护工
    @RequestMapping("deleteNurContent")
    public String deleteNurContent(String nurid,Model model){
        nurseContentService.deleteNurContent(nurid);
        List<NurseContent> nurseContentList = nurseContentService.getAllNurseContentList();
        if(nurseContentList.size() > 0){
            model.addAttribute("nurseList",nurseContentList);
        }
        return "nurManage/nurse";

    }

    //修改页面
    @RequestMapping("updateNurContentForm")
    public String updateNurContentForm(Model model,@RequestParam("nurid") String nurid){
        System.out.println("-----------------");
        System.out.println(nurid);
        NurseContent nurseContent = nurseContentService.getNurById(nurid);
        System.out.println(nurseContent.getDescription()+"++++++++");
        return "nurManage/editNurse";
    }

    //修改
    @RequestMapping("updateNurContent")
    public String updateNurContent(Model model, HttpSession session,String nurId,String name,String sex,String age,
                                   String price,String description,String levelId){
        if(nurId!=null && name!=null && sex != null && age != null && description!=null && price!=null && levelId != null ){
            NurseContent nurseContent = new NurseContent();
            nurseContent.setNurseId(nurId);
            nurseContent.setName(name);
            nurseContent.setSex(sex);
            nurseContent.setAge(Integer.valueOf(age));
            nurseContent.setDescription(description);
            nurseContent.setPrice(Integer.valueOf(price));
            nurseContent.setLevelId(Integer.valueOf(levelId));
            NurseContent oldNurseContent = (NurseContent) model.getAttribute("nurseContent");
            String oldId = oldNurseContent.getNurseId();
            nurseContentService.updateNurContent(nurseContent,oldId);
            List<NurseContent> nurseContentList = nurseContentService.getAllNurseContentList();
            model.addAttribute("nurseList",nurseContentList);
            return "nurManage/nurse";
        }else {
            String msg = "输入不能为空";
            session.setAttribute("msg",msg);
            return "redirect:/nurseContent/updateNurContentForm";
        }
    }

    //护理记录
    @GetMapping("/viewNurseRecord")
    public String viewNurseRecord(Model model){
        List<NurseRecord> nurseRecordList = nurseContentService.getAllNurseRecordList();
        model.addAttribute(nurseRecordList);
        return "nurManage/nurRecord";
    }

    //添加护理记录页面
    @GetMapping("/addNurseRecordForm")
    public String addNurseRecordForm(Model model){
        return "nurManage/addNurRecord";
    }

    //修改护理记录页面
    @RequestMapping("/editNurRecordForm")
    public String editNurRecordForm(Model model,String id){
        NurseRecord nurseRecord = nurseContentService.getNurseRecord(Integer.valueOf(id));
        model.addAttribute(nurseRecord);
        return "nurManage/editNurRecord";
    }

    @RequestMapping("/addNurseRecord")
    public String addNurseRecord(Model model,HttpSession session,String customerId,String nurseId,String content,
                                 String startTime,String endTime){

        return "nurManage/nurRecord";
    }



}
