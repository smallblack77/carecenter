package org.csu.carecenter.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.csu.carecenter.common.PageUtils;
import org.csu.carecenter.entity.ContractEntity;
import org.csu.carecenter.entity.DiseaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.csu.carecenter.entity.PhysicalExamEntity;
import org.csu.carecenter.service.PhysicalExamService;

import javax.servlet.http.HttpSession;


/**
 * @author lyx
 * @email lyx@gmail.com
 * @date 2021-12-06 22:37:23
 */
@Controller
@RequestMapping("/physicalexam")
public class PhysicalExamController {
    @Autowired
    private PhysicalExamService physicalExamService;

    /**
     * 列表
     */

    @RequestMapping("viewList")
    //@RequiresPermissions("carecenter:disease:list")disease
    public String viewList(Model model) {
        List<PhysicalExamEntity> list = physicalExamService.list();
        model.addAttribute("physicalExamList", list);
        return "healthyManage/physicalExam";
    }


    /**
     * 添加页面跳转addPhysicalExamForm
     */
    @GetMapping("/addPhysicalExamForm")
    public String addPhysicalExamForm(Model model) {
        return "healthyManage/addPhysicalExam";
    }

    /**
     * 保存
     */
    @RequestMapping("/addPhysicalExam")
    public String addPhysicalExam(Model model, HttpSession session,
                              Integer custId, String assessment, String doctorName,
                              String note, String examTime, String report
    ) {
        if (!StringUtils.isEmpty(custId) && !StringUtils.isEmpty(assessment) && !StringUtils.isEmpty(doctorName)
                && !StringUtils.isEmpty(note) && !StringUtils.isEmpty(examTime) && !StringUtils.isEmpty(report)) {
            int res = physicalExamService.saveNew(custId, assessment, doctorName, note, examTime, report);
            if (res==1){
                //保存成功
                return this.viewList(model);
            }else {
                String msg = "保存失败";
                session.setAttribute("msg", msg);
                return "redirect:/physicalexam/addPhysicalExamForm";
            }

        } else {
            String msg = "输入不能为空";
            session.setAttribute("msg", msg);
            return "redirect:/physicalexam/addPhysicalExamForm";
        }
    }

    /**
     * 修改 页面跳转
     * updatePhysicalExamForm?id=1
     */
    @RequestMapping("/updatePhysicalExamForm")
    public String updateContractForm(String id, Model model) {
        PhysicalExamEntity entity = physicalExamService.getById(id);
        model.addAttribute("physicalExam", entity);
        return "healthyManage/editPhysicalExam";
    }

    /**
     * 修改体检记录
     * /updatePhysicalExam
     */
    @RequestMapping("/updatePhysicalExam")
    public String updateContract(Model model, HttpSession session,Integer id,
                                 Integer custId, String assessment, String doctorName,
                                 String note, String examTime, String report) {
        deletePhysicalExam(id.toString(),model,session);
        return addPhysicalExam(model,session,custId,assessment,doctorName,note,examTime,report);
    }


    /**
     * 删除/deletePhysicalExam?id=1
     */
    @RequestMapping("/deletePhysicalExam")
    public String deletePhysicalExam(String id, Model model, HttpSession session) {

        boolean b = physicalExamService.removeById(id);
        if (b) {
            return this.viewList(model);
        }
        return "healthyManage/physicalExam";
    }

}