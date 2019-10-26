package com.imooc.mail.service;

import com.imooc.mail.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {
    @Resource
    MailService mailService;

    @Test
    public void sendSimpleMailTest(){
        String subject = "这是一封springboot邮件";
        String context = "这是一封测试邮件";
        mailService.sendSimpleMail("1173171092@qq.com",subject,context);
    }

    @Test
    public void sendHtmlMailTest(){
        String subject = "这是一封springboot邮件";
        String context = "<html>" +
                "<body>" +
                "<h1>" +"这是一个H1标题"+
                "</h1>" +
                "</body>" +
                "</html>";
        try {
            mailService.sendHtmlMail("1173171092@qq.com",subject,context);
        }catch (Exception E){
            E.printStackTrace();
        }
    }

    @Test
    public void sendFileMailTest(){
        String subject = "这是一封springboot邮件";
        String context = "<html>" +
                "<body>" +
                "<h1>" +"这是一个H1标题"+
                "</h1>" +
                "</body>" +
                "</html>";
        HashMap<Object,String> file = new HashMap<Object, String>();
        file.put(1,"/Users/cj/Documents/面试/面试题整理.docx");
        file.put(2,"/Users/cj/Documents/面试/模板.docx");
        try{
            mailService.sendFileMail("1173171092@qq.com",subject,context,file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void sendImgMailTest(){
        String image = "/Users/cj/Downloads/1568045440.png";
        FileSystemResource resource = new FileSystemResource(new File(image));
        String subject = "这是一封springboot邮件";
        String rscId = resource.getFilename().substring(0,resource.getFilename().indexOf("."));
        String context = "<html>" +
                "<body><h1>这是一个H1标题</h1>:<img src=\'cid:" +
                rscId + "\'></img></body></html>";
        try{
            mailService.sendImgMail("1173171092@qq.com",subject,context,resource,rscId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
