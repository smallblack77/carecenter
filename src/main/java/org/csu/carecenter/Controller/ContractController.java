package org.csu.carecenter.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.csu.carecenter.entity.Bed;
import org.csu.carecenter.entity.BedAndCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.csu.carecenter.entity.ContractEntity;
import org.csu.carecenter.service.ContractService;

import javax.servlet.http.HttpSession;


/**
 * @author lyx
 * @email lyx@gmail.com
 * @date 2021-12-02 13:59:09
 */
@Controller
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;

    /**
     * 列表
     */
    @RequestMapping("/contractForm")
    //@RequiresPermissions("carecenter:contract:list")
    public String list(Model model) {
        List<ContractEntity> contractList = contractService.list();
        model.addAttribute("contractList", contractList);
        return "contractManage/contract";
    }

    @GetMapping("/addContractForm")
    public String addContractForm(Model model) {
        return "contractManage/addContract";
    }

    @RequestMapping("/addContract")
    public String addContract(Model model, String custid, String manageLevel, String nurseLevel, String url,
                              HttpSession session) {
        if (!StringUtils.isEmpty(custid) && !StringUtils.isEmpty(manageLevel) && !StringUtils.isEmpty(nurseLevel) && !StringUtils.isEmpty(url)) {
            ContractEntity entity = new ContractEntity();
            entity.setCustid(Integer.parseInt(custid));
            entity.setManageLevel(manageLevel);
            entity.setNurseLevel(nurseLevel);
            entity.setUrl(url);
            entity.setCreatedTime(new Date());
            contractService.save(entity);
            //    model.addAttribute("bed",bed);
            List<ContractEntity> entityList = contractService.list();
            model.addAttribute("contractList", entityList);
            return "contractManage/contract";
        } else {
            String msg = "输入不能为空";
            session.setAttribute("msg", msg);
            return "redirect:/contract/addContractForm";
        }
    }

    /**
     * 修改
     */
    @GetMapping("/updateContractForm")
    public String updateContractForm(String contractId, Model model) {
        ContractEntity contractEntity = contractService.getById(Integer.valueOf(contractId));
        model.addAttribute("contract", contractEntity);
        return "contractManage/editContract";
    }

    @RequestMapping("/updateContract")
    public String updateContract(Model model, String contractId, String custid, String manageLevel, String nurseLevel, String url,
                            HttpSession session) {
        if (contractId != null) {
            ContractEntity entity = new ContractEntity();
            entity.setId(Integer.parseInt(contractId));
            entity.setCustid(Integer.parseInt(custid));
            entity.setManageLevel(manageLevel);
            entity.setNurseLevel(nurseLevel);
            entity.setUrl(url);
            contractService.updateById(entity);
            List<ContractEntity> list = contractService.list();
            model.addAttribute("contractList", list);
            return "contractManage/contract";
        } else {
            String msg = "输入不能为空";
            session.setAttribute("msg", msg);
            return "contractManage/editContract";
        }

    }

    /**
     * 删除
     */
  @RequestMapping("deleteContract")
    public String deleteContract(String contractId, Model model, HttpSession session) {

        boolean b = contractService.removeById(contractId);
        if (b) {
            List<ContractEntity> list = contractService.list();
            model.addAttribute("contractList", list);
            return "contractManage/contract";
        }
        return "contractManage/contract";
    }

}