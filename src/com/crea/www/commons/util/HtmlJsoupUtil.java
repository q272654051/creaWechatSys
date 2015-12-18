package com.crea.www.commons.util;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 抓取网页工具类 参考例子 http://www.jb51.net/article/43485.htm
 * http://www.open-open.com/jsoup/dom-navigation.htm
 *
 * @author moon
 *
 */
public class HtmlJsoupUtil {

    static Logger logger = Logger.getLogger(HtmlJsoupUtil.class);

    /**
     * 这里获取的只能是http 和https的数据,暂时不知道数据格式,只提供document文档自己解析(按照html解析)
     *
     * @param URl
     */
    public static Document loadUrlData(String URl) {

        try {
            Document doc = Jsoup.connect(URl).data("query", "Java")//
                    .userAgent("Mozilla")//
                    .cookie("auth", "token")//
                    .timeout(3000)// 相应时间
                    .get();// 请求方式
            return doc;
        } catch (IOException e) {
            logger.error("网页加载出错-->" + e);
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 获取磁盘html文件
     *
     * @param file
     *            文件路径(File input = new File("/tmp/input.html");)
     * @param BaseUrl
     *            基本路径(可以理解项目根目录)
     */
    public static Document loadUrlParse(File file, String BaseUrl) {

        try {
            Document doc = Jsoup.parse(file, "utf-8", BaseUrl);
            return doc;
        } catch (IOException e) {
            logger.error("网页加载出错-->" + e);
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) {
        Document document=   HtmlJsoupUtil.loadUrlData("http://www.open-open.com/jsoup/example-list-links.htm");
        Elements elements= document.select("a[href]");//标签里面的数据库a[herf]
        Elements media = document.select("[src]");
        Elements imports = document.select("link[href]");
        for(Element e:elements){
            System.out.println(e.text());

        }
    }
}
