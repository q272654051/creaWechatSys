package com.crea.www.service;

import java.io.Serializable;
import java.util.List;

import com.crea.www.commons.dao.IBaseDAO;
import com.crea.www.commons.util.Pager;
import com.crea.www.vo.Text;

/**
 * @author djx
 * @date 2015-8-17
 * @description
 */
public interface ITextService extends IBaseDAO<Text, Serializable>{

	//保存
	public boolean saveupdateText (Text entity);
	//删除
	public boolean deleteTextById (Serializable id);
	//修改
	public boolean updateText (Text id);
	
	public Text findTextById(Serializable id);
	//查询
	public List<Text> findAllText ();
	
	public Pager findBySQLQuery(String textId, Pager pager);
	
	public Pager findBySQLQuery(Pager pager);
}
