package com.crea.www.commons.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

/**
 * 通过互联网返回 解析外部系统返回的json数据格式或者xml文件格式
 * 返回字符串
 *
 * @author moon
 *
 */
public class URLtoJSONandXML {

    private static URL url = null;
    private static URLConnection urlConnection = null;
    private static  Logger logger=Logger.getLogger(URLtoJSONandXML.class);

    static int BUFFER_SIZE = 8192;

    /**
     * 将InputStream转换成某种字符编码的String
     *
     * @param in
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String InputStreamTOString(InputStream in, String encoding)
            throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return new String(outStream.toByteArray(), encoding);
    }

    /**
     * 采用httpclient 抓取数据 Get  4.1最新方法
     *
     * @param url
     * @param encoding
     * @return
     */
    public static String getHttpUrl_new(String url, String encoding) {
        String content = "";
        //创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(10000).setConnectTimeout(10000).build();// 设置请求和传输超时时间
        httpGet.setConfig(requestConfig);
        try {
            //执行get请求
            HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
            //响应状态
//            System.out.println("status:" + httpResponse.getStatusLine());
            if (httpResponse.getStatusLine().getStatusCode() == 200) {//成功响应吗为200
                //获取响应消息实体
                HttpEntity entity = httpResponse.getEntity();
                //判断响应实体是否为空
                if (entity != null) {
//                System.out.println("contentEncoding:" + entity.getContentEncoding());
//                    content = EntityUtils.toString(entity,encoding);
                    InputStream instream = entity.getContent();
                    try {
                        content = InputStreamTOString(instream, encoding);
                        // System.out.println(content);
                        // do something useful
                    } finally {
                        instream.close();
                    }
                }
            }
        } catch (Exception e) {
        	 logger.error(e.toString());
        } finally {
            try {                //关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
           	 logger.error(e.toString());
            }
        }
        return content;
    }

    /**
     * 采用httpclient 抓取数据 Get
     *
     * @param url
     * @param encoding
     * @return
     */
    public static String getHttpUrl(String url, String encoding) {
        String content = "";
        try {
            HttpClient httpclient = HttpClients.createDefault();// new
            // DefaultHttpClient();
            // 4.3版本代替
            HttpGet httpget = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(10000).setConnectTimeout(10000).build();// 设置请求和传输超时时间
            httpget.setConfig(requestConfig);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {

                InputStream instream = entity.getContent();
                try {
                    content = InputStreamTOString(instream, encoding);
                    // System.out.println(content);
                    // do something useful
                } finally {
                    instream.close();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return content;
    }

    /**
     * 采用httpclient 抓取数据 post 4.1最新方法
     *
     * @param url
     * @param encoding
     * @return
     */
    public static String postHttpUrl_new(String url, String data, String encoding) {
        String content = "";
        //创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpPost httpPost = new HttpPost(url);
        try {
            //设置参数
            StringEntity stringEntry = new StringEntity(data, encoding);
            //设置参数类型
            stringEntry.setContentType("application/json");
            httpPost.setEntity(stringEntry);
            //执行post请求
            HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
            //响应状态
            if (httpResponse.getStatusLine().getStatusCode() == 200) {//成功响应吗为200
                //获取响应消息实体
                HttpEntity entity = httpResponse.getEntity();
                //判断响应实体是否为空
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    try {
                        content = InputStreamTOString(instream, encoding);
                    } finally {
                        instream.close();
                    }
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return content;
    }

    /**
     * 采用httpclient 抓取数据 post
     *
     * @param url
     * @param encoding
     * @return
     */
    public static String postHttpUrl(String url, String data, String encoding) {
        String content = "";
        HttpClient httpclient = new DefaultHttpClient();// new
        HttpPost httpPost = new HttpPost(url);
        try {
            //设置参数
            StringEntity stringEntry = new StringEntity(data, "utf-8");
            //设置参数类型
            stringEntry.setContentType("application/json");
            httpPost.setEntity(stringEntry);
            HttpResponse httpResponse = httpclient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {//成功响应吗为200
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    InputStream instream = httpEntity.getContent();
                    try {
                        content = InputStreamTOString(instream, encoding);
                    } finally {
                        instream.close();
                    }
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return content;
    }

    /**
     * 解析外部系统返回的json或者xml数据方法
     *
     * @param urlPath
     *            连接url
     * @return
     */
    public static String getURLreturnJSONorXML(String urlPath) {

        try {
            url = new URL(urlPath);
            try {
                urlConnection = url.openConnection();
                urlConnection.setConnectTimeout(30000);
                if (urlConnection != null) {
                    InputStreamReader insr = new InputStreamReader(
                            urlConnection.getInputStream(), "UTF-8");
                    BufferedReader br = new BufferedReader(insr);
                    String data = null;
                    String flag = "";
                    while ((data = br.readLine()) != null) {
                        flag = flag + data;
                    }
                    insr.close();
                    return flag;
                }

            } catch (IOException e) {
                e.printStackTrace();
                logger.error("URLtoJSONandXML->getURLreturnJSONorXML:"+e.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            logger.error("URLtoJSONandXML->getURLreturnJSONorXML:"+e.toString());
        }

        return null;
    }

    public static String postURLreturnJSONorXML(String urlPath, String data) {

        String result = "";
        try {

            result = postHttpUrl_new(urlPath, data, "utf-8");
        } catch (Exception ex) {
       	 logger.error(ex.toString());
        }
        return result;
    }
    
    public static void main(String[] args) {
        URLtoJSONandXML
                .getURLreturnJSONorXML("http://api.map.baidu.com/geocoder/v2/?ak=pmCgmADsAsD9rEXkqWNcTzjd&location=116.296447,39.919084"
                        + "&output=json");
    }

}
