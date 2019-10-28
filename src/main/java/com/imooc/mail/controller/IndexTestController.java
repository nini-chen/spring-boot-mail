package com.imooc.mail.controller;

import com.imooc.mail.pojo.Test;
import com.imooc.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexTestController {

    @Autowired
    MailService mailService;

    @RequestMapping(value = {"/filemail"},method = RequestMethod.POST)
    public String getSendFileMail(Test test, HttpServletRequest request){
        try{
            String syspath = request.getSession().getServletContext().getRealPath("filemail");
            mailService.sendFileMail(test,syspath);
            System.out.println("hello");
            return "OK";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "发送成功";
    }

    @RequestMapping("/test")
    public Map getSendMap(){
        Map test = new HashMap();
        test.put(1,"qwe");
        test.put(2,"qwe");
        return test;
    }

    @RequestMapping("/")
    public String indexs(){
        return "OK";
    }
}
