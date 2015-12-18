package com.crea.www.commons.util.vo;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PdfEntity {
    private String title="sheet1"; //标题
    private float margin_bottom = 50; //下边距  
    private float margin_left = 50;
    private float margin_top = 50;
    private float margin_right = 50;
    private List<Map<String, String>> Chapter=null;//目录
    private String pageSize = "A4"; //纸张大小  
    private String fileName = "c://report.pdf"; //文件名称  
    private String author = "Galo"; //作者  
    private String subject = "Zhang";   //子项  
    private boolean creationDate = true;    //创建时间  
    private String creator = "Galo"; //创建者  
    private String keywords = "报表"; //关键字  
    private String pageHeader="1-1"; //页眉  
    private String pageFooter="1-1"; //页脚  
    private String[] headers={};   //表头  `
    private List dataset;  //数据集  
    private OutputStream os;    //文件输出流




    public List<Map<String, String>> getChapter() {
        return Chapter;
    }
    public void setChapter(List<Map<String, String>> chapter) {
        Chapter = chapter;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public float getMargin_bottom() {
        return margin_bottom;
    }
    public void setMargin_bottom(float margin_bottom) {
        this.margin_bottom = margin_bottom;
    }
    public float getMargin_left() {
        return margin_left;
    }
    public void setMargin_left(float margin_left) {
        this.margin_left = margin_left;
    }
    public float getMargin_top() {
        return margin_top;
    }
    public void setMargin_top(float margin_top) {
        this.margin_top = margin_top;
    }
    public float getMargin_right() {
        return margin_right;
    }
    public void setMargin_right(float margin_right) {
        this.margin_right = margin_right;
    }
    public String getPageSize() {
        return pageSize;
    }
    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public boolean isCreationDate() {
        return creationDate;
    }
    public void setCreationDate(boolean creationDate) {
        this.creationDate = creationDate;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public String getPageHeader() {
        return pageHeader;
    }
    public void setPageHeader(String pageHeader) {
        this.pageHeader = pageHeader;
    }
    public String getPageFooter() {
        return pageFooter;
    }
    public void setPageFooter(String pageFooter) {
        this.pageFooter = pageFooter;
    }
    public String[] getHeaders() {
        return headers;
    }
    public void setHeaders(String[] headers) {
        this.headers = headers;
    }
    public List getDataset() {
        return dataset;
    }
    public void setDataset(List dataset) {
        this.dataset = dataset;
    }
    public OutputStream getOs() {
        return os;
    }
    public void setOs(OutputStream os) {
        this.os = os;
    }
    @Override
    public String toString() {
        return "PdfEntity [title=" + title + ", margin_bottom=" + margin_bottom
                + ", margin_left=" + margin_left + ", margin_top=" + margin_top
                + ", margin_right=" + margin_right + ", pageSize=" + pageSize
                + ", fileName=" + fileName + ", author=" + author
                + ", subject=" + subject + ", creationDate=" + creationDate
                + ", creator=" + creator + ", keywords=" + keywords
                + ", pageHeader=" + pageHeader + ", pageFooter=" + pageFooter
                + ", headers=" + Arrays.toString(headers) + ", dataset="
                + dataset + ", os=" + os + "]";
    }



}
