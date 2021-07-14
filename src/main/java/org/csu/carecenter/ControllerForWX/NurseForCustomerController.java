package org.csu.carecenter.ControllerForWX;

import org.csu.carecenter.Common.ResponseCode;
import org.csu.carecenter.entity.NurseContent;
import org.csu.carecenter.entity.NurseRecord;
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

    @ResponseBody
    @GetMapping("getNurseList")
    public List<NurseContent> getNurseList(){
        List<NurseContent> nurseContentList = nurseContentService.getAllNurseContentList();
        return nurseContentList;
    }

    @ResponseBody
    @GetMapping("setNurseContent")
    public NurseRecord setNurseContent(HttpServletResponse response, HttpServletRequest request){

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

        NurseRecord nurseRecord = new NurseRecord();
        nurseRecord.setNurseId(nurseId);
        nurseRecord.setCustomerId(Integer.parseInt(customerId));
        nurseRecord.setStartTime(df.format(date));
        nurseRecord.setEndTime(df.format(calendar.getTime()));

        nurseContentService.addNurseRecord(nurseRecord);
        return nurseRecord;
    }
}
