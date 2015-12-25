package com.crea.www.service;

import java.io.Serializable;
import java.util.List;

import com.crea.www.commons.dao.IBaseDAO;
import com.crea.www.vo.KeyLink;

/**
 * @author djx
 * @date 2015-8-17
 * @description
 */
public interface IKeyLinkService extends IBaseDAO<KeyLink, Serializable>{

	//保存
	public boolean saveupdateKeyLink (KeyLink entity);
	//删除
	public boolean deleteKeyLinkById (Serializable id);
	//删除
	public boolean deleteKeyLink (KeyLink entity);
	//修改
	public boolean updateKeyLink (KeyLink entity);
	
	public KeyLink findKeyLinkById(Serializable id);
	//查询
	public List<KeyLink> findAllKeyLink ();
	
	public List<KeyLink> findKeyLinkBykeyWordId (String keyWordId);
	
}
