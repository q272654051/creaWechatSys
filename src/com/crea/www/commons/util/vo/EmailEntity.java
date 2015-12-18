package com.crea.www.commons.util.vo;
/**
 *  邮件发送实体,就是常用参数
 * @author moon
 *
 */
public class EmailEntity {
    // 邮件发送者的地址
    private String fromAddress;
    private String fromAddressName;
    // 邮件接收者的地址  
    private String toAddress;
    private String toAddressName;
    // 登陆邮件发送服务器的用户名和密码  
    private String userName;
    private String password;
    // 邮件主题  
    private String subject;
    // 邮件的文本内容  
    private String content;
    // 邮件附件的文件名,但是有时候需要穿多个文件  ,全路径
    private String attachFileNames;
    //发送邮件的服务器的IP和端口  邮件服务器类型,具体根据客户那边的邮件进行判断
    private String serviceType;
    private String  fileName;
    public String getFromAddress() {
        return fromAddress;
    }
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }
    public String getFromAddressName() {
        return fromAddressName;
    }
    public void setFromAddressName(String fromAddressName) {
        this.fromAddressName = fromAddressName;
    }
    public String getToAddress() {
        return toAddress;
    }
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }
    public String getToAddressName() {
        return toAddressName;
    }
    public void setToAddressName(String toAddressName) {
        this.toAddressName = toAddressName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getAttachFileNames() {
        return attachFileNames;
    }
    public void setAttachFileNames(String attachFileNames) {
        this.attachFileNames = attachFileNames;
    }
    public String getServiceType() {
        return serviceType;
    }
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }




}
