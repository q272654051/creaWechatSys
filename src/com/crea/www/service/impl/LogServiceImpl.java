package com.crea.www.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crea.www.commons.dao.BaseDao;
import com.crea.www.commons.util.FormatDateUtil;
import com.crea.www.commons.util.Pager;
import com.crea.www.service.ILogService;
import com.crea.www.vo.Log;


/**
 * @author dengbojing
 * @date 2015-8-12
 * @description
 */


@Service
@Transactional
public class LogServiceImpl extends BaseDao<Log> implements ILogService{
	
	
	public LogServiceImpl(){
		this.setClazz(Log.class);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void add(Log log) {
		this.saveEntity(log);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public Pager findByHQLQuery(Log entity, Pager pager) {
	    String sql = " from Log l where 1=1 ";
	    //操作人
	    if(StringUtils.isNotBlank((entity.getUserid()+"")) && entity.getUserid() != null){
	        sql += " and l.userid = "+entity.getUserid();
	    }
	    //类型
	    if(StringUtils.isNotBlank(entity.getType())){
	    	sql += "and l.type= "+entity.getType();
        }
	    //编辑时间
	   if(StringUtils.isNotBlank(FormatDateUtil.getfirstDate())&&StringUtils.isNotBlank(FormatDateUtil.getlastDate())){
		   sql += " and l.createdate between '"+FormatDateUtil.getfirstDate()+"' and '"+FormatDateUtil.getlastDate()+"'";
	   }
		return this.findByHQLQuery(sql, null, pager);
		
	}
}
