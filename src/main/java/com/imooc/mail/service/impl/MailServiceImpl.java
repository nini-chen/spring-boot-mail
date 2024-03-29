package com.imooc.mail.service.impl;

import com.imooc.mail.pojo.Test;
import com.imooc.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author cj
 * @date 2019/12/4
 */

@Service
public class MailServiceImpl implements MailService {

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
    @Override
    public void sendSimpleMail(String to, String subject, String context){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //发件人
        mailMessage.setFrom(from);
        //收件人
        mailMessage.setTo(to);
        //主题
        mailMessage.setSubject(subject);
        //主题
        mailMessage.setText(context);
        //发送
        mailSender.send(mailMessage);
    }

    @Override
    public void sendHtmlMail(String to, String subject, String context) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
        messageHelper.setTo(to);
        messageHelper.setFrom(from);
        messageHelper.setSubject(subject);
        messageHelper.setText(context,true);
        mailSender.send(mimeMessage);
    }

    /**
     *
     * @param test
     * @throws MessagingException
     */
    @Override
    public void sendFileMail(Test test, String syspath) throws MessagingException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);

        // 添加多个附件
        for(MultipartFile multipartFile:test.getMultipartFiles()){
            File file = new File(syspath,multipartFile.getOriginalFilename());
            //判断服务器当前路径文件夹是否存在
            if(!file.getParentFile().exists()){
                //不存在则创建文件夹
                file.getParentFile().mkdirs();
            }

            BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(file));
            out.write(multipartFile.getBytes());
            out.flush();
            out.close();
            FileSystemResource resource = new FileSystemResource(file);
            messageHelper.addAttachment(multipartFile.getOriginalFilename(),file);
        }

        messageHelper.setFrom(from);
        messageHelper.setTo(test.getTo());
        messageHelper.setSubject(test.getSubject());
        messageHelper.setText(test.getContext());
        mailSender.send(mimeMessage);
    }

    @Override
    public void sendImgMail(String to, String subject, String context, FileSystemResource resource, String rscId) throws MessagingException {
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
