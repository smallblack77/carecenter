package org.csu.carecenter.controller;

import org.csu.carecenter.entity.CustomerAndNurse;
import org.csu.carecenter.entity.NurseContent;
import org.csu.carecenter.entity.NurseRecord;
import org.csu.carecenter.entity.VO.NurContentVO;
import org.csu.carecenter.service.CustomerService;
import org.csu.carecenter.service.NurseContentService;

import org.csu.carecenter.service.NurseLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("nurseContent")
@SessionAttributes("nurseRecord")
public class NurseContentController {
    @Autowired
    NurseContentService nurseContentService;

    @Autowired
    NurseLevelService nurseLevelService;

    @Autowired
    CustomerService customerService;

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
    //NURID,NAME,SEX,AGE,PRICE,DESCRIBE,LEVELID
    @RequestMapping("addNurContent")
    public String addNurContent(Model model, HttpSession session,
                                String nurId,String name,String sex,String age,
                                String price,String description,Integer levelId){
        if(nurId!=null && name!=null && sex != null && age != null &&description!=null&&price!=null&&levelId!=null){
            NurseContent nurseContent = new NurseContent();
            nurseContent.setNurseId(nurId);
            nurseContent.setName(name);
            nurseContent.setSex(sex);
            nurseContent.setAge(Integer.valueOf(age));
            nurseContent.setDescription(description);
            nurseContent.setPrice(Integer.valueOf(price));
            nurseContent.setLevelId(levelId);
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
        model.addAttribute("nurseContent",nurseContent);
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
    @RequestMapping("/viewNurseRecord")
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
        model.addAttribute("nurseRecord",nurseRecord);
        return "nurManage/editNurRecord";
    }

    //添加护理记录
    @RequestMapping("/addNurseRecord")
    public String addNurseRecord(Model model,HttpSession session,String customerId,String nurseId,String content,
                                 String startTime,String endTime){

        if(!customerId.equals("") && !startTime.equals("") && !endTime.equals("") && !nurseId.equals("") && !content.equals("")){

            int NurId = nurseContentService.getNurId(Integer.valueOf(customerId));

            if(NurId != Integer.valueOf(nurseId)){
                String msg = "护工信息错误";
                session.setAttribute("msg",msg);
                return "nurManage/addNurRecord";

            }else {
                NurseRecord nurseRecord = new NurseRecord();
                nurseRecord.setContent(content);
                nurseRecord.setNurseId(nurseId);
                nurseRecord.setCustomerId(Integer.valueOf(customerId));
                nurseRecord.setStartTime(startTime);
                nurseRecord.setEndTime(endTime);
                nurseContentService.addNurseRecord(nurseRecord);
                int id = nurseContentService.getNurseRecordMaxId();
                nurseRecord.setId(String.valueOf(id));
                List<NurseRecord> nurseRecordList = nurseContentService.getAllNurseRecordList();
                model.addAttribute(nurseRecordList);
                return "nurManage/nurRecord";
            }

        }else {
            String msg = "输入不能为空";
            session.setAttribute("msg",msg);
            return "nurManage/addNurRecord";
        }
    }

    //修改护理记录
    @RequestMapping("updateNurRecord")
    public String updateNurRecord(Model model,HttpSession session,String customerId,String nurseId,String content,
                                  String startTime,String endTime) {
        if (!customerId.equals("") && !startTime.equals("") && !endTime.equals("") && !nurseId.equals("") && !content.equals("")) {

            int NurId = nurseContentService.getNurId(Integer.valueOf(customerId));

            if(NurId != Integer.valueOf(nurseId)){
                String msg = "护工信息错误";
                session.setAttribute("msg",msg);
                return "nurManage/editNurRecord";

            }else {

                NurseRecord nurseRecord = new NurseRecord();
                nurseRecord.setContent(content);
                nurseRecord.setNurseId(nurseId);
                nurseRecord.setCustomerId(Integer.valueOf(customerId));
                nurseRecord.setStartTime(startTime);
                nurseRecord.setEndTime(endTime);
                NurseRecord nurseRecord1 = (NurseRecord) model.getAttribute("nurseRecord");
                int id = Integer.valueOf(nurseRecord1.getId());
                nurseRecord.setId(String.valueOf(id));

                nurseContentService.updateNurRecord(nurseRecord);
                List<NurseRecord> nurseRecordList = nurseContentService.getAllNurseRecordList();
                model.addAttribute(nurseRecordList);
                return "nurManage/nurRecord";
            }

        } else {
            String msg = "输入不能为空";
            session.setAttribute("msg", msg);
            return "nurManage/editNurRecord";
        }
    }

    //删除
    @RequestMapping("deleteNurRecord")
    public String deleteNurRecord(String id,Model model){
        nurseContentService.deleteNurRecord(Integer.valueOf(id));
        List<NurseRecord> nurseRecordList = nurseContentService.getAllNurseRecordList();
        model.addAttribute(nurseRecordList);
        return "nurManage/nurRecord";
    }



    //小程序获取对应护工
    @RequestMapping("getNurseContent")
    @ResponseBody
        public NurContentVO  getNurseContent(HttpServletResponse response, HttpServletRequest request) throws ParseException {

        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        //获取微信小程序get的参数值
        String custname = request.getParameter("custname");
        String phone = request.getParameter("phone");
        int id = customerService.getCustomerId(custname, phone);

        int nurseId = nurseContentService.getNurId(id);
        CustomerAndNurse customerAndNurse = nurseLevelService.getCustAndNurByCustId(id);
        NurseContent nurseContent = nurseLevelService.getNurseContentById(nurseId);

        //计算签约时间和到期时间相隔天数
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = dateFormat.parse(customerAndNurse.getStartTime());
        Date endTime = dateFormat.parse(customerAndNurse.getEndTime());
        Date now = new Date();
        dateFormat.format(now);
        int allDays = (int) ((endTime.getTime() - startTime.getTime()) / (1000*3600*24));
        int lastDays = (int) ((endTime.getTime() - now.getTime()) / (1000*3600*24));

        NurContentVO nurContentVO = new NurContentVO();
        nurContentVO.setNurseId(nurseContent.getNurseId());
        nurContentVO.setName(nurseContent.getName());
        nurContentVO.setSex(nurseContent.getSex());
        nurContentVO.setAge(nurseContent.getAge());
        nurContentVO.setPrice(nurseContent.getPrice());
        nurContentVO.setDescription(nurseContent.getDescription());
        nurContentVO.setLevelId(nurseContent.getLevelId());
        nurContentVO.setLevelName(nurseContent.getLevelName());
        nurContentVO.setStartTime(customerAndNurse.getStartTime());
        nurContentVO.setEndTime(customerAndNurse.getEndTime());
        nurContentVO.setAllDays(allDays);
        nurContentVO.setLastDays(lastDays);
        return nurContentVO;
    }
}
