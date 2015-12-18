package com.crea.www.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crea.www.commons.dao.BaseDao;
import com.crea.www.commons.util.Pager;
import com.crea.www.service.ITextService;
import com.crea.www.vo.Text;

/**
 * @author djx
 * @date 2015-12-17
 * @description
 */
@Service
@Transactional
public class TextServiceImpl  extends BaseDao<Text> implements ITextService{

    
    public TextServiceImpl() {
        this.setClazz(Text.class);
    }
	
  //save
    @Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
    public boolean saveupdateText(Text entity) {
        return this.saveEntity(entity);
    }
    //delete
    @Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
    public boolean deleteTextById(Serializable id) {
        return this.deleteEntityById(id);
    }
    //update
    @Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
    public boolean updateText(Text entity) {
        //这里的验证条件自己添加吧
        return this.updateEntity(entity);
    }
    //find
    @Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
    public Text findTextById(Serializable id) {
        return this.findById(id);
    }

	@Override
	public List<Text> findAllText() {
		String sql = "from Text";
		List<Text> list = findByHQLQuery(sql);
		return list;
	}

	@Transactional(readOnly=true)
    public Pager findBySQLQuery(String textId, Pager pager) {
        StringBuffer sb=new StringBuffer("from Text t where 1=1 ");
        ArrayList values=new ArrayList();
        if(textId!=null){
                sb.append(" and t.id=? ");
                values.add(textId);
        }
        return    this.findByHQLQuery(sb.toString(), values.toArray(), pager);
    }
	
	@Transactional(readOnly=true)
    public Pager findBySQLQuery(Pager pager) {
        StringBuffer sb=new StringBuffer("from Text t where 1=1 ");
        return    this.findByHQLQuery(sb.toString(), null, pager);
    }
}
