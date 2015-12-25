package com.crea.www.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
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
        List textIdList = new ArrayList();
        String keyWordID = request.getParameter("keyWordId");
        List<KeyLink> keyLinkList = keyLinkService.findKeyLinkBykeyWordId(keyWordID);
        for (int i=0; i<keyLinkList.size(); i++){
        	KeyLink keyLink = keyLinkList.get(i);
        	String massageID = keyLink.getMassageId();
        	textIdList.add(massageID);
        }
        result_map.put("data", textIdList);
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
        List articleIdList = new ArrayList();
        String keyWordID = request.getParameter("keyWordId");
        List<KeyLink> keyLinkList = keyLinkService.findKeyLinkBykeyWordId(keyWordID);
        for (int i=0; i<keyLinkList.size(); i++){
        	KeyLink keyLink = keyLinkList.get(i);
        	String massageID = keyLink.getMassageId();
        	articleIdList.add(massageID);
        }
        result_map.put("data", articleIdList);
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    /**
     * 首次关注关联回复信息
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping("/relationFirst")
    public void relationFirst(HttpServletRequest request, HttpServletResponse response, PrintWriter printWriter){
    	Map<String,Object> result_map = new HashMap<String,Object>();
    	String massageId = request.getParameter("massageId");
    	String keyWordId = request.getParameter("keyWordId");
    	KeyLink keyLink = new KeyLink();
//    	保证关键字对应唯一
    	/*List<KeyLink> keyLinkList = keyLinkService.findKeyLinkBykeyWordId("shouciguanzhuid");
    	if (keyLinkList.size() > 0){
        	keyLink = keyLinkList.get(0);
        	keyLink.setMassageId(ListId);
        } else {
        	keyLink.setId(UUID.randomUUID()+"");
        	keyLink.setKeyWordId("shouciguanzhuid");
        	keyLink.setMassageId(ListId);
        }*/
//    	关键字可以对应多个
    	keyLink.setId(UUID.randomUUID()+"");
    	keyLink.setKeyWordId(keyWordId);
    	keyLink.setMassageId(massageId);
    	boolean bon = keyLinkService.saveupdateKeyLink(keyLink);
    	result_map.put("success", bon);
    	
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    /**
     * 首次关注取消关联回复信息
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping("/deleteFirst")
    public void deleteFirst(HttpServletRequest request, HttpServletResponse response, PrintWriter printWriter){
    	Map<String,Object> result_map = new HashMap<String,Object>();
    	String massageId = request.getParameter("massageId");
    	String keyWordId = request.getParameter("keyWordId");
    	KeyLink keyLink = new KeyLink();
    	List<KeyLink> keyLinkList = keyLinkService.findKeyLinkBykeyWordId(keyWordId);
    	for (int i=0;i<keyLinkList.size(); i++){
    		String id = keyLinkList.get(i).getMassageId();
    		if (id == massageId || massageId.equals(id)){
    			keyLink = keyLinkList.get(i);
    			break;
    		}
        }
    	boolean bon = keyLinkService.deleteKeyLink(keyLink);
    	result_map.put("success", bon);
    	
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
        Integer curPage = 1;
        Integer pageSize = 2147483647;
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
        result_map.put("data", keyWord);
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
