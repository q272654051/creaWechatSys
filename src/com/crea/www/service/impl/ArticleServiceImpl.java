package com.crea.www.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crea.www.commons.dao.BaseDao;
import com.crea.www.commons.util.Pager;
import com.crea.www.service.IArticleService;
import com.crea.www.vo.Article;
import com.crea.www.vo.Text;

/**
 * @author djx
 * @date 2015-12-17
 * @description
 */
@Service
@Transactional
public class ArticleServiceImpl  extends BaseDao<Article> implements IArticleService{

    
    public ArticleServiceImpl() {
        this.setClazz(Article.class);
    }
	@Override
	public boolean saveArticle(Article entity) {
		// TODO Auto-generated method stub
		return this.saveArticle(entity);
		
	}

	@Override
	public boolean updateArticle(Article id) {
		// TODO Auto-generated method stub
		return this.updateArticle(id);
	}

	@Override
	public boolean deleteArticleById(Serializable id) {
		// TODO Auto-generated method stub
		return this.deleteEntityById(id);
	}

	@Override
	public List<Article> findArticle() {
		// TODO Auto-generated method stub
		return this.findArticle();
	}

	@Transactional(readOnly=true)
    public Pager findBySQLQuery(String articleId, Pager pager) {
        StringBuffer sb=new StringBuffer("from Article t where 1=1 ");
        ArrayList values=new ArrayList();
        if(articleId!=null){
                sb.append(" and t.id=? ");
                values.add(articleId);
        }
        return    this.findByHQLQuery(sb.toString(), values.toArray(), pager);
    }
	
	@Transactional(readOnly=true)
    public Pager findBySQLQuery(Pager pager) {
        StringBuffer sb=new StringBuffer("from Article t where 1=1 ");
        return    this.findByHQLQuery(sb.toString(), null, pager);
    }
}
