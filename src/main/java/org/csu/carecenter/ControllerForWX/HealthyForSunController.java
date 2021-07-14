package org.csu.carecenter.ControllerForWX;

import org.csu.carecenter.entity.Healthy;
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

        int id = customerService.getCustomerId(custname);
        List<Healthy> healthyList = healthyService.getAllHealthy(id);
        return healthyList;
    }
}
