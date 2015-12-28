package com.crea.www.controller;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.crea.www.commons.util.JsonUtil;
import com.crea.www.commons.util.Pager;
import com.crea.www.commons.util.ReadProperties;
import com.crea.www.service.IArticleService;
import com.crea.www.service.IKeyLinkService;
import com.crea.www.service.IKeyWordService;
import com.crea.www.service.ITextService;
import com.crea.www.vo.Article;
import com.crea.www.vo.Text;

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
        Pager pager = articleService.findBySQLQuery(id,new Pager(1,5));
        List<Article> articleList = pager.getList();
        String picUrl = articleList.get(0).getPicUrl();
        String realPath = request.getSession().getServletContext()
                .getRealPath("/") + "upload/"+picUrl.split("upload")[1];
        Boolean bon = articleService.deleteArticleById(id);
        deleteFile(realPath);
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
        // 判断文件是否为空
    	String result = "http://";
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中  )
                String filePath = request.getSession().getServletContext()
                    .getRealPath("/") + "upload/";
                // 文件原名称
    			String originFileName = file.getOriginalFilename();
    			Date date = new Date();
    			//当前时间
    			String time = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
    			String fileName = StringUtils.trim(time+"name"+originFileName);
//                File saveDir = new File(filePath);
//                if (!saveDir.getParentFile().exists()){
//                    saveDir.getParentFile().mkdirs();
//                }
                // 转存文件
//                file.transferTo(saveDir);
              //这里使用Apache的FileUtils方法来进行保存
				FileUtils.copyInputStreamToFile(file.getInputStream(),
						new File(filePath, fileName));
				String urlPath = ReadProperties.readPropertie("FileStorage", "UrlPath");
				result = urlPath+"/upload/"+fileName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
        }
        out.write(result);
        out.flush();
        out.close();
    }
    
    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String sPath) {
        Boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
    
}
