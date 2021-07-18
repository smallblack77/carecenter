package org.csu.carecenter.Controller;

import org.csu.carecenter.entity.Customer;
import org.csu.carecenter.entity.Diet;
import org.csu.carecenter.entity.OrderDiet;
import org.csu.carecenter.entity.TimeLine;
import org.csu.carecenter.service.CustomerService;
import org.csu.carecenter.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/diet")
@SessionAttributes("diet")
public class DietController {

    @Autowired
    private DietService dietService;

    @Autowired
    private CustomerService customerService;

    //跳转到膳食信息展示界面
    @GetMapping("/diets")
    public String viewDiets(Model model){
        List<Diet> dietList = dietService.getAllDiet();
        model.addAttribute(dietList);
        return "dietManage/diet";
    }

    //跳转到膳食信息展示界面
    @GetMapping("/photo")
    public String viewPhoto(Model model){
        return "dietManage/dietPhoto";
    }

    @GetMapping("/calendar")
    public String viewCalendar(Model model){
        return "dietManage/dietCalendar";
    }


    //跳转到时间线展示界面
    @GetMapping("/timeLine")
    public String viewTimeLine(Model model,int id){
        return "custManage/timeLine";
    }

    //跳转到膳食信息展示界面
    @GetMapping("/addDietForm")
    public String addDietForm(){
        return "dietManage/addDiet";
    }

    //跳转到膳食信息展示界面
    @RequestMapping("/editDietForm")
    public String editDietForm(HttpServletRequest req, HttpSession session, Model model){
        int id = Integer.parseInt(req.getParameter("id"));
        Diet diet = dietService.getDietById(id);
        model.addAttribute(diet);
        session.setAttribute("diet", diet);
        model.addAttribute("dietId",id);
        return "dietManage/editDiet";
    }

    @RequestMapping("/addDiet")
    public String addDiet(@RequestParam("name")String name,
                          @RequestParam("food1")String food1,
                          @RequestParam("food2")String food2,
                          @RequestParam("food3")String food3,
                          @RequestParam("food4")String food4,
                          @RequestParam("food5")String food5,
                          @RequestParam("description")String description,
                          @RequestParam("taste")String taste,
                          @RequestParam("price")String price,
                          @RequestParam("picture")String picture,
                          HttpSession httpSession,
                          Model model)
    {
        if( name != null && food1 != null && description != null && taste != null && price != null && picture != null ){
            Diet diet = new Diet();
            diet.setName(name);
            diet.setFood1(food1);
            diet.setFood2(food2);
            diet.setFood3(food3);
            diet.setFood4(food4);
            diet.setFood5(food5);
            diet.setDescription(description);
            diet.setPrice(Integer.parseInt(price));
            diet.setTaste(taste);
            diet.setPicture(picture);

            List<Diet> dietList = dietService.getAllDiet();

            boolean jug = false;
            for (Diet diet1:dietList
            ) {
                if(diet.equals(diet1)){
                    jug = true;
                    break;
                }
            }

            if(jug){
                String addDietValue = "重复膳食信息，请重新添加";
                model.addAttribute("addDietValue",addDietValue);
                return "dietManage/addDiet";
            }else {
                dietService.addDiet(diet);
                model.addAttribute("dietList",dietService.getAllDiet());
                return "dietManage/diet";
            }

        }else {
            String addDietValue = "新增失败";
            model.addAttribute("addDietValue",addDietValue);
            return "dietManage/addDiet";}
    }

    @RequestMapping("/editDiet")
    public String editDiet(@RequestParam("name")String name,
                           @RequestParam("food1")String food1,
                           @RequestParam("food2")String food2,
                           @RequestParam("food3")String food3,
                           @RequestParam("food4")String food4,
                           @RequestParam("food5")String food5,
                           @RequestParam("description")String description,
                           @RequestParam("taste")String taste,
                           @RequestParam("price")String price,
                           @RequestParam("picture")String picture,
                           HttpSession httpSession,
                           Model model)
    {
        Diet diet = (Diet) httpSession.getAttribute("diet");
        diet.setName(name);
        diet.setFood1(food1);
        diet.setFood2(food2);
        diet.setFood3(food3);
        diet.setFood4(food4);
        diet.setFood5(food5);
        diet.setDescription(description);
        diet.setPrice(Integer.parseInt(price));
        diet.setTaste(taste);
        diet.setPicture(picture);

        dietService.editDiet(diet);
        List<Diet> dietList = dietService.getAllDiet();
        model.addAttribute(dietList);
        return "dietManage/diet";
    }

    @RequestMapping("/deleteDiet")
    public String deleteDiet(Model model,HttpServletRequest req)
    {
        int id = Integer.parseInt(req.getParameter("id"));
        dietService.deleteDiet(id);
        List<Diet> dietList = dietService.getAllDiet();
        model.addAttribute(dietList);
        return "dietManage/diet";
    }

    @RequestMapping("/addCustDiet")
    public String addCustDiet(@RequestParam("custId")String custId,
                              @RequestParam("bre")String bre,
                              @RequestParam("lunch")String lunch,
                              @RequestParam("dinner")String dinner,
                              Model model){
        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currengTime = formatter.format(date);
     //   System.out.println(formatter.format(date));
        if(!custId.equals("") && !bre.equals("") && !lunch.equals("") && !dinner.equals("")){

            Customer customer = customerService.getCustomer(Integer.valueOf(custId));
            if (customer == null){
                String msg = "该客户不存在";
                model.addAttribute("msg",msg);
                return "dietManage/dietCalendar";
            }

            OrderDiet orderDiet = new OrderDiet();
            orderDiet.setDay(currengTime);
            orderDiet.setCustomerId(custId);
            orderDiet.setBreakfastId(bre);
            orderDiet.setDeleteStatus("1");
            orderDiet.setLunchId(lunch);
            orderDiet.setDinnerId(dinner);

            dietService.addOrderDiet(orderDiet);

            String msg = "添加成功";
            model.addAttribute("msg",msg);

            //添加时间线记录
            //早餐
            Calendar calendar=Calendar.getInstance();
            calendar.set(2015, 10, 12,7,30,0);
            Date breTime = calendar.getTime();

            SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
            TimeLine timeLine = new TimeLine();
            timeLine.setDay(currengTime);
            timeLine.setDate(df1.format(breTime));
            timeLine.setCustId(Integer.parseInt(custId));

            Diet diet = dietService.getDietById(Integer.parseInt(bre));
            String breContent = "早餐时间：" + diet.getName() + "," + diet.getFood1();
            if(diet.getFood2() != null){
                breContent = breContent + "," + diet.getFood2();
            }
            if(diet.getFood3() != null){
                breContent = breContent + "," + diet.getFood3();
            }
            if(diet.getFood4() != null){
                breContent = breContent + "," + diet.getFood4();
            }
            if(diet.getFood5() != null){
                breContent = breContent + "," + diet.getFood5();
            }
            timeLine.setContent(breContent);

            customerService.insertTimeLine(timeLine);

            //中餐
            calendar.set(2015, 10, 12,12,0,0);
            Date lunchTime = calendar.getTime();

            TimeLine timeLine1 = new TimeLine();
            timeLine1.setDay(currengTime);
            timeLine1.setDate(df1.format(lunchTime));
            timeLine1.setCustId(Integer.parseInt(custId));

            Diet diet1 = dietService.getDietById(Integer.parseInt(lunch));
            String lunchContent = "午餐时间：" + diet1.getName() + "," + diet1.getFood1();
            if(diet1.getFood2() != null){
                lunchContent = lunchContent + "," + diet1.getFood2();
            }
            if(diet1.getFood3() != null){
                lunchContent = lunchContent + "," + diet1.getFood3();
            }
            if(diet1.getFood4() != null){
                lunchContent = lunchContent + "," + diet1.getFood4();
            }
            if(diet1.getFood5() != null){
                lunchContent = lunchContent + "," + diet1.getFood5();
            }
            timeLine1.setContent(lunchContent);
            customerService.insertTimeLine(timeLine1);

            //晚餐
            calendar.set(2015, 10, 12,17,30,0);
            Date dinnerTime = calendar.getTime();
            TimeLine timeLine2 = new TimeLine();
            timeLine2.setDay(currengTime);
            timeLine2.setDate(df1.format(dinnerTime));
            timeLine2.setCustId(Integer.parseInt(custId));

            Diet diet2 = dietService.getDietById(Integer.parseInt(dinner));
            String dinnerContent = "晚餐时间：" + diet2.getName() + "," + diet2.getFood1();
            if(diet2.getFood2() != null){
                dinnerContent = dinnerContent + "," + diet2.getFood2();
            }
            if(diet2.getFood3() != null){
                dinnerContent = dinnerContent + "," + diet2.getFood3();
            }
            if(diet2.getFood4() != null){
                dinnerContent = dinnerContent + "," + diet2.getFood4();
            }
            if(diet2.getFood5() != null){
                dinnerContent = dinnerContent + "," + diet2.getFood5();
            }
            timeLine2.setContent(dinnerContent);
            customerService.insertTimeLine(timeLine2);

            return "account/index";
        }else {
            String msg = "输入不能为空";
            model.addAttribute("msg",msg);
            return "dietManage/dietCalendar";
        }
    }
}
