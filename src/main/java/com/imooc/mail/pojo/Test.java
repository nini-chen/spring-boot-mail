package com.imooc.mail.pojo;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public class Test {
    private MultipartFile[] multipartFiles;

    private String to;

    private String subject;

    private String context;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTo() {
        return to;
    }

    public MultipartFile[] getMultipartFiles() {
        return multipartFiles;
    }

    public void setMultipartFiles(MultipartFile[] multipartFiles) {
        this.multipartFiles = multipartFiles;
    }

    public void setTo(String to) {
        this.to = to;
    }


}
