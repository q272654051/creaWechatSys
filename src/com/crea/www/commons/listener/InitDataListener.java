package com.crea.www.commons.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import com.crea.www.service.IUserService;
import com.crea.www.vo.User;

/**
 * @author djx
 * @date 2015-8-17
 * @description
 */
public class InitDataListener implements InitializingBean, ServletContextAware{
    @Autowired
    private IUserService userService;
    @Override
    public void setServletContext(ServletContext context) {
        //获取所有用户
       /* Map<String,User> user_map = new HashMap<String,User>();
        Map<String,User> userId_map = new HashMap<String,User>();
        List<User> user_list = userService.findByHQLQuery("from User");
        for(User user : user_list){
            user_map.put(user.getUserName()+"", user);
            userId_map.put(user.getId()+"", user);
        }
        context.setAttribute("user_map", user_map);
        context.setAttribute("userId_map", userId_map);
        context.setAttribute("user_list", user_list);*/
        
    }
    
    
    @Override
    public void afterPropertiesSet() throws Exception {
        
    }
}