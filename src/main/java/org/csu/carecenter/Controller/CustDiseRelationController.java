package org.csu.carecenter.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.csu.carecenter.common.PageUtils;
import org.csu.carecenter.entity.Customer;
import org.csu.carecenter.entity.DiseaseEntity;
import org.csu.carecenter.entity.NurseRecord;
import org.csu.carecenter.entity.VO.CustDiseRelationVo;
import org.csu.carecenter.service.CustomerService;
import org.csu.carecenter.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.csu.carecenter.entity.CustDiseRelationEntity;
import org.csu.carecenter.service.CustDiseRelationService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


/**
 * @author lyx
 * @email lyx@gmail.com
 * @date 2021-12-06 14:12:37
 */
@Controller
@RequestMapping("/custdiserelation")
public class CustDiseRelationController {
    @Autowired
    private CustDiseRelationService custDiseRelationService;

    @Resource
    CustomerService customerService;

    @Resource
    DiseaseService diseaseService;

    @RequestMapping("viewList")
    //@RequiresPermissions("carecenter:disease:list")disease
    public String viewList(Model model) {
        List<CustDiseRelationVo> list = custDiseRelationService.getDetailList();
        model.addAttribute("relationList", list);
        return "healthyManage/custDisease";
    }


    //页面跳转页面跳转addRelationForm
    @GetMapping("addRelationForm")
    public String addRelationForm() {
        return "healthyManage/addCustDisease";
    }


    //修改患病信息页面
    @RequestMapping("/editCustDiseaseForm")
    public String editNurRecordForm(Model model,Integer id){
//        NurseRecord nurseRecord = nurseContentService.getNurseRecord(Integer.valueOf(id));
//        model.addAttribute("nurseRecord",nurseRecord);
        CustDiseRelationVo vo = custDiseRelationService.getVoById(id);
        model.addAttribute("relation",vo);
        return "healthyManage/editCustDisease";
    }

    /**
     * 添加患病记录
     */
    @RequestMapping("/addRelation")
    public String addRelation(Model model, HttpSession session,
                              Integer custId, Integer diseaseId, String level,
                              String duration, String remarks) {

        //输入值非空判断
        if (!StringUtils.isEmpty(custId) && !StringUtils.isEmpty(diseaseId) && !StringUtils.isEmpty(level) &&
                !StringUtils.isEmpty(duration) && !StringUtils.isEmpty(remarks)) {
            //查询判断录入客户和疾病是否存在
            Customer customer = customerService.getById(custId.toString());
            DiseaseEntity disease = diseaseService.getById(diseaseId);
            if (customer != null && disease != null) {

                //保存患病记录
                CustDiseRelationEntity relation = new CustDiseRelationEntity();
                relation.setCustId(custId);
                relation.setDiseaseId(diseaseId);
                relation.setLevel(level);
                relation.setDuration(duration);
                relation.setRemarks(remarks);
                custDiseRelationService.save(relation);
                //返回页面，输出全部数据
                return this.viewList(model);

            } else {

                String msg = "用户或疾病信息错误";
                session.setAttribute("msg", msg);
                return "healthyManage/addCustDisease";
            }
        } else {
            String msg = "输入不能为空";
            session.setAttribute("msg", msg);
            return "healthyManage/addCustDisease";
        }

    }

    /**
     * 修改/editRelation
     */
    @RequestMapping("/editRelation")
    public String updateNurRecord(Model model, HttpSession session,Integer id,
                                  Integer custId, Integer diseaseId, String level,
                                  String duration, String remarks) {
        //输入值非空判断
        if (!StringUtils.isEmpty(custId) && !StringUtils.isEmpty(diseaseId) && !StringUtils.isEmpty(level) &&
                !StringUtils.isEmpty(duration) && !StringUtils.isEmpty(remarks)) {
            boolean b = custDiseRelationService.removeById(id);
            if (b){
                return this.addRelation(model,session,custId,diseaseId,level,duration,remarks);
            }else {
                return "healthyManage/editCustDisease";
            }
        } else {
            String msg = "输入不能为空";
            session.setAttribute("msg", msg);
            return "healthyManage/editCustDisease";
        }

    }



    //删除
    @RequestMapping("/deleteCustDisease")
    public String deleteNurRecord(String id,Model model){

        if (custDiseRelationService.removeById(id)){
            return this.viewList(model);
        }else {
            String msg = "删除失败";
            model.addAttribute("msg", msg);
            return "healthyManage/custDisease";
        }
    }


}