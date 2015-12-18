package com.crea.www.service;

import java.io.Serializable;
import java.util.List;

import com.crea.www.commons.dao.IBaseDAO;
import com.crea.www.commons.util.Pager;
import com.crea.www.vo.Article;
import com.crea.www.vo.Text;

/**
 * @author djx
 * @date 2015-8-17
 * @description
 */
public interface IArticleService extends IBaseDAO<Article, Serializable>{

	//保存
	public boolean saveArticle (Article entity);
	//修改
	public boolean updateArticle (Article id);
	//删除
	public boolean deleteArticleById (Serializable id);
	//查询
	public List<Article> findArticle ();
	
	public Pager findBySQLQuery(String textId, Pager pager);
	
	public Pager findBySQLQuery(Pager pager);
	
}
