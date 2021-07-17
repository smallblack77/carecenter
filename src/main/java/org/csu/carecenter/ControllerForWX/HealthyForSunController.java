package org.csu.carecenter.ControllerForWX;

import org.csu.carecenter.entity.Customer;
import org.csu.carecenter.entity.Healthy;
import org.csu.carecenter.entity.VO.HealthyVO;
import org.csu.carecenter.service.CustomerService;
import org.csu.carecenter.service.HealthyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/healthyForSun")
@SessionAttributes("healthyForSun")
public class HealthyForSunController {

    @Autowired
    private HealthyService healthyService;

    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @GetMapping("/getHealthyList")
    public List<Healthy> getHealthyList(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        //获取微信小程序get的参数值
        String custname = request.getParameter("custname");
        String phone = request.getParameter("phone");

        System.out.println(custname);
        System.out.println(phone);

        int id = customerService.getCustomerId(custname, phone);
        List<Healthy> healthyList = healthyService.getAllHealthy(id);
        return healthyList;
    }

    @ResponseBody
    @GetMapping("/getHealthyVO")
    public HealthyVO getHealthyVO(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException {
        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        //获取微信小程序get的参数值
        String custname = request.getParameter("custname");
        String phone = request.getParameter("phone");
        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currengTime = formatter.format(date);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = sdf.parse(currengTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        String currentWeek = simpleDateFormat.format(currentDate);

        int id = customerService.getCustomerId(custname, phone);
//        List<HealthyVO> healthyVOList = healthyService.getHealthyVO(id, currentWeek);

        Healthy healthy = healthyService.getHealthy(id, currentWeek);
        Customer customer = customerService.getCustomer(id);
        HealthyVO healthyVO = new HealthyVO();
        healthyVO.setCustId(healthy.getCustId());
        healthyVO.setTemp(healthy.getTemp());
        healthyVO.setPressure(healthy.getPressure());
        healthyVO.setSugar(healthy.getSugar());
        healthyVO.setWeight(healthy.getWeight());
        healthyVO.setPulse(healthy.getPulse());
        healthyVO.setSelfCare(healthy.getSelfCare());
        healthyVO.setDay(healthy.getDay());

        healthyVO.setHeight(customer.getHeight());
        healthyVO.setSex(customer.getSex());
        healthyVO.setName(custname);
        healthyVO.setAttention(customer.getAttention());
        return healthyVO;
    }
}
