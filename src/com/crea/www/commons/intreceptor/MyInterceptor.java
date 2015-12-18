package com.crea.www.commons.intreceptor;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 自定义拦截器
 * 
 * @author tushen
 * @date Nov 5, 2011
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 最后执行，可用于释放资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	/**
	 * 显示视图前执行
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// System.out.println(request.getContentType()+"-----"+request.getCharacterEncoding()+"------"+request.getContextPath());
		// System.out.println("访问结束返回路 径--->>  "+modelAndView.getViewName());
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * Controller之前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	 // 1.获取用户ID
        String userId = (String) request.getSession().getAttribute("userId");
        
        String request_Url = request.getRequestURL().toString();//请求的链接
        String reg = ".*\\.(?i)(jpg|js|png|css|gif)";
        //静态资源不进行验证
        if(request_Url.matches(reg)){
            return true;
        }
        if(request_Url.contains("replyController/reply")){
        	return true;
        }
        if (null == userId || "".equals(userId)) {// 如果用户为空(同等于session过期操作)
            String url = request_Url.substring(request_Url.lastIndexOf("/"), request_Url.length()).replace(".do","");
            // 跳到首页
            if (url.indexOf("tologin") == -1
                    && url.indexOf("_noLogin") == -1
                    && url.indexOf(".") == -1) {// 不需要登录就能进入的页面
                Map map = request.getParameterMap();
                String params = "";// 参数
                if (null != map && map.size() > 0) {
                    Iterator it = map.keySet().iterator();
                    while (it.hasNext()) {
                        String key = "";
                        String[] value;
                        key = it.next().toString();
                        value = (String[]) map.get(key);
                        params += key + "=" + value[0] + "&";
                    }
                    response.sendRedirect(request.getContextPath()+"/login.jsp?ResultUrl=" + request.getRequestURL() + "?" + params.substring(0, params.length() - 1));
                    return false;
                } else {
                    response.getOutputStream().print("<script>window.location.href = '/creaWechatSys/login.jsp'</script>");
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return true;
        }
	}
}
