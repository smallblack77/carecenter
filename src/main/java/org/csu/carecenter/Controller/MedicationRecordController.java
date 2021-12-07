package org.csu.carecenter.controller;

import org.csu.carecenter.entity.ContractEntity;
import org.csu.carecenter.entity.MedicationRecordEntity;
import org.csu.carecenter.entity.VO.MediRecdVo;
import org.csu.carecenter.service.MedicationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author lyx
 * @email lyx@gmail.com
 * @date 2021-12-06 22:37:23
 */
@Controller
@RequestMapping("/medicationrecord")
public class MedicationRecordController {
    @Autowired
    private MedicationRecordService medicationRecordService;

    /**
     * 列表 addMediRecordForm
     */
    @RequestMapping("viewList")
    public String viewList(Model model) {
        List<MediRecdVo> list = medicationRecordService.getVoList();
        model.addAttribute("medicationRecordVoList", list);
        return "healthyManage/medicationRecord";
    }



    /**
     * 保存页面跳转 /medicationrecord/addMediRecordForm
     */
    @RequestMapping("/addMediRecordForm")
    public String addMediRecordForm(Model model) {
        return "healthyManage/addMedicationRecord";
    }

    /**
     * 保存
     */
    @RequestMapping("/addMediRecord")
    public String addMediRecord(Model model, HttpSession session,
                                Integer custId, String medicine, String dosage,
                                String condition, Integer nurId, String takeTime
    ) {
        if (!StringUtils.isEmpty(custId) && !StringUtils.isEmpty(medicine) && !StringUtils.isEmpty(dosage)
                && !StringUtils.isEmpty(condition) && !StringUtils.isEmpty(nurId) && !StringUtils.isEmpty(takeTime)) {


            MedicationRecordEntity entity = new MedicationRecordEntity();
            entity.setCustId(custId);
            entity.setNurId(nurId);
            entity.setMedicine(medicine);
            entity.setDosage(dosage);
            entity.setCondit(condition);
            entity.setTakeTime(takeTime);
            boolean b = medicationRecordService.saveSafe(entity);
            if (b) {
                return this.viewList(model);
            } else {
                return "redirect:/medicationrecord/addMediRecordForm";
            }
        } else {
            String msg = "输入不能为空";
            session.setAttribute("msg", msg);
            return "redirect:/medicationrecord/addMediRecordForm";
        }
    }


    /**
     * 修改
     * editMediRecordForm?id=7
     */
    @GetMapping("/editMediRecordForm")
    public String editMediRecordForm(String id, Model model) {
        MedicationRecordEntity entity = medicationRecordService.getById(id);
        model.addAttribute("MedicationRecord", entity);
        return "healthyManage/editMedicationRecord";
    }

    //updateMediRecord
    @RequestMapping("/updateMediRecord")
    public String updateMediRecord(Model model, HttpSession session,Integer id,
                                   Integer custId,Integer nurId, String medicine,
                                   String dosage, String condition,String takeTime) {
        if (id != null) {
            medicationRecordService.removeById(id);
            return addMediRecord(model,session,custId,medicine,dosage,condition,nurId,takeTime);
        } else {
            String msg = "输入不能为空";
            session.setAttribute("msg", msg);
            return "healthyManage/editMedicationRecord";
        }

    }

    /**
     * 删除
     * deleteMediRecord?id=8
     */
    @RequestMapping("/deleteMediRecord")
    //@RequiresPermissions("carecenter:medicationrecord:delete")
    public String deleteContract(String id, Model model, HttpSession session) {

        boolean b = medicationRecordService.removeById(id);
        if (b) {
            return this.viewList(model);
        }
        return this.viewList(model);
    }

}