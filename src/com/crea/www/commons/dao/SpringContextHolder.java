/**
 * 
 */
package com.crea.www.commons.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author djx
 * @date 2015-8-12
 * @description
 */
public class SpringContextHolder<T> {
	private SpringContextHolder(){}
	public SpringContextHolder<T> getSpringContextHolder(){
		return new SpringContextHolder<T>();
	}
	public T getBean(String beanName){
		ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml"); 
		T obj = (T) context.getBean(beanName);
		return obj;
	}
}
