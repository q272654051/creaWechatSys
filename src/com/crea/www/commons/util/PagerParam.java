/**
 * 
 */
package com.crea.www.commons.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/** 
 * @author moon
 * @date 2015-5-11 下午5:02:08
 * @version 
 * @TODO 描述 获取分页参数,起一个验证作用
 */
public class PagerParam {
	/**
	 * @param request
	 * @param pagers
	 * @return
	 */
	public  static Pager getPagerParam(HttpServletRequest request, Pager pagers) {
	String  pageSize_str=request.getParameter("pageSize");
       String  curPage_str=request.getParameter("curPage");
        if (StringUtils.isBlank(curPage_str)) {
            pagers = new Pager();
        } else {
            int curPage = Integer.parseInt(curPage_str);
            if(StringUtils.isNotBlank(pageSize_str)){
            	pagers = new Pager(curPage, Integer.parseInt(pageSize_str));
            }else{    
            	pagers = new Pager(curPage, 10);
            }
        }
		return pagers;
	}
}
