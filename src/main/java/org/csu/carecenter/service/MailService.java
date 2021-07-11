package org.csu.carecenter.service;

import org.csu.carecenter.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    public static String myEmailAccount = "331980235@qq.com";
    public static String myEmailPassword = "djvbcjypqziabgcc";
    public static String server = "pop.qq.com";

    /**
     * 发送简单邮件
     *
     * @param to      接受者。邮件的接受方
     * @param subject 主题。邮箱标题
     * @param content 内容。是邮箱的Text
     */
    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);//接受者
        //多人mailMessage.setTo("1xx.com","2xx.com","3xx.com");
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        try {
            javaMailSender.send(mailMessage);
            System.out.println("发送简单邮件");
        } catch (Exception e) {
            System.out.println("发送简单邮件失败");
        }
    }

    public List<Mail> receiveMail() throws MessagingException, IOException,ClassCastException
    {
        List<Mail> mailList = new ArrayList<>();
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "pop3");
        props.setProperty("mail.pop3.host", server);
        props.setProperty("mail.pop3.ssl.enable", "true");

        //根据属性新建一个邮件会话.
        Session session = Session.getDefaultInstance(props, null);
        //从会话对象中获得POP3协议的Store对象
        Store store = session.getStore("pop3");
        session.setDebug(true);
        store.connect("pop.qq.com", myEmailAccount, myEmailPassword);
        //获取邮件服务器的收件箱
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);
        Message message[] = folder.getMessages();
        for (int i = 0; i < message.length; i++) {
            Mail mail = new Mail();
            Message message1 = message[i];

            String from = message1.getFrom()[0].toString();
            String subject = message1.getSubject();
            Date sentDate = message1.getSentDate();
            String content = message1.getContent().toString();

            mail.setFrom(from);
            mail.setSubject(subject);
            mail.setSentDate(sentDate);
            mail.setContent(content);
            mailList.add(mail);
        }
        //关闭连接
        folder.close(false);
        store.close();
        return mailList;
    }

    public Mail viewMail(String from, String subject, Date sentDate) throws IOException, MessagingException {
        List<Mail> mailList = receiveMail();
        Mail mail = null;
        for(int i=0;i<mailList.size();i++)
        {
            mail = mailList.get(i);
            if(mail.getFrom().equals(from) && mail.getSubject().equals(subject) && mail.getSentDate().equals(sentDate))
            {
                System.out.println(mail);
                break;
            }
        }
        return mail;
    }
}
