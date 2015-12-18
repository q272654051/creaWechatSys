package com.crea.www.commons.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crea.www.commons.util.Pager;
import com.mysql.jdbc.PreparedStatement;

/**
 * 封装dao 使其service层直接继承实现即可
 *
 * @param <T>
 * @author hdfs
 */
@Transactional
@Repository
public class BaseDao<T> implements IBaseDAO<T, Serializable> {
    public BaseDao() {

    }

    // 日志记录
    Logger logger = Logger.getLogger(BaseDao.class);
    @Resource
    private SessionFactory sessionFactory;
    // 使用反射获取实体对象
    public Class<T> entityClass;

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setClazz(Class<T> clazzToSet) {
        this.entityClass = clazzToSet;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {

        return sessionFactory;
    }

    /**
     * 放弃使用原始的session方法
     *
     * @return
     */
    public Session getSession() {
        return (Session) sessionFactory.getCurrentSession();
    	//return (Session) sessionFactory.openSession();
    }

    /**
     * 交给spring来处理,给提供一个方便有的人使用,
     *
     * @param sessionFactory
     * @return
     */
    public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }

    @SuppressWarnings("unchecked")
    public T findById(Serializable id) {
        return (T) this.getSession().get(getEntityClass(), id);
    }

    @SuppressWarnings("unchecked")
    public T findById(Class c, Serializable id) {
        return (T) this.getSession().get(c, id);
    }

    public List<T> loadAll(Pager pager) {
        Query query = this.getSession().createQuery(
                " from " + entityClass.getName());
        query.setCacheable(true);//添加缓存
        return query.list();
    }

    public T loadById(Serializable id) {
        return (T) this.getSession().load(entityClass, id);
    }

    public Boolean saveEntity(T entity) {
        boolean con = false;
        try {
            this.getSession().save(entity);
            this.flush();
            con = true;
        } catch (Exception e) {
            e.printStackTrace();
            con = false;
        }
        return con;
    }


    public Boolean saveOrUpdateAll(Collection<T> entities) {
        boolean con = false;
        try {
            for (Iterator iterator = entities.iterator(); iterator.hasNext(); ) {
                T t = (T) iterator.next();
                this.getSession().saveOrUpdate(t);
                this.flush();
            }
            con = true;
        } catch (Exception e) {
            e.printStackTrace();
            con = false;
        }
        return con;
    }

    public Boolean saveOrUpdate(T entity) {
        boolean con = false;
        try {
            this.getSession().saveOrUpdate(entity);
            this.flush();
            con = true;
        } catch (Exception e) {
            e.printStackTrace();
            con = false;
        }
        return con;
    }

    public Boolean updateEntity(T entity) {
        boolean con = false;
        try {
            this.getSession().update(entity);
            this.flush();
            con = true;
        } catch (Exception e) {
            e.printStackTrace();
            con = false;
        }
        return con;
    }

    public Long countByHql(String hql, Object... values) {
        int flag = hql.indexOf("from");
        String newHql = "select count(*)  " + hql.substring(flag, hql.length());
        Query query = this.getSession().createQuery(newHql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return Long.valueOf(query.uniqueResult().toString());
    }

    public Long SQLcount(String sql, Object... values) {
        int flag = sql.indexOf("from");
        String newHql = "select count(*)  " + sql.substring(flag, sql.length());
        Query query = this.getSession().createSQLQuery(newHql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return Long.valueOf(query.uniqueResult().toString());
    }

    public Boolean deleteAll(Collection<T> entities) {
        boolean con = false;
        try {
            for (Iterator iterator = entities.iterator(); iterator.hasNext(); ) {
                T t = (T) iterator.next();
                Session session = this.getSession();
                session.delete(session.load(entityClass, t.toString()));
                this.flush();
            }
            con = true;
        } catch (Exception e) {
            e.printStackTrace();
            con = false;
        }
        return con;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean deleteEntity(T entity) {
        boolean con = false;
        try {
            this.getSession().delete(entity);
            this.flush();
            con = true;
        } catch (Exception e) {
            e.printStackTrace();
            con = false;
        }
        return con;
    }

    //根据ID删除数据
    public Boolean deleteEntityById(Serializable id) {
        boolean con = false;
        try {
            Session session = this.getSession();
            session.delete(this.findById(id));
            this.flush();
            con = true;
        } catch (Exception e) {
            e.printStackTrace();
            con = false;
        }
        return con;
    }

    public Pager findByHQLQuery(String sql, Pager pager) {
        Query query = this.getSession().createQuery(sql);
        Integer count = countByHql(sql, null).intValue();
        query.setFirstResult(pager.getStart());
        query.setMaxResults(pager.getPageSize());
        pager.setTotalRow(count);
        pager.setList(query.list());
        return pager;
    }

    public Pager findByHQLQuery(String sql, Object[] values, Pager pager) {
        Query query = this.getSession().createQuery(sql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        Integer count = countByHql(sql, values).intValue();
        query.setFirstResult(pager.getStart());
        query.setMaxResults(pager.getPageSize());
        pager.setTotalRow(count);
        pager.setList(query.list());
        return pager;
    }

    public Pager findBySQLQuery(String sql, Pager pager) {
        Query query = this.getSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Integer count = SQLcount(sql, null).intValue();
        query.setFirstResult(pager.getStart());
        query.setMaxResults(pager.getPageSize());
        pager.setTotalRow(count);
        pager.setList(query.list());
        return pager;
    }

    public List<T> findByHQLQuery(String sql) {
        Query query = this.getSession().createQuery(sql);
        return query.list();
    }

    public Pager findBySQLQuery(String sql, Object[] values, Pager pager) {
        Query query = this.getSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        Integer count = SQLcount(sql, values).intValue();
        query.setFirstResult(pager.getStart());
        query.setMaxResults(pager.getPageSize());
        pager.setTotalRow(count);
        pager.setList(query.list());
        return pager;
    }

    public void flush() {
        this.getSession().flush();
    }

    public void clear() {
        this.getSession().clear();
    }

    public void evict(Object entity) {
        this.getSession().evict(entity);
    }

    /**
     * 设置每行批处理参数
     *
     * @param ps
     * @param pos  ?占位符索引，从0开始
     * @param data
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    private void setParameter(PreparedStatement ps, int pos, Object data)
            throws SQLException {
        if (data == null) {
            ps.setNull(pos + 1, Types.VARCHAR);
            return;
        }
        Class dataCls = data.getClass();
        if (String.class.equals(dataCls)) {
            ps.setString(pos + 1, (String) data);
        } else if (boolean.class.equals(dataCls)) {
            ps.setBoolean(pos + 1, ((Boolean) data));
        } else if (int.class.equals(dataCls)) {
            ps.setInt(pos + 1, (Integer) data);
        } else if (double.class.equals(dataCls)) {
            ps.setDouble(pos + 1, (Double) data);
        } else if (Date.class.equals(dataCls)) {
            Date val = (Date) data;
            ps.setTimestamp(pos + 1, new Timestamp(val.getTime()));
        } else if (BigDecimal.class.equals(dataCls)) {
            ps.setBigDecimal(pos + 1, (BigDecimal) data);
        } else { // 未知类型

            ps.setObject(pos + 1, data);
        }

    }

    public List findOldBySql(final String sql, final RowMapper map,
                             final Object... values) {
        final List list = new ArrayList();
        Work jdbcWork = new Work() {
            public void execute(Connection connection) throws SQLException {
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                    ps = (PreparedStatement) connection.prepareStatement(sql);
                    for (int i = 0; i < values.length; i++) {
                        setParameter(ps, i, values[i]);
                    }
                    rs = ps.executeQuery();
                    int index = 0;
                    while (rs.next()) {
                        Object obj = map.mapRow(rs, index++);
                        list.add(obj);
                    }
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }

            }
        };
        this.getSession().doWork(jdbcWork);
        return list;
    }

    public boolean updateBatch(String sql) {
        boolean con = false;
        try {
            int flag = this.getSession().createSQLQuery(sql).executeUpdate();
            if (flag > 0) {
                con = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            con = false;
        }
        return con;
    }

    public List<T> findAlllist(String sql) {
        Query query = this.getSession().createSQLQuery(sql).addEntity(entityClass);
        return query.list();
    }

}
