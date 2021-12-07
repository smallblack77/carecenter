package org.csu.carecenter.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.api.R;
import org.csu.carecenter.common.PageUtils;
import org.csu.carecenter.entity.ContractEntity;
import org.csu.carecenter.entity.NurseContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.csu.carecenter.entity.DiseaseEntity;
import org.csu.carecenter.service.DiseaseService;


/**
 * @author lyx
 * @email lyx@gmail.com
 * @date 2021-12-06 14:12:37
 */
@Controller
@RequestMapping("/disease")
public class DiseaseController {
    @Autowired
    private DiseaseService diseaseService;

    /**
     * 列表
     */
    @RequestMapping("viewList")
    //@RequiresPermissions("carecenter:disease:list")disease
    public String viewList(Model model) {
        List<DiseaseEntity> list = diseaseService.list();
        model.addAttribute("diseaseList", list);
        return "healthyManage/disease";
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("carecenter:disease:info")
    public String info(@PathVariable("id") Integer id) {
        DiseaseEntity disease = diseaseService.getById(id);

        return "";
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("carecenter:disease:save")
    public String save(@RequestBody DiseaseEntity disease) {
        diseaseService.save(disease);

        return "";
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("carecenter:disease:update")
    public String update(@RequestBody DiseaseEntity disease) {
        diseaseService.updateById(disease);

        return "";
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("carecenter:disease:delete")
    public String delete(@RequestBody Integer[] ids) {
        diseaseService.removeByIds(Arrays.asList(ids));

        return "";
    }

}