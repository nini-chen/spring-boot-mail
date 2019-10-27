package com.imooc.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送普通邮件
     * @param to
     * @param subject
     * @param context
     */
    public void sendSimpleMail(String to,String subject,String context){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);//发件人
        mailMessage.setTo(to);//收件人
        mailMessage.setSubject(subject);//主题
        mailMessage.setText(context);//邮件内容
        mailSender.send(mailMessage);//发送
    }

     public void sendHtmlMail(String to,String subject,String context) throws Exception {
         MimeMessage mimeMessage = mailSender.createMimeMessage();
         MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
         messageHelper.setTo(to);
         messageHelper.setFrom(from);
         messageHelper.setSubject(subject);
         messageHelper.setText(context,true);
         mailSender.send(mimeMessage);
     }

    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param context
     * @param file
     * @throws MessagingException
     */
    public void sendFileMail(String to, String subject, String context, Map<Object,Object> file) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);

        // 添加多个附件
        for(Object key:file.keySet()){
            FileSystemResource resource = new FileSystemResource(file.get(key).toString());
            messageHelper.addAttachment(resource.getFilename(),resource);
        }
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(context,true);
        mailSender.send(mimeMessage);
    }

    public void sendImgMail(String to, String subject, String context,FileSystemResource resource,String rscId) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);

        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(context,true);
        messageHelper.addInline(rscId,resource);
        mailSender.send(mimeMessage);
    }
}
