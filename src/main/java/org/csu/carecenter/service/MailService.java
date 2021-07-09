package org.csu.carecenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     * 发送简单邮件
     * @param to 接受者。邮件的接受方
     * @param subject 主题。邮箱标题
     * @param content 内容。是邮箱的Text
     */
    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);//发起者
        mailMessage.setTo(to);//接受者
        //多人mailMessage.setTo("1xx.com","2xx.com","3xx.com");
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        try {
            javaMailSender.send(mailMessage);
            System.out.println("发送简单邮件");
        }catch (Exception e){
            System.out.println("发送简单邮件失败");
        }
    }
}

