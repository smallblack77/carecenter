package org.csu.carecenter.service;

import org.csu.carecenter.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    public static String myEmailAccount = "1633737877@qq.com";
    public static String myEmailPassword = "ugzpjrkwhucyfcbd";
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
        mailMessage.setFrom(from);
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
            MimeMessage message1 = (MimeMessage) message[i];
            Mail mail = new Mail();

            String from = message1.getFrom()[0].toString();
            String subject = message1.getSubject();
            Date sentDate = message1.getSentDate();
            StringBuffer content = new StringBuffer();
            String contentType = message1.getContentType();
            if (contentType.startsWith("text/plain")) {
                getMailTextContent(message1, content,true);
            } else
            {
                getMailTextContent(message1,content, false);
            }
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

    public Mail viewMail(String from, String subject,Date sentDate) throws IOException, MessagingException {
        List<Mail> mailList = receiveMail();
        Mail mail = null;
        Date realDate = getRealDate(sentDate);
        for(int i=0;i<mailList.size();i++)
        {
            mail = mailList.get(i);
            if(mail.getFrom().equals(from) && mail.getSubject().equals(subject) && mail.getSentDate().equals(realDate))
            {
                System.out.println("break");
                break;
            }
        }
        return mail;
    }

    //获取正确时间
    public static Date getRealDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 将时分秒,毫秒域清零
        calendar.add(Calendar.HOUR,-14);
        return calendar.getTime();
    }

    public static void getMailTextContent(Part part, StringBuffer content,boolean plainFlag) throws MessagingException, IOException {
        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
        if (part.isMimeType("text/html") && !isContainTextAttach && plainFlag == false) {
            content.append(MimeUtility.decodeText(part.getContent().toString()));
        } else if(part.isMimeType("text/plain") && !isContainTextAttach && plainFlag){
            content.append(part.getContent().toString());
            plainFlag = false;
        } else if (part.isMimeType("message/rfc822")) {
            getMailTextContent((Part)part.getContent(),content,plainFlag);
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                getMailTextContent(bodyPart,content,plainFlag);
            }
        }
    }


    /**
     * 对复杂邮件的解析
     * @param multipart
     * @throws MessagingException
     * @throws IOException
     */
    public static void parseMultipart(Multipart multipart) throws MessagingException, IOException {
        int count = multipart.getCount();
        System.out.println("couont =  "+count);
        for (int idx=0;idx<count;idx++) {
            BodyPart bodyPart = multipart.getBodyPart(idx);
            System.out.println(bodyPart.getContentType());
            if (bodyPart.isMimeType("text/plain")) {
                System.out.println("plain................."+bodyPart.getContent());
            } else if(bodyPart.isMimeType("text/html")) {
                System.out.println("html..................."+bodyPart.getContent());
            } else if(bodyPart.isMimeType("multipart/*")) {
                Multipart mpart = (Multipart)bodyPart.getContent();
                parseMultipart(mpart);
            }
        }
    }
}
