package com.crea.www.service.impl;

import java.io.Serializable;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crea.www.commons.dao.BaseDao;
import com.crea.www.commons.util.Pager;
import com.crea.www.service.IKeyWordService;
import com.crea.www.vo.KeyWord;

/**
 * @author djx
 * @date 2015-12-17
 * @description
 */
@Service
@Transactional
public class KeyWordServiceImpl  extends BaseDao<KeyWord> implements IKeyWordService{

    
    public KeyWordServiceImpl() {
        this.setClazz(KeyWord.class);
    }
	
  //save
    @Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
    public boolean saveupdateKeyWord(KeyWord entity) {
        return this.saveEntity(entity);
    }
    //delete
    @Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
    public boolean deleteKeyWordById(Serializable id) {
        return this.deleteEntityById(id);
    }
    //update
    @Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
    public boolean updateKeyWord(KeyWord entity) {
        //这里的验证条件自己添加吧
        return this.updateEntity(entity);
    }
    //find
    @Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
    public KeyWord findKeyWordById(Serializable id) {
        return this.findById(id);
    }

	@Override
	public List<KeyWord> findAllKeyWord() {
		String sql = "from KeyWord";
		List<KeyWord> list = findByHQLQuery(sql);
		return list;
	}

	@Override
	public List<KeyWord> findListByKeyWord(String keyWord) {
		String sql = "from KeyWord where keyWord='"+keyWord+"'";
		List<KeyWord> list = findByHQLQuery(sql);
		return list;
	}
	
	@Transactional(readOnly=true)
    public Pager findBySQLQuery(Pager pager) {
        StringBuffer sb=new StringBuffer("from KeyWord where 1=1 ");
        return    this.findByHQLQuery(sb.toString(), null, pager);
    }
}
