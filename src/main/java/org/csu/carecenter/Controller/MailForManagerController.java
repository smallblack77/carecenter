package org.csu.carecenter.controller;

import org.csu.carecenter.entity.Mail;
import org.csu.carecenter.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.mail.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/mailForManager")
@SessionAttributes("mail")
public class MailForManagerController {
    @Autowired
    MailService mailService;

    @GetMapping("/mailForm")
    private String mailForm(){
        return "redirect:/mailForManager/getMail";
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

    @RequestMapping("/getMail")
    public String  getQQMail (Model model) throws MessagingException, IOException
    {
        List<Mail> mailList = mailService.receiveMail();
        model.addAttribute("mailList",mailList);
        return "mailForManager/mailDemo";
    }

    @RequestMapping("/viewMail")
    public String viewMail(Model model,String from, String subject,Date sentDate) throws IOException, MessagingException, ParseException {
        Mail mail = mailService.viewMail(from,subject,sentDate);
        if(mail == null)
        {
            return "mailForManager/mailDemo";
        }
        else {
            model.addAttribute("mail",mail);
            return "mailForManager/viewMail";
        }
    }
}
