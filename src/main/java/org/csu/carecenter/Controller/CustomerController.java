package org.csu.carecenter.controller;

import org.csu.carecenter.entity.*;
import org.csu.carecenter.service.BedService;
import org.csu.carecenter.service.CustomerService;
import org.csu.carecenter.service.HealthyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/customer")
@SessionAttributes({"customer","checkinId"})
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private HealthyService healthyService;
    @Autowired
    private BedService bedService;

    //时间线
//    @GetMapping("/getDay")
//    public String getDay(HttpServletRequest req,HttpSession session,Model model){
//        int id = Integer.parseInt(req.getParameter("id"));
//        List<Date> dayList = customerService.getDayList();
//        List<TimeLine> timeLineList = customerService.getAllTimeLine();
////        for (Date day:dayList
////             ) {
////
////        }
//        model.addAttribute("dayList", dayList);
//        model.addAttribute("id", id);
//        session.setAttribute("id", id);
//        return "redirect:/customer/getTimeLineById";
//    }

    @GetMapping("/getTimeLineById")
    public String getTimeLineById(HttpServletRequest req,HttpSession session,Model model){
        int id = Integer.parseInt(req.getParameter("id"));
        List<String> dayList = customerService.getDayList(id);
        List<TimeLine> timeLineList = customerService.getTimeLineById(id);

        List<TimeLine> leftLine = new ArrayList<>();
        List<TimeLine> rightLine = new ArrayList<>();


            if(timeLineList.size() % 2 == 0){
                for(int i = 0; i < timeLineList.size() / 2.0; i++){

                    leftLine.add(timeLineList.get(i * 2));
                    rightLine.add(timeLineList.get(i * 2 + 1));
                }
            }else{
                for(int i = 0; i < (timeLineList.size() - 1 )/ 2.0; i++){

                    leftLine.add(timeLineList.get(i * 2));
                    rightLine.add(timeLineList.get(i * 2 + 1));
                }
                leftLine.add(timeLineList.get(timeLineList.size() - 1 ));
            }


        model.addAttribute("dayList", dayList);
        model.addAttribute("leftLine", leftLine);
        model.addAttribute("rightLine", rightLine);
        model.addAttribute("timeLineList", timeLineList);
        return "custManage/timeLine";
    }

    //客户基本信息
    //查询客户列表
    @GetMapping("/selectCustomerList")
    public String getCustomerList(Model model) throws IOException {
        List<Customer> customers =  customerService.getCustomerLsit();
        model.addAttribute("customerList", customers);
        return "custManage/customer";
    }

    @GetMapping("/selectCustomer")
    public void getCustomer(){

    }

     //删除客户信息
    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("id")String id,Model model){
        customerService.deleteCustomer(Integer.parseInt(id));
        healthyService.deleteHealthy(Integer.parseInt(id));
        customerService.deleteCheckin(Integer.parseInt(id));
        model.addAttribute("customerList",customerService.getCustomerLsit());
        return "custManage/customer";
    }

    //修改客户信息的表单
    @GetMapping("/editCustomerForm")
    public String editCustomerForm(HttpServletRequest req, Model model){
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService.getCustomer(id);
        model.addAttribute(customer);
        model.addAttribute("customerId",id);
        return "custManage/editCustomer";
    }

    //修改客户信息
    @PostMapping("/editCustomer")
    public String editCustomer(
                               @RequestParam("rfid")String rfid,
                               @RequestParam("name")String name,
                               @RequestParam("sex")String sex,
                               @RequestParam("age")String age,
                               @RequestParam("height")String height,
                               @RequestParam("weight")String weight,
                               @RequestParam("birthday")String birthday,
                               @RequestParam("attention")String attention,
                               String phone,
                               HttpSession httpSession,
                               Model model){
//        int id = Integer.parseInt(httpSession.getAttribute("id").toString());
        Customer customer = (Customer) httpSession.getAttribute("customer");
        customer.setRfid(rfid);
        customer.setName(name);
        customer.setSex(sex);
        customer.setAge(Integer.parseInt(age));
        customer.setHeight(Double.parseDouble(height));
        customer.setWeight(Double.parseDouble(weight));
        customer.setBirthday(birthday);
        customer.setAttention(attention);
        customer.setPhone(phone);
        customerService.updateCustomer(customer);
        List<Customer> customers =  customerService.getCustomerLsit();
        model.addAttribute("customerList", customers);
        return "custManage/customer";
    }

    //新增客户的表单
    @GetMapping("/newCustomerForm")
    public String newCustomerFrom(){
        return "custManage/addCustomer";
    }

    //新增客户
    @RequestMapping("/addCustomer")
    public String addCustomer(
                            @RequestParam("rfid")String rfid,
                            @RequestParam("name")String name,
                            @RequestParam("sex")String sex,
                            @RequestParam("phone")String phone,
                            @RequestParam("age")String age,
                            @RequestParam("height")String height,
                            @RequestParam("weight")String weight,
                            @RequestParam("birthday")String birthday,
                            @RequestParam("attention")String attention,
                            Model model){
        if( name != null && sex != null && age != null && height != null && weight != null && birthday != null && attention != null ){

            Customer customer = new Customer();
            customer.setRfid(rfid);
            customer.setName(name);
            customer.setSex(sex);
            customer.setPhone(phone);
            customer.setAge(Integer.parseInt(age));
            customer.setHeight(Double.parseDouble(height));
            customer.setWeight(Double.parseDouble(weight));
            customer.setBirthday(birthday);
            customer.setAttention(attention);

            List<Customer> customerList = customerService.getCustomerLsit();

            boolean jug = false;
            for (Customer customer1:customerList
            ) {
                if(customer.equals(customer1)){
                    jug = true;
                    break;
                }
            }

            if(jug){
                String addCustomerValue = "重复客户信息，请重新添加";
                model.addAttribute("addCustomerValue",addCustomerValue);
                return "custManage/addCustomer";
            }else {
                customerService.addCustomer(customer);
                model.addAttribute("customerList",customerService.getCustomerLsit());
                return "custManage/customer";
            }

        }else {
            String addCustomerValue = "新增失败";
            model.addAttribute("addCustomerValue",addCustomerValue);
            return "custManage/addCustomer";
        }

    }




    //入住信息
    //查看所有入住信息
    @GetMapping("/selectCheckinList")
    public String getCheckinList(Model model){
        List<BedAndCustomer> checkinList = customerService.selectCheckinList();
        model.addAttribute("checkinList", checkinList);
        return "custManage/checkin";
    }

    //删除入住信息
    @GetMapping("/deleteCheckin")
    public String deleteCheckin(@RequestParam("id")String id,Model model){
        customerService.deleteCheckin(Integer.parseInt(id));
        List<BedAndCustomer> checkinList = customerService.selectCheckinList();
        model.addAttribute("checkinList", checkinList);
        return "custManage/checkin";
    }

    @RequestMapping("/editCheckinForm")
    public String editCheckinForm(String id,HttpSession session, Model model){

        if(id==null){
         //   System.out.println("mmmmmmmmmmmmmmm");
         //   System.out.println((String) model.getAttribute("checkinId"));
            id = (String) model.getAttribute("checkinId");
            String msg = "修改失败";
            model.addAttribute("msg",msg);
        }
        BedAndCustomer checkin = customerService.selectCheckin(Integer.valueOf(id));
        session.setAttribute("checkin", checkin);
        model.addAttribute("checkin", checkin);
        model.addAttribute("checkinId",id);
        return "/custManage/editCheckin";
    }

    @RequestMapping("/editCheckin")
    public String editCheckin(@RequestParam("custid")String custid,
                              @RequestParam("bedid")String bedid,
                              @RequestParam("starttime")String starttime,
                              HttpSession httpSession,
                              Model model){



        if(!custid.equals("") && !bedid.equals("") && !starttime.equals("")){

            Bed bed = bedService.getBedByBedId(Integer.valueOf(bedid));
            Customer customer = customerService.getCustomer(Integer.valueOf(custid));

            if (bed == null) {
                String msg = "该床位不存在";
                model.addAttribute("msg", msg);
                return "redirect:/customer/editCheckinForm";
            }else if (customer == null){
                String msg = "该用户不存在";
                model.addAttribute("msg",msg);
                return "redirect:/customer/editCheckinForm";
            }else {
                BedAndCustomer checkin = (BedAndCustomer) httpSession.getAttribute("checkin");
                checkin.setCustomerID(Integer.parseInt(custid));
                checkin.setBedId(Integer.parseInt(bedid));
                checkin.setStartTime(starttime);

                customerService.updateCheckin(checkin);
                List<BedAndCustomer> checkinList = customerService.selectCheckinList();
                model.addAttribute("checkinList", checkinList);
                return "custManage/checkin";
            }
        }else {
            String msg = "输入不能为空";
            model.addAttribute("msg",msg);
            return "redirect:/customer/editCheckinForm";
        }



    }

    @GetMapping("/addCheckinForm")
    public String addCheckinForm(){
        return "custManage/addCheckin";
    }

    @RequestMapping("/addCheckin")
    public String addCheckin(@RequestParam("custid")String custid,
                             @RequestParam("bedid")String bedid,
                             @RequestParam("starttime")String starttime,
                             HttpSession httpSession,
                             Model model){
        if( !custid.equals("")  && !bedid.equals("")  && !starttime.equals("")  ){
            Customer customer = customerService.getCustomer(Integer.valueOf(custid));
            Bed bed = bedService.getBedByBedId(Integer.valueOf(bedid));
            if (customer == null){
                String addCheckinValue = "该客户不存在";
                model.addAttribute("addCheckinValue",addCheckinValue);
                return "custManage/addCheckin";
            }else if (bed == null){
                String addCheckinValue ="该床位不存在";
                model.addAttribute("addCheckinValue",addCheckinValue);
                return "custManage/addCheckin";
            }
            if(bed.isBedStatus() == false){
                String addCheckinValue ="该床位不可住";
                model.addAttribute("addCheckinValue",addCheckinValue);
                return "custManage/addCheckin";
            }
            BedAndCustomer checkin = new BedAndCustomer();
            checkin.setCustomerID(Integer.parseInt(custid));
            checkin.setBedId(Integer.parseInt(bedid));
            checkin.setStartTime(starttime);

            List<BedAndCustomer> checkinList = customerService.selectCheckinList();

            boolean jug = false;
            for (BedAndCustomer b:checkinList
            ) {
                if(checkin.equals(b)){
                    jug = true;
                    break;
                }
            }

            if(jug){
                String addCheckinValue = "重复入住信息，请重新添加";
                model.addAttribute("addCheckinValue",addCheckinValue);
                return "custManage/addCheckin";
            }else {

                Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
                TimeLine timeLine = new TimeLine();
                timeLine.setDay(df.format(date));
                timeLine.setDate(df1.format(date));
                timeLine.setCustId(Integer.parseInt(custid));
                timeLine.setContent("入住到" + bedid + "号房间");
                customerService.insertTimeLine(timeLine);

//                //在某一个时间点上加两小时的写法
//                Calendar calendar = Calendar.getInstance();
//                //此处setTime为Date类型
//                calendar.setTime(date);
                bedService.updateBedStatus(0, Integer.parseInt(bedid));
                customerService.addCheckin(checkin);
                model.addAttribute("checkinList",customerService.selectCheckinList());
                return "custManage/checkin";
            }

        }else {
            String addCheckinValue = "新增失败";
            model.addAttribute("addCheckinValue",addCheckinValue);
            return "custManage/addCheckin";
        }
    }






    //退住信息
    //查看所有退住信息
    @GetMapping("/selectCheckoutList")
    public String getCheckoutList(Model model){
        List<BedAndCustomer> list = customerService.selectCheckoutList();
        List<BedAndCustomer> checkoutList = new ArrayList<>();

        for (BedAndCustomer b:list
             ) {
            if(b.getEndTime() != null){
                checkoutList.add(b);
            }
        }
        model.addAttribute("checkoutList", checkoutList);
        return "custManage/checkout";
    }

    @GetMapping("/deleteCheckout")
    public String deleteCheckout(@RequestParam("id")String id,Model model){
        BedAndCustomer checkout = customerService.selectCheckout(Integer.parseInt(id));
        customerService.deleteCheckout(checkout);
        List<BedAndCustomer> list = customerService.selectCheckoutList();
        List<BedAndCustomer> checkoutList = new ArrayList<>();

        for (BedAndCustomer b:list
        ) {
            if(b.getEndTime() != null){
                checkoutList.add(b);
            }
        }
        model.addAttribute("checkoutList", checkoutList);
        return "custManage/checkout";
    }

    @RequestMapping("/editCheckoutForm")
    public String editCheckoutForm(HttpServletRequest req,HttpSession session, Model model){
        int id = Integer.parseInt(req.getParameter("id"));
        BedAndCustomer checkout = customerService.selectCheckout(id);
        session.setAttribute("checkout", checkout);
        model.addAttribute("checkout", checkout);
        model.addAttribute("CheckoutId",id);
        return "/custManage/editCheckout";
    }

    @RequestMapping("/editCheckout")
    public String editCheckout(@RequestParam("custid")String custid,
                               @RequestParam("endtime")String endtime,
                              HttpSession httpSession,
                              Model model){
        BedAndCustomer checkout = (BedAndCustomer) httpSession.getAttribute("checkout");
        checkout.setCustomerID(Integer.parseInt(custid));
        checkout.setStartTime(endtime);

        customerService.updateCheckout(checkout);
        List<BedAndCustomer> list = customerService.selectCheckoutList();
        List<BedAndCustomer> checkoutList = new ArrayList<>();

        for (BedAndCustomer b:list
        ) {
            if(b.getEndTime() != null){
                checkoutList.add(b);
            }
        }
        model.addAttribute("checkoutList", checkoutList);
        return "custManage/checkout";
    }

    @GetMapping("/addCheckoutForm")
    public String addCheckoutForm(){
        return "custManage/addCheckout";
    }

    @RequestMapping("/addCheckout")
    public String addCheckout(@RequestParam("custid")String custid,
                             @RequestParam("endtime")String endtime,
                             HttpSession httpSession,
                             Model model){
        if(custid != null && endtime != null){
            BedAndCustomer checkout = customerService.selectCheckoutByCustId(Integer.parseInt(custid));
            checkout.setEndTime(endtime);

            List<BedAndCustomer> checkoutList = customerService.selectCheckoutList();

            boolean jug = false;
            for (BedAndCustomer b:checkoutList
            ) {
                if(checkout.equals(b)){
                    jug = true;
                    break;
                }
            }

            if(jug){
                String addCheckoutValue = "重复入住信息，请重新添加";
                model.addAttribute("addCheckoutValue",addCheckoutValue);
                return "custManage/addCheckout";
            }else {

                Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
                TimeLine timeLine = new TimeLine();
                timeLine.setDay(df.format(date));
                timeLine.setDate(df1.format(date));
                timeLine.setCustId(Integer.parseInt(custid));
                timeLine.setContent("退住" + checkout.getBedId() + "号房间");
                customerService.insertTimeLine(timeLine);

                customerService.updateCheckout(checkout);
                List<BedAndCustomer> list = customerService.selectCheckoutList();
                List<BedAndCustomer> checkoutList1 = new ArrayList<>();

                for (BedAndCustomer b:list
                ) {
                    if(b.getEndTime() != null){
                        checkoutList1.add(b);
                    }
                }
                model.addAttribute("checkoutList", checkoutList1);
                return "custManage/checkout";
            }

        }else {
            String addCheckoutValue = "新增失败";
            model.addAttribute("addCheckoutValue",addCheckoutValue);
            return "custManage/addCheckout";
        }
    }



    //外出信息
    //查询所有的外出记录
    @GetMapping("/selectAllOutList")
    public String getAllOutList(Model model){
        List<Out> outList = customerService.getAllOutList();
        model.addAttribute("outList", outList);
        return "custManage/out";
    }

    //修改外出信息的表单
    @GetMapping("/editOutForm")
    public String editOutForm(HttpServletRequest req,HttpSession session, Model model){
        int id = Integer.parseInt(req.getParameter("id"));
        Out out = customerService.getOut(id);
        model.addAttribute(out);
        session.setAttribute("out",out);
        model.addAttribute("outId",id);
        return "custManage/editOut";
    }

    //修改外出信息
    @PostMapping("/editOut")
    public String editOut(@RequestParam("custid")String custid,
                          @RequestParam("reason")String reason,
                          @RequestParam("starttime")String starttime,
                          @RequestParam("exptime")String exptime,
                          @RequestParam("acttime")String acttime,
                          @RequestParam("aidphone")String aidphone,
                          HttpSession httpSession,
                          Model model){
        Out out = (Out) httpSession.getAttribute("out");
        out.setCustomerId(Integer.parseInt(custid));
        out.setReason(reason);
        out.setStartTime(starttime);
        out.setExpectReturnTime(exptime);
        out.setActualReturnTime(acttime);
        out.setAirPhone(aidphone);
        customerService.updateOut(out);
        model.addAttribute("out", out);

        List<Out> outList = customerService.getAllOutList();
        model.addAttribute("outList", outList);
        return "custManage/out";
    }

    //新增外出信息的表单
    @GetMapping("/addOutForm")
    public String addOutForm(){
        return "custManage/addOut";
    }

    //新增外出信息
    @RequestMapping("/addOut")
    public String addOut(@RequestParam("custid")String custid,
                         @RequestParam("reason")String reason,
                         @RequestParam("starttime")String starttime,
                         @RequestParam("exptime")String exptime,
                         @RequestParam("acttime")String acttime,
                         @RequestParam("aidphone")String aidphone,
                         HttpSession httpSession,
                         Model model){
        if(custid != null && reason != null && starttime != null && exptime != null && acttime != null && aidphone != null){
            Out out = new Out();
            out.setCustomerId(Integer.parseInt(custid));
            out.setReason(reason);
            out.setStartTime(starttime);
            out.setExpectReturnTime(exptime);
            out.setActualReturnTime(acttime);
            out.setAirPhone(aidphone);

            List<Out> outList = customerService.getAllOutList();

            boolean jug = false;
            for (Out out1:outList
            ) {
                if(out.equals(out1)){
                    jug = true;
                    break;
                }
            }

            if(jug){
                String addOutValue = "重复外出信息，请重新添加";
                model.addAttribute("addOutValue",addOutValue);
                return "custManage/addOut";
            }else {
                Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
                TimeLine timeLine = new TimeLine();
                timeLine.setDay(df.format(date));
                timeLine.setDate(df1.format(date));
                timeLine.setCustId(Integer.parseInt(custid));
                timeLine.setContent("由于" + reason + "原因外出");
                customerService.insertTimeLine(timeLine);

                customerService.addOut(out);
                model.addAttribute("outList",customerService.getAllOutList());
                return "custManage/out";
            }

        }else {
            String addOutValue = "新增失败";
            model.addAttribute("addOutValue",addOutValue);
            return "custManage/out";
        }

    }

    //删除外出信息
    @GetMapping("/deleteOut")
    public String deleteOut(@RequestParam("id")String id, Model model){
        customerService.deleteOut(Integer.parseInt(id));
        List<Out> outList = customerService.getAllOutList();
        model.addAttribute("outList", outList);
        return "custManage/out";
    }
}
