package com.crea.www.service;

import java.io.Serializable;
import java.util.List;

import com.crea.www.commons.dao.IBaseDAO;
import com.crea.www.commons.util.Pager;
import com.crea.www.vo.KeyWord;

/**
 * @author djx
 * @date 2015-12-17
 * @description
 */
public interface IKeyWordService extends IBaseDAO<KeyWord, Serializable>{

	//保存
	public boolean saveupdateKeyWord (KeyWord entity);
	//删除
	public boolean deleteKeyWordById (Serializable id);
	//修改
	public boolean updateKeyWord (KeyWord id);
	
	public KeyWord findKeyWordById(Serializable id);
	//查询
	public List<KeyWord> findAllKeyWord ();
	
	public List<KeyWord> findListByKeyWord(String keyWord);
	
	public Pager findBySQLQuery(Pager pager);
}
