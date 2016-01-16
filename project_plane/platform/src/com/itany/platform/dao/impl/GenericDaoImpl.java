//package com.itany.platform.dao.impl;
//
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.math.BigDecimal;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.sql.Types;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Query;
//import org.hibernate.SQLQuery;
//import org.hibernate.ScrollableResults;
//import org.hibernate.Session;
//import org.hibernate.engine.NamedSQLQueryDefinition;
//import org.hibernate.impl.SessionFactoryImpl;
//import org.hibernate.jdbc.Work;
//import org.springframework.orm.hibernate3.HibernateCallback;
//import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
//
//import com.itany.platform.dao.IGenericDao;
//import com.itany.platform.util.PageResults;
//import com.itany.platform.util.RowMapper;
//
///**
// * <一句话功能简述>
// * <功能详细描述>
// * 
// * @author 崔译
// * @version [V1.00, 2012-11-19]
// * @see [相关类/方法]
// * @since V1.00
// */
//public class GenericDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements IGenericDao<T, PK>
//{
//    protected Class<T> entityClass;
//    
//    public GenericDaoImpl()
//    {
//        Type t = getClass().getGenericSuperclass();
//        if (t instanceof ParameterizedType)
//        {
//            entityClass = (Class<T>)((ParameterizedType)t).getActualTypeArguments()[0];
//        }
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param hql
//     * @param values
//     * @return
//     * @see com.itany.net.dao.IBaseDao#countByHql(java.lang.String, java.lang.Object[])
//     */
//    public Long countByHql(final String hql, final Object... values)
//    {
//        HibernateCallback<Long> callback = new HibernateCallback<Long>()
//        {
//            
//            public Long doInHibernate(Session session)
//                throws HibernateException, SQLException
//            {
//                PageResults retValue = new PageResults();
//                Query query = session.createQuery(hql);
//                for (int i = 0; values != null && i < values.length; i++)
//                {
//                    query.setParameter(i, values[i]);
//                }
//                return (Long)query.uniqueResult();
//            }
//            
//        };
//        return getHibernateTemplate().execute(callback);
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param entity
//     * @see com.itany.net.dao.IBaseDao#delete(java.lang.Object)
//     */
//    public void delete(Object entity)
//    {
//        getHibernateTemplate().delete(entity);
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param entityClass
//     * @param Id
//     * @see com.itany.net.dao.IBaseDao#delete(java.lang.Class, java.io.Serializable)
//     */
//    public void delete(Class entityClass, Serializable Id)
//    {
//        getHibernateTemplate().delete(getHibernateTemplate().get(entityClass, Id));
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param Id
//     * @return
//     * @see com.itany.net.dao.IBaseDao#deleteById(java.io.Serializable)
//     */
//    public boolean deleteById(PK Id)
//    {
//        Object entity = read(Id);
//        if (entity == null)
//        {
//            return false;
//        }
//        delete(entity);
//        return true;
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param hql
//     * @param values
//     * @see com.itany.net.dao.IBaseDao#deleteObjects(java.lang.String, java.lang.Object[])
//     */
//    public void deleteObjects(final String hql, final Object... values)
//    {
//        HibernateCallback<Integer> callback = new HibernateCallback<Integer>()
//        {
//            
//            public Integer doInHibernate(Session session)
//                throws HibernateException, SQLException
//            {
//                Query query = session.createQuery(hql);
//                if (values != null)
//                {
//                    for (int i = 0; i < values.length; i++)
//                    {
//                        query.setParameter(i, values[i]);
//                    }
//                }
//                query.executeUpdate();
//                return null;
//                
//            }
//        };
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param hql
//     * @param values
//     * @return
//     * @see com.itany.net.dao.IBaseDao#executeByHql(java.lang.String, java.lang.Object[])
//     */
//    public int executeByHql(final String hql, final Object... values)
//    {
//        HibernateCallback<Integer> callback = new HibernateCallback<Integer>()
//        {
//            
//            public Integer doInHibernate(Session session)
//                throws HibernateException, SQLException
//            {
//                Query query = session.createQuery(hql);
//                if (values != null)
//                {
//                    for (int i = 0; i < values.length; i++)
//                    {
//                        query.setParameter(i, values[i]);
//                    }
//                }
//                return query.executeUpdate();
//                
//            }
//        };
//        
//        return getHibernateTemplate().execute(callback);
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param hql
//     * @param pageNo
//     * @param pageSize
//     * @return
//     * @see com.itany.net.dao.IBaseDao#findByHql(java.lang.String, int, int)
//     */
//    public List findByHql(String hql, int pageNo, int pageSize)
//    {
//        Session session = getSession();
//        Query query = session.createQuery(hql);
//        query.setFirstResult((pageNo - 1) * pageSize);
//        query.setMaxResults(pageSize);
//        List lst = query.list();
//        releaseSession(session);
//        return lst;
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param sql
//     * @param pageNo
//     * @param pageSize
//     * @return
//     * @see com.itany.net.dao.IBaseDao#findBySql(java.lang.String, int, int)
//     */
//    public List findBySql(String sql, int pageNo, int pageSize)
//    {
//        Session session = getSession();
//        Query query = session.createSQLQuery(sql);
//        query.setFirstResult((pageNo - 1) * pageSize);
//        query.setMaxResults(pageSize);
//        List lst = query.list();
//        releaseSession(session);
//        return lst;
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param sqlName
//     * @param values
//     * @return
//     * @see com.itany.net.dao.IBaseDao#findBySql(java.lang.String, java.lang.Object[])
//     */
//    public List findBySql(final String sqlName, final Object... values)
//    {
//        HibernateCallback<List> callback = new HibernateCallback<List>()
//        {
//            
//            public List doInHibernate(Session session)
//                throws HibernateException, SQLException
//            {
//                Query query = session.getNamedQuery(sqlName);
//                if (values != null)
//                {
//                    for (int i = 0; i < values.length; i++)
//                    {
//                        query.setParameter(i, values[i]);
//                    }
//                }
//                return query.list();
//            }
//        };
//        return getHibernateTemplate().execute(callback);
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param hql
//     * @param values
//     * @return
//     * @see com.itany.net.dao.IBaseDao#findListByHql(java.lang.String, java.lang.Object[])
//     */
//    public List findListByHql(final String hql, final Object... values)
//    {
//        HibernateCallback<List> callback = new HibernateCallback<List>()
//        {
//            
//            public List doInHibernate(Session session)
//                throws HibernateException, SQLException
//            {
//                Query query = session.createQuery(hql);
//                if (values != null)
//                {
//                    for (int i = 0; i < values.length; i++)
//                    {
//                        query.setParameter(i, values[i]);
//                    }
//                }
//                return query.list();
//                
//            }
//        };
//        return getHibernateTemplate().execute(callback);
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param sqlName
//     * @param map
//     * @param values
//     * @return
//     * @see com.itany.net.dao.IBaseDao#findListByNamedSql(java.lang.String, com.itany.net.util.RowMapper,
//     *      java.lang.Object[])
//     */
//    public List findListByNamedSql(final String sqlName, final RowMapper map, final Object... values)
//    {
//        final List list = new ArrayList();
//        HibernateCallback callback = new HibernateCallback()
//        {
//            public Object doInHibernate(Session session)
//                throws HibernateException, SQLException
//            {
//                final String sql = getNamedSql(session, sqlName);
//                // 执行JDBC的数据批量保存
//                Work jdbcWork = new Work()
//                {
//                    public void execute(Connection connection)
//                        throws SQLException
//                    {
//                        PreparedStatement ps = null;
//                        ResultSet rs = null;
//                        try
//                        {
//                            ps = connection.prepareStatement(sql);
//                            for (int i = 0; i < values.length; i++)
//                            {
//                                setParameter(ps, i, values[i]);
//                                
//                            }
//                            
//                            rs = ps.executeQuery();
//                            int index = 0;
//                            while (rs.next())
//                            {
//                                Object obj = map.mapRow(rs, index);
//                                list.add(obj);
//                                index++;
//                            }
//                        }
//                        finally
//                        {
//                            if (rs != null)
//                            {
//                                rs.close();
//                                
//                            }
//                            if (ps != null)
//                            {
//                                ps.close();
//                            }
//                        }
//                        
//                    }
//                };
//                
//                session.doWork(jdbcWork);
//                return null;
//            }
//        };
//        getHibernateTemplate().execute(callback);
//        return list;
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param sql
//     * @param values
//     * @return
//     * @see com.itany.net.dao.IBaseDao#findListBySql(java.lang.String, java.lang.Object[])
//     */
//    public List findListBySql(final String sql, final Object... values)
//    {
//        HibernateCallback<List> callback = new HibernateCallback<List>()
//        {
//            public List doInHibernate(Session session)
//                throws HibernateException, SQLException
//            {
//                SQLQuery query = session.createSQLQuery(sql);
//                if (values != null)
//                {
//                    for (int i = 0; i < values.length; i++)
//                    {
//                        query.setParameter(i, values[i]);
//                    }
//                }
//                return query.list();
//            }
//        };
//        return getHibernateTemplate().execute(callback);
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param sql
//     * @param map
//     * @param values
//     * @return
//     * @see com.itany.net.dao.IBaseDao#findListBySql(java.lang.String, com.itany.net.util.RowMapper, java.lang.Object[])
//     */
//    public List findListBySql(final String sql, final RowMapper map, final Object... values)
//    {
//        final List list = new ArrayList();
//        HibernateCallback callback = new HibernateCallback()
//        {
//            public Object doInHibernate(Session session)
//                throws HibernateException, SQLException
//            {
//                // 执行JDBC的数据批量保存
//                Work jdbcWork = new Work()
//                {
//                    public void execute(Connection connection)
//                        throws SQLException
//                    {
//                        
//                        PreparedStatement ps = null;
//                        ResultSet rs = null;
//                        try
//                        {
//                            ps = connection.prepareStatement(sql);
//                            for (int i = 0; i < values.length; i++)
//                            {
//                                setParameter(ps, i, values[i]);
//                                
//                            }
//                            
//                            rs = ps.executeQuery();
//                            int index = 0;
//                            while (rs.next())
//                            {
//                                Object obj = map.mapRow(rs, index++);
//                                list.add(obj);
//                                
//                            }
//                        }
//                        finally
//                        {
//                            if (rs != null)
//                            {
//                                rs.close();
//                                
//                            }
//                            if (ps != null)
//                            {
//                                ps.close();
//                            }
//                        }
//                    }
//                };
//                
//                session.doWork(jdbcWork);
//                return null;
//            }
//        };
//        getHibernateTemplate().execute(callback);
//        return list;
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param hql
//     * @param values
//     * @return
//     * @see com.itany.net.dao.IBaseDao#findObject(java.lang.String, java.lang.Object[])
//     */
//    public Object findObject(String hql, Object... values)
//    {
//        List list = null;
//        Object result = null;
//        list = getHibernateTemplate().find(hql, values);
//        if ((list != null) && (list.isEmpty() == false))
//        {
//            result = list.get(0);
//        }
//        return result;
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param hql
//     * @param countHql
//     * @param countParams
//     * @param pageNo
//     * @param pageSize
//     * @param values
//     * @return
//     * @see com.itany.net.dao.IBaseDao#findPageByFetchedHql(java.lang.String, java.lang.String, java.lang.Object[], int,
//     *      int, java.lang.Object[])
//     */
//    public PageResults findPageByFetchedHql(final String hql, final String countHql, final int pageNo, final int pageSize, final Object... values)
//    {
//        HibernateCallback<PageResults> callback = new HibernateCallback<PageResults>()
//        {
//            
//            public PageResults doInHibernate(Session session)
//                throws HibernateException, SQLException
//            {
//                PageResults retValue = new PageResults();
//                Query query = session.createQuery(hql);
//                if (values != null)
//                {
//                    for (int i = 0; i < values.length; i++)
//                    {
//                        query.setParameter(i, values[i]);
//                    }
//                }
//                int curpage = pageNo > 1 ? pageNo : 1;
//                retValue.setCurrentPage(curpage);
//                retValue.setPageSize(pageSize);
//                
//                if (countHql == null)
//                {
//                    ScrollableResults results = query.scroll();
//                    results.last();
//                    retValue.setTotalCount(results.getRowNumber() + 1);// 设置总记录数
//                }
//                else
//                {
//                    Long count = countByHql(countHql, values);
//                    retValue.setTotalCount(count.intValue());
//                }
//                
//                retValue.resetPageNo();
//                @SuppressWarnings("unchecked")
//                List<T> itemList = query.setFirstResult((curpage - 1) * pageSize).setMaxResults(pageSize).list();
//                if (itemList == null)
//                {
//                    itemList = new ArrayList<T>();
//                }
//                retValue.setResults(itemList);
//                
//                return retValue;
//            }
//            
//        };
//        return getHibernateTemplate().execute(callback);
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param hql
//     * @param pageNo
//     * @param pageSize
//     * @param values
//     * @return
//     * @see com.itany.net.dao.IBaseDao#findPageByHql(java.lang.String, int, int, java.lang.Object[])
//     */
//    public PageResults findPageByHql(String hql, int pageNo, int pageSize, Object... values)
//    {
//        return findPageByFetchedHql(hql, null, pageNo, pageSize, values);
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param sql
//     * @param pageNo
//     * @param pageSize
//     * @param values
//     * @return
//     * @see com.itany.net.dao.IBaseDao#findPageBySql(java.lang.String, int, int, java.lang.Object[])
//     */
//    public PageResults findPageBySql(String sql, int pageNo, int pageSize, Object... values)
//    {
//        PageResults retValue = new PageResults();
//        if (sql != null)
//        {
//            Session session = getSession();
//            Query query = session.createSQLQuery(sql);
//            for (int i = 0; values != null && i < values.length; i++)
//            {
//                query.setParameter(i, values[i]);
//            }
//            int curpage = pageNo > 1 ? pageNo : 1;
//            retValue.setCurrentPage(curpage);
//            
//            retValue.setPageSize(pageSize);
//            
//            ScrollableResults scrollableResults = query.scroll();
//            scrollableResults.last();
//            if (scrollableResults.getRowNumber() >= 0)
//                retValue.setTotalCount(scrollableResults.getRowNumber() + 1);
//            else
//            {
//                retValue.setTotalCount(0);
//            }
//            retValue.resetPageNo();
//            if (retValue.getTotalCount() == 0)
//                retValue.setResults(new ArrayList());
//            else
//            {
//                retValue.setResults(query.setFirstResult((curpage - 1) * pageSize).setMaxResults(pageSize).list());
//            }
//            releaseSession(session);
//        }
//        return retValue;
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param proxyObj
//     * @see com.itany.net.dao.IBaseDao#forceInitialize(java.lang.Object)
//     */
//    public void forceInitialize(Object proxyObj)
//    {
//        getHibernateTemplate().initialize(proxyObj);
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param session
//     * @param queryName
//     * @return
//     * @see com.itany.net.dao.IBaseDao#getNamedSql(org.hibernate.Session, java.lang.String)
//     */
//    public String getNamedSql(Session session, String queryName)
//    {
//        SessionFactoryImpl factory = (SessionFactoryImpl)session.getSessionFactory();
//        NamedSQLQueryDefinition nsqlqd = factory.getNamedSQLQuery(queryName);
//        if (nsqlqd == null)
//        {
//            return null;
//        }
//        return nsqlqd.getQueryString();
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param id
//     * @return
//     * @see com.itany.net.dao.IBaseDao#load(java.io.Serializable)
//     */
//    public T load(PK id)
//    {
//        return getHibernateTemplate().load(entityClass, id);
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param paramPK
//     * @return
//     * @see com.itany.net.dao.IBaseDao#read(java.io.Serializable)
//     */
//    public T read(PK paramPK)
//    {
//        return getHibernateTemplate().get(entityClass, paramPK);
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param entity
//     * @see com.itany.net.dao.IBaseDao#save(java.lang.Object)
//     */
//    public void save(Object entity)
//    {
//        getHibernateTemplate().save(entity);
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param entity
//     * @see com.itany.net.dao.IBaseDao#saveOrUpdate(java.lang.Object)
//     */
//    public void saveOrUpdate(Object entity)
//    {
//        getHibernateTemplate().saveOrUpdate(entity);
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param entity
//     * @see com.itany.net.dao.IBaseDao#update(java.lang.Object)
//     */
//    public void update(Object entity)
//    {
//        getHibernateTemplate().update(entity);
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param sql
//     * @param dataLst
//     * @return
//     * @see com.itany.net.dao.IBaseDao#updateBatchBySql(java.lang.String, java.util.List)
//     */
//    public int updateBatchBySql(String sql, List<Object[]> dataLst)
//    {
//        return updateBatchBySql(sql, dataLst, true);
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param sql
//     * @param dataLst
//     * @param innerTrans
//     * @return
//     * @see com.itany.net.dao.IBaseDao#updateBatchBySql(java.lang.String, java.util.List, boolean)
//     */
//    public int updateBatchBySql(final String sql, final List<Object[]> dataLst, boolean innerTrans)
//    {
//        HibernateCallback<Integer> callback = new HibernateCallback<Integer>()
//        {
//            public Integer doInHibernate(Session session)
//                throws HibernateException, SQLException
//            {
//                final int[] result = new int[1];
//                // 开启内部事务
//                // if (innerTrans)
//                // {
//                // session.beginTransaction();
//                // }
//                // 执行JDBC的数据批量保存
//                Work jdbcWork = new Work()
//                {
//                    public void execute(Connection connection)
//                        throws SQLException
//                    {
//                        
//                        PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                        try
//                        {
//                            for (Object[] row : dataLst)
//                            {
//                                for (int i = 0; i < row.length; i++)
//                                {
//                                    setParameter(ps, i, row[i]);
//                                }
//                                ps.addBatch();
//                            }
//                            int[] results = ps.executeBatch();
//                            result[0] = results.length; // 返回执行成功的批处理个数
//                        }
//                        finally
//                        {
//                            ps.close();
//                        }
//                        
//                    }
//                };
//                
//                try
//                {
//                    // 执行jdbc批量任务
//                    session.doWork(jdbcWork);
//                }
//                catch (HibernateException e)
//                {
//                    // if (innerTrans)
//                    // {
//                    // session.getTransaction().rollback();
//                    // }
//                    
//                    throw e;
//                }
//                
//                // if (innerTrans)
//                // {
//                // session.getTransaction().commit();
//                // }
//                return result[0];
//            }
//            
//        };
//        return getHibernateTemplate().execute(callback);
//    }
//    
//    /**
//     * 重写方法
//     * 
//     * @param hql
//     * @param values
//     * @return
//     * @see com.itany.net.dao.IBaseDao#updateByHsql(java.lang.String, java.lang.Object[])
//     */
//    public int updateByHsql(String hql, Object... values)
//    {
//        if (hql != null)
//        {
//            Session session = getSession();
//            Query query = session.createQuery(hql);
//            for (int i = 0; values != null && i < values.length; i++)
//            {
//                query.setParameter(i, values[i]);
//            }
//            int i = query.executeUpdate();
//            releaseSession(session);
//            return i;
//        }
//        return 0;
//    }
//    
//    /**
//     * 
//     * 设置每行批处理参数
//     * 
//     * @param ps
//     * @param pos ?占位符索引，从0开始
//     * @param data
//     * @throws SQLException
//     * @see [类、类#方法、类#成员]
//     */
//    private void setParameter(PreparedStatement ps, int pos, Object data)
//        throws SQLException
//    {
//        if (data == null)
//        {
//            ps.setNull(pos + 1, Types.VARCHAR);
//            return;
//        }
//        Class dataCls = data.getClass();
//        if (String.class.equals(dataCls))
//        {
//            ps.setString(pos + 1, (String)data);
//        }
//        else if (boolean.class.equals(dataCls))
//        {
//            ps.setBoolean(pos + 1, ((Boolean)data));
//        }
//        else if (int.class.equals(dataCls))
//        {
//            ps.setInt(pos + 1, (Integer)data);
//        }
//        else if (double.class.equals(dataCls))
//        {
//            ps.setDouble(pos + 1, (Double)data);
//        }
//        else if (Date.class.equals(dataCls))
//        {
//            Date val = (Date)data;
//            ps.setTimestamp(pos + 1, new Timestamp(val.getTime()));
//        }
//        else if (BigDecimal.class.equals(dataCls))
//        {
//            ps.setBigDecimal(pos + 1, (BigDecimal)data);
//        }
//        else
//        {
//            // 未知类型
//            ps.setObject(pos + 1, data);
//        }
//        
//    }
//    
//}
//
//
//
//
//
//
//
//
