package com.imooc.mail.service;

import com.imooc.mail.pojo.Test;
import org.springframework.core.io.FileSystemResource;

import javax.mail.MessagingException;

/**
 * @author liu
 * @date 2019/12/4
 */

public interface MailService {

    /**
     * 发送普通邮件
     * @param to
     * @param subject
     * @param context
     */
    public void sendSimpleMail(String to,String subject,String context);

    /**
     * 发送带有html代码的邮件
     * @param to
     * @param subject
     * @param context
     * @throws Exception
     */
    public void sendHtmlMail(String to,String subject,String context) throws Exception ;

    /**
     *  发送带有附件的邮件
     * @param test
     * @param syspath
     * @throws Exception
     */
    public void sendFileMail(Test test, String syspath)throws Exception ;

    /**
     * 发送带有多张图片的的邮件
     * @param to
     * @param subject
     * @param context
     * @param resource
     * @param rscId
     * @throws MessagingException
     */
    public void sendImgMail(String to, String subject, String context,FileSystemResource resource,String rscId)throws MessagingException;

}
