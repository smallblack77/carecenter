package org.csu.carecenter.controller;

import org.csu.carecenter.entity.NurseLevel;
import org.csu.carecenter.service.NurseLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/nurseLevel")
@SessionAttributes("nurLevel")
public class NurseLevelController {

    @Autowired
    private NurseLevelService nurseLevelService;

    @GetMapping("/viewNurseLevelList")
    public String getNurseLevelList(Model model){
        List<NurseLevel> nurLevelList = nurseLevelService.getNurseLevelList();
        model.addAttribute("nurLevelList", nurLevelList);
        return "/nurManage/nurLevel";
    }

    @RequestMapping("/editNurLevelForm")
    public String editNurLevelForm(HttpServletRequest req, HttpSession session, Model model){
        int id = Integer.parseInt(req.getParameter("id"));
        NurseLevel nurseLevel = nurseLevelService.getNurseLevel(id);
        model.addAttribute("nurLevel", nurseLevel);
        session.setAttribute("nurLevel", nurseLevel);
        return "/nurManage/editNurLevel";
    }

    @RequestMapping("/editNurLevel")
    public String editNurLevel(@RequestParam("id")String id,
                               @RequestParam("levelName")String levelname,
                               HttpSession httpSession,
                               Model model){
        NurseLevel nurseLevel = (NurseLevel) httpSession.getAttribute("nurLevel");
        nurseLevel.setLevelId(Integer.parseInt(id));
        nurseLevel.setLevelName(levelname);

        nurseLevelService.editNurLevel(nurseLevel);
        List<NurseLevel> nurLevelList = nurseLevelService.getNurseLevelList();
        model.addAttribute("nurLevelList", nurLevelList);
        return "/nurManage/nurLevel";
    }

    @GetMapping("/addNurLevelForm")
    public String addNurLevelForm(Model model){
        int addId = nurseLevelService.getAddId();
        model.addAttribute("addId", addId);
        return "/nurManage/addNurLevel";
    }

    @RequestMapping("/addNurLevel")
    public String addNurLevel(@RequestParam("id")String id,
                              @RequestParam("levelName")String levelname,
                              HttpSession httpSession,
                              Model model){
        if( id != null && levelname != null){
            NurseLevel nurseLevel = new NurseLevel();
            nurseLevel.setLevelId(Integer.parseInt(id));
            nurseLevel.setLevelName(levelname);

            List<NurseLevel> nurseLevelList = nurseLevelService.getNurseLevelList();

            boolean jug = false;
            for (NurseLevel nurseLevel1:nurseLevelList
            ) {
                if(nurseLevel.equals(nurseLevel1)){
                    jug = true;
                    break;
                }
            }

            if(jug){
                String addNurLevelValue = "重复客户信息，请重新添加";
                model.addAttribute("addNurLevelValue",addNurLevelValue);
                return "nurManage/addNurLevel";
            }else {
                nurseLevelService.addNurLevel(nurseLevel);
                model.addAttribute("nurLevelList",nurseLevelService.getNurseLevelList());
                return "nurManage/nurLevel";
            }

        }else {
            String addNurLevelValue = "新增失败";
            model.addAttribute("addNurLevelValue",addNurLevelValue);
            return "nurManage/addNurLevel";
        }
    }

    @GetMapping("/deleteNurLevel")
    public String deleteNurLevel(HttpServletRequest req, HttpSession session, Model model){
        int id = Integer.parseInt(req.getParameter("id"));
        nurseLevelService.deleteNurLevel(id);
        List<NurseLevel> nurLevelList = nurseLevelService.getNurseLevelList();
        model.addAttribute("nurLevelList", nurLevelList);
        return "/nurManage/nurLevel";
    }

}
