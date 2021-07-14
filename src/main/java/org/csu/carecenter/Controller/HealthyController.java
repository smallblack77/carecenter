package org.csu.carecenter.Controller;

import org.csu.carecenter.entity.Customer;
import org.csu.carecenter.entity.Healthy;
import org.csu.carecenter.service.CustomerService;
import org.csu.carecenter.service.HealthyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/healthy")
@SessionAttributes(value = {"healthy"})
public class HealthyController {

    @Autowired
    HealthyService healthyService;
    @Autowired
    CustomerService customerService;

    @RequestMapping("/addHealthy")
    public String addHealthy(String id, Model model, HttpSession session) throws ParseException {

        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currengTime = formatter.format(date);
        System.out.println(formatter.format(date));
        session.setAttribute("today",formatter.format(date));
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = sdf.parse(currengTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        String currentWeek = simpleDateFormat.format(currentDate);

        Customer customer = customerService.getCustomer(Integer.valueOf(id));
        model.addAttribute("customer",customer);
        Healthy healthy = healthyService.getHealthy(Integer.valueOf(id),currentWeek);
        model.addAttribute("healthy",healthy);

        return "custManage/healthy";
    }

    @RequestMapping("/updateHealthy")
    public String updateHealthy( String weight, String temp, String pressure, String sugar, String pulse,
                                String selfCare, Model model,HttpSession session)  {


        Healthy healthy1 = (Healthy) model.getAttribute("healthy");
        String day = healthy1.getDay();

        int custId = healthy1.getCustId();


        if( weight!=null && temp != null && pressure!=null && sugar != null && pulse!=null && selfCare!=null ){
            System.out.println("+++++++++++++++++++++++++++++++++");
            if (selfCare.equals("1")){
                selfCare = "可自理";
            }else if(selfCare.equals("2")){
                selfCare = "轻度依赖";
            }else if(selfCare.equals("3")){
                selfCare = "中度依赖";
            }else if(selfCare.equals("4")){
                selfCare = "不能自理";
            }

            Healthy healthy = new Healthy();
            healthy.setPressure(pressure);
            healthy.setCustId(Integer.valueOf(custId));
            healthy.setWeight(Double.valueOf(weight));
            healthy.setTemp(Double.valueOf(temp));
            healthy.setSelfCare(selfCare);
            healthy.setSugar(Double.valueOf(sugar));
            healthy.setPulse(Integer.valueOf(pulse));
            healthy.setDay(day);

            healthyService.updateHealthy(healthy);

            return "redirect:/customer/selectCustomerLsit";


        }else {
            String msg = "不能输入为空";
            session.setAttribute("msg",msg);
            return "redirect:/customer/selectCustomerLsit";
        }


    }

    @GetMapping("/show")

    public String show(Model model,String custId)
    {
        model.addAttribute("custId",custId);
        return "custManage/showHealthy";
    }

    @GetMapping("viewList")
    public String viewList(Model model,String id){
        int custId = Integer.valueOf(id);
        Customer customer = customerService.getCustomer(Integer.valueOf(id));
        model.addAttribute("customer",customer);

        Healthy mon = healthyService.getHealthy(custId,"星期一");
        Healthy tue = healthyService.getHealthy(custId,"星期二");
        System.out.println("+++++++++"+tue.getPressure());
        Healthy wed = healthyService.getHealthy(custId,"星期三");
        Healthy thu = healthyService.getHealthy(custId,"星期四");
        Healthy fri = healthyService.getHealthy(custId,"星期五");
        Healthy sat = healthyService.getHealthy(custId,"星期六");
        Healthy sun = healthyService.getHealthy(custId,"星期日");
        model.addAttribute("mon",mon);
        model.addAttribute("tue",tue);
        model.addAttribute("wed",wed);
        model.addAttribute("thu",thu);
        model.addAttribute("fri",fri);
        model.addAttribute("sat",sat);
        model.addAttribute("sun",sun);


/*        List<Healthy> healthy = healthyService.getAllHealthy(Integer.valueOf(id));

        model.addAttribute("healthy",healthy);*/
        List<Healthy> healthyList = healthyService.getAllList();
        for(int i=0;i<healthyList.size();i++){
            System.out.println(healthyList.get(i).getDay());
        }
        model.addAttribute("healthyList",healthyList);
        return "custManage/healthyDisplay";

    }

    @ResponseBody
    @GetMapping("/showList")
    public List<Healthy> showList(String custId,Model model){
        List<Healthy> healthy = healthyService.getAllHealthy(Integer.valueOf(custId));
        return  healthy;
    }



}
