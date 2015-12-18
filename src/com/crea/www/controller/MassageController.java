package com.crea.www.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
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
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
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
        result_map.put("success", bon);
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
        	article.setTitle(title);
        	article.setDescription(description);
        	article.setPicUrl(picUrl);
        	article.setUrl(url);
        } else {
        	article.setId(UUID.randomUUID()+"");
        	article.setTitle(title);
        	article.setDescription(description);
        	article.setPicUrl(picUrl);
        	article.setUrl(url);
        }
        
        Boolean bon = articleService.saveOrUpdate(article);
        result_map.put("success", bon);
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
}
