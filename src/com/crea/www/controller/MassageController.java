package com.crea.www.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
    
    /**
     * 上传图片到服务器
     * @param request
     * @param response
     * @param printWriter
     */
    @RequestMapping("/upload")  
    public void upload(@RequestParam("studentPhoto") MultipartFile file, HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter) throws IOException {  
    	Map<String,Object> result_map = new HashMap<String,Object>();
    	String filePath = FileUploadUtil.uploadFile(file, request);  
        result_map.put("success", true);
		result_map.put("data", filePath);
		printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    @RequestMapping("/download")  
    public void download(String fileName, HttpServletResponse response) throws IOException {  
        OutputStream os = response.getOutputStream();  
        try {  
            response.reset();  
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);  
            response.setContentType("image/jpeg; charset=utf-8");  
            os.write(FileUtils.readFileToByteArray(FileUploadUtil.getFile(fileName)));  
            os.flush();  
        } finally {  
            if (os != null) {  
                os.close();  
            }  
        }  
    }  
    
    @RequestMapping(value="/uploadImage")
    public void uploadImage(HttpServletRequest request, HttpServletResponse response,PrintWriter printWriter){
    	Map<String,Object> result_map = new HashMap<String,Object>();
        String filePath = request.getParameter("filePath");
        String serverPath = "/uploadImg";
        String logoRealPathDir = request.getSession().getServletContext().getRealPath(serverPath);
        String resultPath;
        String root = request.getServletPath();
		try {
			resultPath = LoadImageToServer(filePath,logoRealPathDir);
			result_map.put("success", true);
			result_map.put("data", resultPath);
		} catch (Exception e) {
			result_map.put("success", false);
			e.printStackTrace();
		}
        printWriter.print(JsonUtil.jsonObject(result_map, null, null));
        printWriter.flush();
        printWriter.close();
    }
    
    /**
     * 上传图片的方法
     * @param filePath
     * @param serverPath
     * @return
     * @throws Exception
     */
    public String LoadImageToServer(String filePath,String serverPath) throws Exception {
        String resultPath = "";          //上传后图片所在的路径
        FileOutputStream out = null;     //文件输出流
        try {                               //验证图片上传的格式是否正确
         File f = new File(filePath);
            if (!f.isFile()) {
            throw new Exception(f +" 不是图片文件!");
        }
         if (f != null && f.exists()) {          //这里的ImageIO属于java工厂类，在工厂类class里面，调用的System.gc()，频繁调用会造成dump，需要考虑优化
            BufferedImage image = ImageIO.read(f); // 读入文件
            if (image != null) {
            BufferedImage tag = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);  //构造一个类型为预定义图像类型之一的 BufferedImage
               tag.getGraphics().drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);                     //绘制所需要尺寸大小的图片
            /*
             * 以下生成图片上传后在服务器上的新路径
             */
            int lastLength = filePath.lastIndexOf(".");
            Date date = new Date(System.currentTimeMillis());
            String strDate = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
            int random = (int)(Math.random()*99);
            String imageName = strDate+random;                          //以系统时间来随机的创建图片文件名
            String fileType = filePath.substring(lastLength);              //获取上传图片的类型
            resultPath = serverPath+"site"+imageName+fileType;
            /*
             * 进行图片的绘制
             */
            out = new FileOutputStream(resultPath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tag);
            param.setQuality(0.95f, true); //95%图像      
            param.setDensityUnit(1);                //像素尺寸单位.像素/英寸    
            param.setXDensity(300);                  //水平分辨率      
            param.setYDensity(300);                 //垂直分辨率
            encoder.setJPEGEncodeParam(param);
            encoder.encode(tag);
            tag = null;
          }
         }

         f = null;

        } catch (Exception ex) {
         ex.printStackTrace();
        } finally {
         out.close();
         out = null;
        }
        return resultPath;
       }
}
