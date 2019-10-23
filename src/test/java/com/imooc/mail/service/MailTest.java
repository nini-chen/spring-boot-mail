package com.imooc.mail.service;

import com.imooc.mail.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
}
