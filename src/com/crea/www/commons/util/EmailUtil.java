package com.crea.www.commons.util;

import java.net.MalformedURLException;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

import com.crea.www.commons.util.vo.EmailEntity;

/**
 * 邮件发送工具
 *
 * @author moon
 *
 */
public class EmailUtil {

    static Logger logger = Logger.getLogger(EmailUtil.class);

    /**
     * 发送邮件,不带文件的
     *
     * @param emailEntity
     * @return
     */
    public static boolean sendEmail(EmailEntity emailEntity) {
        // 创建电子邮件
        MultiPartEmail email = new MultiPartEmail();
        email.setSmtpPort(ContentUtil.emailSmtpPort);
        email.setHostName(ContentUtil.emailServiceType);
        try {
        	email.setFrom(ContentUtil.emailFromAddress,
            		ContentUtil.emailFromAddressName);// 发件人信息
            email.setAuthentication(ContentUtil.emailUserName,
            		ContentUtil.emailPassword);// 发件人用户和密码
            email.addTo(emailEntity.getToAddress(),
                    emailEntity.getToAddressName());// 收件人
            email.setSubject(emailEntity.getSubject());
            email.setMsg(emailEntity.getContent());
            email.setCharset("GB2312");// 设置编码
            // 发送邮件
            email.send();
            logger.info("邮件发送-->" + emailEntity.getToAddress() + "成功!");
            return true;

        } catch (Exception e) {
            logger.info("邮件发送给-->" + emailEntity.getToAddress() + "  失败!" + e);
            e.printStackTrace();
        }
        return false;

    }

    /**
     * 发送的附近,和内容
     *
     * @throws EmailException
     * @throws MalformedURLException
     */
    public static boolean SendFile(EmailEntity emailEntity, String fileUrl,String pathString ) {
        // 创建附件
        EmailAttachment attachment = new EmailAttachment();
        // attachment.setPath("");
        // 这是项目根目录,如果是web目录
        attachment.setPath(pathString);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("ssdadsad");
        // attachment.setName("John.cshtml"); // 这其实是指定格式我查
        BASE64Encoder enc = new BASE64Encoder();
        // this.getName().getBytes()使用的是系统缺省的编码处理,这里是GBK
        attachment.setName("=?GBK?B?" + enc.encode(pathString.getBytes())
                + "?=");// 指定字符编码
        // 创建电子邮件
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(ContentUtil.emailServiceType);// 设置服务格式
        email.setCharset("GB2312");// 设置字符编码
        try {
        	email.setFrom(ContentUtil.emailFromAddress,
            		ContentUtil.emailFromAddressName);// 发件人信息
            email.setAuthentication(ContentUtil.emailUserName,
            		ContentUtil.emailPassword);// 发件人用户和密码
            email.addTo(emailEntity.getToAddress(),
                    emailEntity.getToAddressName());

            
            email.setSubject(emailEntity.getSubject());
            email.setMsg(emailEntity.getContent());
            // 添加附件
            email.attach(attachment);
            // 发送邮件
            email.send();
            logger.info("邮件发送-->" + emailEntity.getToAddress() + "成功!");
            return true;
        } catch (EmailException e) {
            logger.info("邮件发送给-->" + emailEntity.getToAddress() + "  失败!" + e);
            e.printStackTrace();
        }
        return false;
    }

}
