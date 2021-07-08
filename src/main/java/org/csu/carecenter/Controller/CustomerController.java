package org.csu.carecenter.Controller;

import org.csu.carecenter.entity.Customer;
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
    public String deleteCustomer(@RequestParam("id")String id){
        customerService.deleteCustomer(Integer.parseInt(id));
        return "custManage/customer";
    }

    //修改客户信息的表单
    @GetMapping("/editCustomerForm")
    public String editCustomerForm(HttpServletRequest req, Model model){
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService.getCustomer(id);
        model.addAttribute(customer);
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
        int id = Integer.parseInt(httpSession.getAttribute("id").toString());
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setSex(sex);
        customer.setAge(Integer.parseInt(age));
        customer.setHeight(Double.parseDouble(height));
        customer.setWeight(Double.parseDouble(weight));
        customer.setBirthday(birthday);
        customer.setAttention(attention);
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
        if( name != null & sex != null & age != null & height != null & weight != null & birthday != null & attention != null ){
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
}
