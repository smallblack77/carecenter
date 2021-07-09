package org.csu.carecenter.Controller;

import org.csu.carecenter.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/mailForManager")
@SessionAttributes("mail")
public class MailForManagerController {
    @Autowired
    MailService mailService;

    @RequestMapping("/mailForm")
    private String mailForm(){
        return "mailForManager/mailDemo";
    }

    @RequestMapping("/composeMailForm")
    private String composeMailForm(){
        return "mailForManager/composeMail";
    }

    @RequestMapping("/viewMailForm")
    private String viewMailForm(){
        return "mailForManager/viewMail";
    }

    @RequestMapping("/sendmail")
    public String sendmail(HttpServletRequest request){
        String content = request.getParameter("textcontent");
        String to  = request.getParameter("to");
        String subject = request.getParameter("subject");
        mailService.sendMail(to,subject,content);
        return "mailForManager/mailDemo";
    }
}
