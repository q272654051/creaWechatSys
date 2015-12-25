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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.crea.www.commons.util.JsonUtil;
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
 * @date 2015-11-19
 * @description
 */

@Controller
@RequestMapping(value="replyController")
public class ReplyController {
    @Resource
    ITextService textService;
    @Resource
    IArticleService articleService;
    @Resource
    IKeyWordService keyWordService;
    @Resource
    IKeyLinkService keyLinkService;
    
    @RequestMapping("/replyText")
    public void replyText(HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter){
        Map<String,Object> result_map = new HashMap<String,Object>();
        List<Text> textlist = textService.findAllText();
        result_map.put("data", textlist);
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    @RequestMapping("/saveText")
    public void saveText(HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter){
        Map<String,Object> result_map = new HashMap<String,Object>();
        String content = request.getParameter("content");
        
        Text text = new Text();
        text.setId(UUID.randomUUID()+"");
        text.setContent(content);

        Boolean bon = textService.saveOrUpdate(text);
        result_map.put("success", bon);
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    /**
     * 根据关键字获取信息接口
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping("/reply")
    public void reply(HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter){
        Text text = new Text();
        Article article = new Article();
        KeyWord keyWord = new KeyWord();
        KeyLink keyLink = new KeyLink();
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        String keyWords = request.getParameter("keyWord");  //获取关键字，首次关注关键字为shouciguanzhiguanjianzi;
        List<KeyWord> keyWordList = keyWordService.findListByKeyWord(keyWords);
        if (keyWordList.size() > 0){
        	keyWord = keyWordList.get(0);
        	String keyWordId = keyWord.getId();//根据关键字获取关键字对应的id
        	List<KeyLink> keyLinkList = keyLinkService.findKeyLinkBykeyWordId(keyWordId);//通过id获取连接表中的消息id
        	if (keyLinkList.size() > 0){
        		for (int i=0; i<keyLinkList.size(); i++){
        			keyLink = keyLinkList.get(i);
        			String massageId = keyLink.getMassageId();
        			text = textService.findById(massageId);
        			article = articleService.findById(massageId);
        			if (text != null){
        				String content = text.getContent();
        				json.put("content", content);
        			} else {
        				json.put("content", "");
        			}
        			if (article != null){
        				String title = article.getTitle();
        				String description = article.getDescription();
        				String picUrl = article.getPicUrl();
        				String url = article.getUrl();
        				json.put("title", title);
        				json.put("description", description);
        				json.put("picUrl", picUrl);
        				json.put("url", url);
        			} else {
        				json.put("title", "");
        				json.put("description", "");
        				json.put("picUrl", "");
        				json.put("url", "");
        			}
        			jsonArray.add(json);
        		}
        	}
        } else {
        	keyWordList = keyWordService.findListByKeyWord("morenhuifu");
        	if (keyWordList.size()>0){
        		keyWord = keyWordList.get(0);
        		String keyWordId = keyWord.getId();//根据关键字获取关键字对应的id
        		List<KeyLink> keyLinkList = keyLinkService.findKeyLinkBykeyWordId(keyWordId);//通过id获取连接表中的消息id
        		if (keyLinkList.size() > 0){
        			for (int i=0; i<keyLinkList.size(); i++){
        				keyLink = keyLinkList.get(i);
        				String massageId = keyLink.getMassageId();
        				text = textService.findById(massageId);
        				article = articleService.findById(massageId);
        				if (text != null){
        					String content = text.getContent();
        					json.put("content", content);
        				} else {
        					json.put("content", "");
        				}
        				if (article != null){
        					String title = article.getTitle();
        					String description = article.getDescription();
        					String picUrl = article.getPicUrl();
        					String url = article.getUrl();
        					json.put("title", title);
        					json.put("description", description);
        					json.put("picUrl", picUrl);
        					json.put("url", url);
        				} else {
        					json.put("title", "");
        					json.put("description", "");
        					json.put("picUrl", "");
        					json.put("url", "");
        				}
        				jsonArray.add(json);
        			}
        		}
        	}
        }
        printWriter.print(jsonArray);
        printWriter.flush();
        printWriter.close();
    }
    
}
