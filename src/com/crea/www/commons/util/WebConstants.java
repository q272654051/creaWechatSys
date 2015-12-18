/**
 * 
 */
package com.crea.www.commons.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.crea.www.vo.User;

/**
 * @author djx
 * @date 2015-8-12
 * @description
 */
public class WebConstants {
	public static final String CURRENT_USER = "user_info";
	public static void setUser(HttpServletRequest request,User user){
		HttpSession session = request.getSession();
		session.setAttribute(CURRENT_USER, user);
	}
	
	public static User getUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (User)session.getAttribute(CURRENT_USER);
    }
}
