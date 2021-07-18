package org.csu.carecenter.ControllerForWX;

import org.csu.carecenter.Common.ResponseCode;
import org.csu.carecenter.entity.CustomerAndNurse;
import org.csu.carecenter.entity.NurseContent;
import org.csu.carecenter.entity.NurseRecord;
import org.csu.carecenter.service.CustomerService;
import org.csu.carecenter.service.NurseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/nurseForCustomer")
@SessionAttributes("nurseForCustomer")
public class NurseForCustomerController {

    @Autowired
    private NurseContentService nurseContentService;
    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @GetMapping("getNurseList")
    public List<NurseContent> getNurseList(){
        List<NurseContent> nurseContentList = nurseContentService.getAllNurseContentList();
        return nurseContentList;
    }

    @ResponseBody
    @GetMapping("setNurseContent")
    public CustomerAndNurse setNurseContent(HttpServletResponse response, HttpServletRequest request){

        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        //获取微信小程序get的参数值
        String sonId = request.getParameter("sonId");
        String customerId = request.getParameter("customerId");
        String nurseId = request.getParameter("nurseId");

        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");
        //在某一个时间点上加一个月的写法
        Calendar calendar = Calendar.getInstance();
        //此处setTime为Date类型
        calendar.setTime(date);
        //加上两小时
        calendar.add(Calendar.MONTH , 1);//月

        CustomerAndNurse customerAndNurse = new CustomerAndNurse();
        customerAndNurse.setCustId(customerId);
        customerAndNurse.setStartTime(df.format(date));
        customerAndNurse.setEndTime(df.format(calendar.getTime()));

        nurseContentService.insertCustAndNur(customerAndNurse);
        return customerAndNurse;
    }

    //通过id获取护工
    @ResponseBody
    @GetMapping("/getNurById")
    public NurseContent getNurById(String id){
        NurseContent nurseContent = nurseContentService.getNurById(id);
        return nurseContent;
    }

    @RequestMapping("/addNurseAndCust")
    @ResponseBody
    public CustomerAndNurse addNurseAndCust(String custName,String custPhone,String time,String nurseId){
        System.out.println(custName);
        System.out.println(custPhone);

        System.out.println(nurseId);
        int id = customerService.getCustomerId(custName,custPhone);
        CustomerAndNurse customerAndNurse = new CustomerAndNurse();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        System.out.println("当前日期:"+sf.format(c.getTime()));
        customerAndNurse.setStartTime(sf.format(c.getTime()));
        c.add(Calendar.DAY_OF_MONTH, Integer.valueOf(time));
        System.out.println("增加一天后日期:"+sf.format(c.getTime()));


        customerAndNurse.setCustId(String.valueOf(id));

        customerAndNurse.setNurId(nurseId);
        customerAndNurse.setEndTime(sf.format(c.getTime()));

        nurseContentService.insertCustAndNur(customerAndNurse);

        return customerAndNurse;

    }


}
