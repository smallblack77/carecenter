package org.csu.carecenter.Controller;

import org.csu.carecenter.entity.BedAndCustomer;
import org.csu.carecenter.entity.Customer;
import org.csu.carecenter.entity.Out;
import org.csu.carecenter.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/customer")
@SessionAttributes("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;



    @GetMapping("/selectCustomer")
    public void getCustomer(){

    }

    //客户基本信息
    //查询客户列表
    @GetMapping("/selectCustomerLsit")
    public String getCustomerLsit(Model model){
        List<Customer> customers =  customerService.getCustomerLsit();
        model.addAttribute("customerList", customers);
        return "custManage/customer";
    }

     //删除客户信息
    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("id")String id,Model model){
        customerService.deleteCustomer(Integer.parseInt(id));
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
        model.addAttribute("editCustomer","修改客户成功！");
        return "custManage/editCustomer";
    }

    //修改客户信息
    @PostMapping("/editCustomer")
    public String editCustomer(@RequestParam("name")String name,
                               @RequestParam("sex")String sex,
                               @RequestParam("age")String age,
                               @RequestParam("height")String height,
                               @RequestParam("weight")String weight,
                               @RequestParam("birthday")String birthday,
                               @RequestParam("attention")String attention,
                               HttpSession httpSession,
                               Model model){
//        int id = Integer.parseInt(httpSession.getAttribute("id").toString());
        Customer customer = (Customer) httpSession.getAttribute("customer");
        customer.setName(name);
        customer.setSex(sex);
        customer.setAge(Integer.parseInt(age));
        customer.setHeight(Double.parseDouble(height));
        customer.setWeight(Double.parseDouble(weight));
        customer.setBirthday(birthday);
        customer.setAttention(attention);
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
                            @RequestParam("name")String name,
                            @RequestParam("sex")String sex,
                            @RequestParam("age")String age,
                            @RequestParam("height")String height,
                            @RequestParam("weight")String weight,
                            @RequestParam("birthday")String birthday,
                            @RequestParam("attention")String attention,
                            Model model){
        if( name != null && sex != null && age != null && height != null && weight != null && birthday != null && attention != null ){
            Customer customer = new Customer();
            customer.setName(name);
            customer.setSex(sex);
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
    public String deleteCheckin(){

        return "custManage/checkin";
    }

    @RequestMapping("/editCheckinForm")
    public String editCheckinForm(HttpServletRequest req,HttpSession session, Model model){
        int id = Integer.parseInt(req.getParameter("id"));
        BedAndCustomer checkin = customerService.selectCheckin(id);
        session.setAttribute("checkin", checkin);
        model.addAttribute("checkinId",id);
        return "/custManage/editCheckin";
    }

    @RequestMapping("/editCheckin")
    public String editCheckin(@RequestParam("custid")String custid,
                              @RequestParam("bedid")String bedid,
                              @RequestParam("starttime")String starttime,
                              HttpSession httpSession,
                              Model model){
        BedAndCustomer checkin = (BedAndCustomer) httpSession.getAttribute("checkin");
        checkin.setCustomerID(Integer.parseInt(custid));
        checkin.setBedId(Integer.parseInt(bedid));
        checkin.setStartTime(starttime);

        customerService.updateCheckin(checkin);
        List<BedAndCustomer> checkinList = customerService.selectCheckinList();
        model.addAttribute("checkinList", checkinList);
        return "custManage/checkout";
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
        if(custid != null && bedid != null && starttime != null){
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
        List<BedAndCustomer> checkoutList = customerService.selectCheckoutList();
        model.addAttribute("checkoutList", checkoutList);
        return "custManage/checkout";
    }

    @RequestMapping("/editCheckoutForm")
    public String editCheckoutForm(HttpServletRequest req,HttpSession session, Model model){
        int id = Integer.parseInt(req.getParameter("id"));
        BedAndCustomer checkout = customerService.selectCheckout(id);
        session.setAttribute("checkout", checkout);
        model.addAttribute("CheckoutId",id);
        return "/custManage/editCheckout";
    }

    @RequestMapping("/editCheckout")
    public String editCheckout(@RequestParam("custid")String custid,
                              @RequestParam("bedid")String bedid,
                              @RequestParam("starttime")String starttime,
                              HttpSession httpSession,
                              Model model){
        BedAndCustomer checkout = (BedAndCustomer) httpSession.getAttribute("checkout");
        checkout.setCustomerID(Integer.parseInt(custid));
        checkout.setBedId(Integer.parseInt(bedid));
        checkout.setStartTime(starttime);

        customerService.updateCheckout(checkout);
        List<BedAndCustomer> checkoutList = customerService.selectCheckoutList();
        model.addAttribute("checkoutList", checkoutList);
        return "custManage/checkout";
    }

    @GetMapping("/addCheckoutForm")
    public String addCheckoutForm(){
        return "custManage/addCheckout";
    }

    @RequestMapping("/addCheckout")
    public String addCheckout(@RequestParam("custid")String custid,
                             @RequestParam("bedid")String bedid,
                             @RequestParam("starttime")String starttime,
                             HttpSession httpSession,
                             Model model){
        if(custid != null && bedid != null && starttime != null){
            BedAndCustomer checkout = new BedAndCustomer();
            checkout.setCustomerID(Integer.parseInt(custid));
            checkout.setBedId(Integer.parseInt(bedid));
            checkout.setStartTime(starttime);

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
                customerService.addCheckout(checkout);
                model.addAttribute("checkoutList",customerService.selectCheckoutList());
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
