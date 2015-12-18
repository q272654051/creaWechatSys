package com.crea.www.commons.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.crea.www.commons.util.Pager;

/**
 * dao接口类
 * 
 * @author djx
 * @date:2015年4月22日15:18:04
 */
public interface IBaseDAO<T, ID extends Serializable> {

	/***************************************************************************
	 * ------------------------------hql语句块-------------------------------- *
	 **************************************************************************/
	/**
	 * 获取指定id值get方式
	 * 
	 * @param id
	 * @return T 如果没有查询到记录，load会报异常，get返回null load可以使用实体对象的延迟加载，get不能使用延迟加载
	 */
	public T findById(ID id);

	/**
	 * 提供指定类加载方式
	 * 
	 * @param c
	 * @param id
	 * @return T
	 */
	public T findById(Class c, ID id);

	// -------------使用load方式 如果没有查询到记录，load会报异常，get返回null

	public T loadById(ID id);

	/**
	 * load加载分页数据
	 * 
	 * @return
	 */
	public List<T> loadAll(Pager pager);

	/**
	 * 添加操作
	 * 
	 * @param entity
	 */
	public Boolean saveEntity(T entity);

	/**
	 * 添加和修改操作
	 * @param entity
	 * @return
	 */
	public Boolean saveOrUpdate(T entity);

	/**
	 * 跟新实体操作
	 * 
	 * @param entity
	 */
	public Boolean updateEntity(T entity);

	/**
	 * 保存或者添加操作
	 * 
	 * @param entities
	 */
	public Boolean saveOrUpdateAll(Collection<T> entities);

	/**
	 * 按照条件删除操作(但是必须有id才行)
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public Boolean deleteEntity(T entity);

	/**
	 * 删除指定id的数据
	 * 
	 * @param id
	 */
	public Boolean deleteEntityById(ID id);

	/**
	 * 删除满足条件的集合数据
	 * 
	 * @param entities
	 *            实体集合
	 */
	public Boolean deleteAll(Collection<T> entities);

	/**
	 * 使用hql语句查询数据
	 * 
	 * @param sql
	 *            语句
	 * @param pager
	 *            分页工具
	 * @return
	 */
	public Pager findByHQLQuery(String sql, Pager pager);

	/**
	 * 使用hql语句查询数据,带集合参数
	 * 
	 * @param sql
	 *            语句
	 * @param pager
	 *            分页工具
	 * @return
	 */
	public Pager findByHQLQuery(String sql, Object[] values, Pager pager);

	/**
	 * 使用hql语句查询带集合参数,获取总数
	 * 
	 * @param sql
	 *            语句
	 * @param pager
	 *            分页工具
	 * @return
	 */
	public Long countByHql(String hql, Object... values);

	/***************************************************************************
	 * ------------------------------sql语句-------------------------------- *
	 **************************************************************************/
	/**
	 * 通过sql语句查询数据
	 * 
	 * @param queryName
	 * @return
	 */
	public Pager findBySQLQuery(String sql, Pager pager);

	/**
	 * 使用hql语句查询数据List
	 *
	 * @param sql
	 *            语句
	 * @return
	 */
	public List<T> findByHQLQuery(String sql);

	/**
	 * 通过sql语句查询数据
	 * 
	 * @param sql
	 * @param values
	 *            参数集合
	 * @return
	 */
	public Pager findBySQLQuery(String sql, Object[] values, Pager pager);

	/**
	 * sql获取sql语句条数
	 * 
	 * @param entity
	 * @param lockMode
	 */
	public Long SQLcount(String hql, Object... values);

	/**
	 * 使用原生的sql语句查询方式
	 * 
	 * @param sql
	 * @param map
	 * @param values
	 * @return
	 */
	public List findOldBySql(final String sql, final RowMapper map,
			final Object... values);

	
	public boolean updateBatch(final String sql);
	
	/**
	 * 刷新缓存
	 */
	public void flush();

	/**
	 * 清楚缓存
	 * 
	 * void
	 */
	public void clear();

	// 清除缓存(指定对象)
	public void evict(Object entity);

	/**
	 * 根据SQl获取对于的对象集合
	 * @param sql
	 * @return
	 */
	public List<T> findAlllist(String sql);

}
