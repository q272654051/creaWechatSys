package com.crea.www.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.crea.www.commons.util.FileUploadUtil;
import com.crea.www.commons.util.JsonUtil;
import com.crea.www.commons.util.Pager;
import com.crea.www.service.IArticleService;
import com.crea.www.service.IKeyLinkService;
import com.crea.www.service.IKeyWordService;
import com.crea.www.service.ITextService;
import com.crea.www.service.impl.ArticleServiceImpl;
import com.crea.www.vo.Article;
import com.crea.www.vo.KeyLink;
import com.crea.www.vo.KeyWord;
import com.crea.www.vo.Text;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author djx
 * @date 2015-12-18
 * @description
 */

@Controller
@RequestMapping(value="massageController")
public class MassageController {
    @Resource
    ITextService textService;
    @Resource
    IArticleService articleService;
    @Resource
    IKeyWordService keyWordService;
    @Resource
    IKeyLinkService keyLinkService;
    
    /**
     * 加载文字消息
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping("/loadText")
    public void loadText(HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter){
        Map<String,Object> result_map = new HashMap<String,Object>();
        Integer curPage = Integer.parseInt(request.getParameter("curPage"));
        Integer pageSize = 5;
        Pager pagers = new Pager(curPage, pageSize);
        Pager result = textService.findBySQLQuery(pagers);
        result_map.put("data", result);
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    /**
     * 加载图文消息
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping("/loadArticle")
    public void loadArticle(HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter){
        Map<String,Object> result_map = new HashMap<String,Object>();
        Integer curPage = Integer.parseInt(request.getParameter("curPage"));
        Integer pageSize = 5;
        Pager pagers = new Pager(curPage, pageSize);
        Pager result = articleService.findBySQLQuery(pagers);
        result_map.put("data", result);
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    /**
     * 保存文字消息
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping("/saveOrupdateText")
    public void saveOrupdateText(HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter){
        Map<String,Object> result_map = new HashMap<String,Object>();
        String content = request.getParameter("content");
        String id = request.getParameter("id");
        Text text = new Text();
        if (StringUtils.isNotBlank(id) && id != "null"){
        	text = textService.findById(id);
        }else{
        	text.setId(UUID.randomUUID()+"");
        }
        text.setContent(content);

        Boolean bon = textService.saveOrUpdate(text);
        if (bon){
        	result_map.put("success", "添加成功");
        } else {
        	result_map.put("success", "添加失败");
        }
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    /**
     * 保存图文消息
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping("/saveOrupdateArticle")
    public void saveOrupdateArticle(HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter){
        Map<String,Object> result_map = new HashMap<String,Object>();
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String picUrl = request.getParameter("picUrl");
        String url = request.getParameter("url");
        
        Article article = new Article();
        if (StringUtils.isNotBlank(id) && id != "null"){
        	article = articleService.findById(id);
        } else {
        	article.setId(UUID.randomUUID()+"");
        }
        article.setTitle(title);
        article.setDescription(description);
        article.setPicUrl(picUrl);
        article.setUrl(url);
        
        Boolean bon = articleService.saveOrUpdate(article);
        if (bon){
        	result_map.put("success", "添加成功");
        } else {
        	result_map.put("success", "添加失败");
        }
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    /**
     * 删除文字消息
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping(value="/deleteText")
    public void deleteText(HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter){
    	Map<String,Object> result_map = new HashMap<String,Object>();
        String id = request.getParameter("id");
        Boolean bon = textService.deleteTextById(id);
        result_map.put("success", bon);
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    /**
     * 删除图文消息
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping(value="/deleteArticle")
    public void deleteArticle(HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter){
    	Map<String,Object> result_map = new HashMap<String,Object>();
        String id = request.getParameter("id");
        Boolean bon = articleService.deleteArticleById(id);
        result_map.put("success", bon);
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    /***
     * 保存文件
     *
     * @param file
     * @return
     */
    @RequestMapping(value="/uploadImg")
    public void uploadImg(@RequestParam(value = "exampleInputFile") MultipartFile file,HttpServletRequest request, PrintWriter out) {
    	Map<String,Object> result_map = new HashMap<String,Object>();
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中  )
                String filePath = request.getSession().getServletContext()
                    .getRealPath("/") + "upload/";
                // 文件原名称
    			String originFileName = file.getOriginalFilename();
    			Date date = new Date();
    			//当前时间
    			String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
    			String fileName = time+originFileName;
//                File saveDir = new File(filePath);
//                if (!saveDir.getParentFile().exists()){
//                    saveDir.getParentFile().mkdirs();
//                }
                // 转存文件
//                file.transferTo(saveDir);
              //这里使用Apache的FileUtils方法来进行保存
				FileUtils.copyInputStreamToFile(file.getInputStream(),
						new File(filePath, fileName));
                result_map.put("success", true);
                result_map.put("data", filePath);
            } catch (Exception e) {
                e.printStackTrace();
                result_map.put("success", false);
                result_map.put("data", "http://");
            }
        }else{
        	result_map.put("success", false);
            result_map.put("data", "http://");
        }
        out.write(JsonUtil.jsonObject(result_map, null, null));
        out.flush();
        out.close();
    }
    
}
