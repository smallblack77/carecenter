package org.csu.carecenter.ControllerForWX;

import org.csu.carecenter.entity.OrderDiet;
import org.csu.carecenter.service.CustomerService;
import org.csu.carecenter.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/dietForCustomer")
@SessionAttributes("dietForCustomer")
public class DietForCustomerController {

    @Autowired
    private DietService dietService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getOrderDietById")
    @ResponseBody
    public List<OrderDiet> getOrderDietById(HttpServletResponse response, HttpServletRequest request){

        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        //获取微信小程序get的参数值
        String custname = request.getParameter("custname");
        String phone = request.getParameter("phone");
        int id = customerService.getCustomerId(custname, phone);

        List<OrderDiet> orderDiets = dietService.getOrderDietById(id);
        return orderDiets;
    }
}
