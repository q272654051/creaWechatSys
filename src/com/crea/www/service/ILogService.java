/**
 * 
 */
package com.crea.www.service;

import java.io.Serializable;

import com.crea.www.commons.dao.IBaseDAO;
import com.crea.www.commons.util.Pager;
import com.crea.www.vo.Log;


/**
 * @author djx
 * @date 2015-8-12
 * @description
 */
public interface ILogService extends IBaseDAO<Log, Serializable>{
	public void add(Log log);
	public Pager findByHQLQuery(Log entity, Pager pager);
}
