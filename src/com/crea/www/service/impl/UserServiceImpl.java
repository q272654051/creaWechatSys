package com.crea.www.service.impl;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crea.www.commons.dao.BaseDao;
import com.crea.www.service.IUserService;
import com.crea.www.vo.User;

/**
 * @author djx
 * @date 2015-8-17
 * @description
 */
@Service
@Transactional
public class UserServiceImpl  extends BaseDao<User> implements IUserService{

    
    public UserServiceImpl() {
        this.setClazz(User.class);
    }
	@Override
	public boolean saveUser(User entity) {
		// TODO Auto-generated method stub
		return this.saveUser(entity);
		
	}

	@Override
	public boolean updateUser(User id) {
		// TODO Auto-generated method stub
		return this.updateUser(id);
	}

	@Override
	public boolean deleteUser(User entity) {
		// TODO Auto-generated method stub
		return this.deleteUser(entity);
	}

	@Override
	public List<User> findUser() {
		// TODO Auto-generated method stub
		return this.findUser();
	}

	@Override
	public List<User> findUserByName(String name) {
		String sql = "from User u where u.userName = '"+name+"'";
		List<User> list = findByHQLQuery(sql);
		return list;
	}
	
	@Override
	public List<User> findUserByNameAndPwd(String name, String pwd) {
		String sql = "from User u where u.userName = '"+name+"' and pwd='"+pwd+"'";
		List<User> list = findByHQLQuery(sql);
		return list;
	}

}
