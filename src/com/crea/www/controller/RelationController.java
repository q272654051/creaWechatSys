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
 * @date 2015-12-19
 * @description
 */

@Controller
@RequestMapping(value="relationController")
public class RelationController {
    @Resource
    ITextService textService;
    @Resource
    IArticleService articleService;
    @Resource
    IKeyWordService keyWordService;
    @Resource
    IKeyLinkService keyLinkService;
    
    /**
     * 加载已关联关键字的文字消息
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping("/loadTextByKeyWord")
    public void loadTextByKeyWord(HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter){
        Map<String,Object> result_map = new HashMap<String,Object>();
        String keyWordID = request.getParameter("keyWordId");
        Integer curPage = Integer.parseInt(request.getParameter("curPage"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        Pager result = new Pager();
        List<KeyLink> keyLinkList = keyLinkService.findKeyLinkBykeyWordId(keyWordID);
        for (int i=0; i<keyLinkList.size(); i++){
        	KeyLink keyLink = keyLinkList.get(i);
        	String massageID = keyLink.getMassageId();
        	result = textService.findBySQLQuery(massageID, new Pager(curPage, pageSize));
        }
        result_map.put("data", result);
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    /**
     * 加载已关联关键字的图文消息
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping("/loadArticleByKeyWord")
    public void loadArticleByKeyWord(HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter){
        Map<String,Object> result_map = new HashMap<String,Object>();
        String keyWordID = request.getParameter("keyWordId");
        Integer curPage = Integer.parseInt(request.getParameter("curPage"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        Pager result = new Pager();
        List<KeyLink> keyLinkList = keyLinkService.findKeyLinkBykeyWordId(keyWordID);
        for (int i=0; i<keyLinkList.size(); i++){
        	KeyLink keyLink = keyLinkList.get(i);
        	String massageID = keyLink.getMassageId();
        	result = articleService.findBySQLQuery(massageID, new Pager(curPage, pageSize));
        }
        result_map.put("data", result);
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    /**
     * 加载关键字
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping("/loadKeyWord")
    public void loadKeyWord(HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter){
        Map<String,Object> result_map = new HashMap<String,Object>();
        Integer curPage = Integer.parseInt(request.getParameter("curPage"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        Pager pagers = new Pager(curPage, pageSize);
        Pager result = keyWordService.findBySQLQuery(pagers);
        result_map.put("data", result);
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    /**
     * 保存关键字
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping("/saveOrupdateKeyWord")
    public void saveOrupdateKeyWord(HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter){
        Map<String,Object> result_map = new HashMap<String,Object>();
        String keyWords = request.getParameter("keyWord");
        String id = request.getParameter("id");
        KeyWord keyWord = new KeyWord();
        if (StringUtils.isNotBlank(id) && id != "null"){
        	keyWord = keyWordService.findById(id);
        }else{
        	keyWord.setId(UUID.randomUUID()+"");
        }
        keyWord.setKeyWord(keyWords);

        Boolean bon = keyWordService.saveOrUpdate(keyWord);
        result_map.put("success", bon);
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    /**
     * 删除关键字
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping(value="/deleteKeyWord")
    public void deleteKeyWord(HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter){
    	Map<String,Object> result_map = new HashMap<String,Object>();
        String id = request.getParameter("id");
        Boolean bon = keyWordService.deleteKeyWordById(id);
        result_map.put("success", bon);
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
}
