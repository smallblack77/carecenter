package org.csu.carecenter.Controller;

import org.csu.carecenter.entity.Mail;
import org.csu.carecenter.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.mail.MessagingException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/mail")
@SessionAttributes("mail")
public class MailController {

    @Autowired
    MailService mailService;

    @RequestMapping("/mailForm")
    private String mailForm(){
        return "redirect:/mail/getMail";
    }

    @RequestMapping("/composeMailForm")
    private String composeMailForm(){
        return "mail/composeMail";
    }

    @RequestMapping("/viewMailForm")
    private String viewMailForm(){
        return "mail/viewMail";
    }

    @RequestMapping("/sendmail")
    public String sendmail(HttpServletRequest request){
        String content = request.getParameter("textcontent");
        String to  = request.getParameter("to");
        String subject = request.getParameter("subject");
        mailService.sendMail(to,subject,content);
        return "mail/mailDemo";
    }

    @RequestMapping("/getMail")
    public String  getQQMail (Model model) throws MessagingException, IOException
    {
        List<Mail> mailList = mailService.receiveMail();
        model.addAttribute("mailList",mailList);
        return "mail/mailDemo";
    }

    @RequestMapping("/viewMail")
    public String viewMail(Model model, String from, String subject,Date sentDate) throws IOException, MessagingException {
        Mail mail = mailService.viewMail(from,subject,sentDate);
        if(mail == null)
        {
            return "mail/mailDemo";
        }
        else {
            model.addAttribute("mail",mail);
            return "mail/viewMail";
        }
    }
}
