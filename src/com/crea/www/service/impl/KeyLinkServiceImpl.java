package com.crea.www.service.impl;

import java.io.Serializable;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crea.www.commons.dao.BaseDao;
import com.crea.www.service.IKeyLinkService;
import com.crea.www.vo.KeyLink;

/**
 * @author djx
 * @date 2015-12-17
 * @description
 */
@Service
@Transactional
public class KeyLinkServiceImpl  extends BaseDao<KeyLink> implements IKeyLinkService{

    
    public KeyLinkServiceImpl() {
        this.setClazz(KeyLink.class);
    }
	
  //save
    @Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
    public boolean saveupdateKeyLink(KeyLink entity) {
        return this.saveEntity(entity);
    }
    //delete
    @Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
    public boolean deleteKeyLinkById(Serializable id) {
        return this.deleteEntityById(id);
    }
    //update
    @Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
    public boolean updateKeyLink(KeyLink entity) {
        //这里的验证条件自己添加吧
        return this.updateEntity(entity);
    }
    //find
    @Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
    public KeyLink findKeyLinkById(Serializable id) {
        return this.findById(id);
    }

	@Override
	public List<KeyLink> findAllKeyLink() {
		String sql = "from KeyLink";
		List<KeyLink> list = findByHQLQuery(sql);
		return list;
	}

	public List<KeyLink> findKeyLinkBykeyWordId (String keyWordId){
		String sql = "from KeyLink where keyWordId ='" + keyWordId +"'";
		List<KeyLink> list = findByHQLQuery(sql);
		return list;
	}
}
