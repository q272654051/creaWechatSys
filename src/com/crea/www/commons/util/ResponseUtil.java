package com.crea.www.commons.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 返回到前台的工具类编写
 * @author moon
 *
 */
public class ResponseUtil {
	Logger logger=Logger.getLogger(ResponseUtil.class);
	/**
	 * 返回到前台的数据方法
	 * @param response 
	 * @param json 传进来的字符串
	 */
	public void returnPrintWriter(HttpServletResponse response,String json){
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("ResponseUtil-->returnPrintWriter"+e.toString());
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * 设置头信息编码
	 * @param response
	 */
	public static void responseSetCharacterEncoding(HttpServletResponse response){
		response.setHeader("Cache-Control", "no-cache");   
   	 	response.setContentType("text/json;charset=UTF-8"); 
   	 	response.setCharacterEncoding("UTF-8");  
	}
}
