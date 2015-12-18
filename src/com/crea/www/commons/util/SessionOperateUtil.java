package com.crea.www.commons.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 
 * @author djx
 * @date 2015年5月11日 下午1:21:30
 * @version 
 * @TODO 描述:存取session工具类
 */
public class SessionOperateUtil {

	static HttpServletRequest request;
	static HttpSession session;
 
    /**
     * 设置HttpRequest中Attribute的简化函数.
     */
    public static void setRequestAttribute(String key ,String value) {
    	request.setAttribute(key, value);
    }
    public static Object getRequestAttribute(String key) {
    	return request.getAttribute(key);
    }
 
    /**
     * 设置HttpSession中Attribute的简化函数.
     */
    public static void setSessionAttribute(String key ,String value){
    	session.setAttribute(key, value);
    }
    public static Object getSessionAttribute(String key) {
    	return session.getAttribute(key);
    }

    /**
     * @author djx
     * @date 2015/5/13
     * @TODO 描述 获取HttpRequest中Attribute的简化函数.（数据字典）
     */
    public static Object getRequestAttribute(HttpServletRequest request,String key) {
        return request.getSession().getServletContext().getAttribute(key);
    }
	
}
