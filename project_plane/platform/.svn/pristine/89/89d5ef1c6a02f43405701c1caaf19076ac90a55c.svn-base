package com.itany.platform.dao.impl;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.engine.NamedSQLQueryDefinition;
import org.hibernate.impl.SessionFactoryImpl;
import org.hibernate.jdbc.Work;

import com.itany.platform.dao.IGenericDao;
import com.itany.platform.exception.DataAccessException;
import com.itany.platform.util.HibernateUtil;
import com.itany.platform.util.PageResults;
import com.itany.platform.util.RowMapper;

/**
 * s
 * <一句话功能简述>
 *  
 * @author  崔译
 * @version  [V1.00, 2014-11-3]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class GenericDaoImpl4Hibernate<T, PK extends Serializable> implements IGenericDao<T, PK>
{
    protected Class<T> entityClass;
    
    public GenericDaoImpl4Hibernate()
    {
        Type t = getClass().getGenericSuperclass();
        if (t instanceof ParameterizedType)
        {
            entityClass = (Class<T>)((ParameterizedType)t).getActualTypeArguments()[0];
        }
    }
    
    /**
     * 重写方法
     * <根据HQL去统计数据条数 用占位符 ？>
     * 
     * @param hql
     * @param values 条件
     * @return
     * @see com.itany.net.dao.IBaseDao#countByHql(java.lang.String, java.lang.Object[])
     */
    public Long countByHql(String hql, Object... values)
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSession();
            Query query = session.createQuery(hql);
            
            for (int i = 0; values != null && i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
            
            Object result = query.uniqueResult();
            if (result instanceof Long)
            {
                return (Long)result;
            }
            else if (result instanceof Integer)
            {
                return new Long((Integer)result);
            }
            else
            {
                return Long.valueOf(result.toString());
            }
            
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * 重写方法
     * 
     * @param entityClass
     * @param Id
     * @see com.itany.net.dao.IBaseDao#delete(java.lang.Class, java.io.Serializable)
     */
    public void delete(Class entityClass, Serializable Id)
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSession();
            session.delete(session.get(entityClass, Id));
        }
        catch (Exception e)

        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * 重写方法
     * 
     * @param entity
     * @see com.itany.net.dao.IBaseDao#delete(java.lang.Object)
     */
    
    public void delete(Object entity)
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSession();
            session.delete(entity);
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * 
     * @param Id
     * @return
     * @see com.itany.net.dao.IBaseDao#deleteById(java.io.Serializable)
     */
    
    public boolean deleteById(PK Id)
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSession();
            Object entity = session.get(entityClass, Id);
            if (entity == null)
            {
                return false;
            }
            session.delete(entity);
            return true;
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * 
     * @param hql
     * @see com.itany.net.dao.IBaseDao#deleteObjects(java.lang.String)
     */
    
    public void deleteObjects(String hql, Object... values)
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSession();
            Query query = session.createQuery(hql);
            for (int i = 0; values != null && i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
            query.executeUpdate();
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * 
     * <执行HQL更新、删除操作>
     * <执行基础HQL的更新、删除操作，支持HQL ？占位符方式赋值参数，不支持集合参数赋值>
     * 不建议以返回记录数确定更新操作操作是否成功，应为数据库服务器差异，该返回值不一定准确。
     * 建议 通过捕获异常判断操作是否成功，默认未出现异常着确定操作成功。
     * 
     * @param hql
     * @param values
     * @return
     * @see com.itany.net.dao.IBaseDao#executeByHql(java.lang.String, java.lang.Object[])
     */
    
    public int executeByHql(String hql, Object... values)
    {
        Session session = null;
        Query query = session.createQuery(hql);
        for (int i = 0; values != null && i < values.length; i++)
        {
            query.setParameter(i, values[i]);
        }
        return query.executeUpdate();
    }
    
    /**
     * 重写方法
     * 
     * @param hql
     * @param pageNo
     * @param pageSize
     * @return
     * @see com.itany.net.dao.IBaseDao#findByHql(java.lang.String, int, int)
     */
    
    public List findByHql(String hql, int pageNo, int pageSize)
    {
        Session session = null;
        List list = new ArrayList();
        try
        {
            session = HibernateUtil.getSession();
            Query query = session.createQuery(hql);
            int curpage = pageNo > 1 ? pageNo : 1;
            query.setFirstResult((curpage - 1) * pageSize).setMaxResults(pageSize);
            list = query.list();
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
        return list;
    }
    
    /**
     * 
     * @param sql
     * @param pageNo
     * @param pageSize
     * @return
     * @see com.itany.net.dao.IBaseDao#findBySql(java.lang.String, int, int)
     */
    
    public List findBySql(String sql, int pageNo, int pageSize)
    {
        Session session = null;
        List list = new ArrayList();
        try
        {
            session = HibernateUtil.getSession();
            Query query = session.createSQLQuery(sql);
            int curpage = pageNo > 1 ? pageNo : 1;
            query.setFirstResult((curpage - 1) * pageSize).setMaxResults(pageSize);
            list = query.list();
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
        return list;
    }
    
    /**
     * 
     * @param sqlName
     * @param values
     * @return
     * @see com.itany.net.dao.IBaseDao#findBySql(java.lang.String, java.lang.Object[])
     */
    
    public List findBySql(String sqlName, Object... values)
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSession();
            Query query = session.getNamedQuery(sqlName);
            for (int i = 0; values != null && i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
            return query.list();
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * 
     * @param hql
     * @param values
     * @return
     * @see com.itany.net.dao.IBaseDao#findListByHql(java.lang.String, java.lang.Object[])
     */
    
    public List findListByHql(String hql, Object... values)
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSession();
            Query query = session.createQuery(hql);
            for (int i = 0; values != null && i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
            return query.list();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * 
     * @param sqlName
     * @param map
     * @param values
     * @return
     * @see com.itany.net.dao.IBaseDao#findListByNamedSql(java.lang.String, com.itany.net.util.RowMapper,
     *      java.lang.Object[])
     */
    public List findListByNamedSql(final String sqlName, final RowMapper map, final Object... values)
    {
        Session session = null;
        final List list = new ArrayList();
        try
        {
            session = HibernateUtil.getSession();
            final String sql = getNamedSql(session, sqlName);
            Work jdbcWork = new Work()
            {
                
                public void execute(Connection connection)
                    throws SQLException
                {
                    PreparedStatement ps = null;
                    ResultSet rs = null;
                    try
                    {
                        
                        ps = connection.prepareStatement(sql);
                        for (int i = 0; values != null && i < values.length; i++)
                        {
                            setParameter(ps, i, values[i]);
                        }
                        rs = ps.executeQuery();
                        int index = 0;
                        while (rs.next())
                        {
                            Object obj = map.mapRow(rs, index++);
                            list.add(obj);
                        }
                    }
                    finally
                    {
                        if (rs != null)
                        {
                            rs.close();
                        }
                        if (ps != null)
                        {
                            ps.close();
                        }
                    }
                    
                }
            };
            
            session.doWork(jdbcWork);
            return list;
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * 
     * @param sql
     * @param values
     * @return
     * @see com.itany.net.dao.IBaseDao#findListBySql(java.lang.String, java.lang.Object[])
     */
    
    public List findListBySql(String sql, Object... values)
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSession();
            Query query = session.createSQLQuery(sql);
            for (int i = 0; values != null && i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
            return query.list();
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * 
     * @param sql
     * @param map
     * @param values
     * @return
     * @see com.itany.net.dao.IBaseDao#findListBySql(java.lang.String, com.itany.net.util.RowMapper, java.lang.Object[])
     */
    
    public List findListBySql(final String sql, final RowMapper map, final Object... values)
    {
        Session session = null;
        final List list = new ArrayList();
        try
        {
            session = HibernateUtil.getSession();
            Work jdbcWork = new Work()
            {
                
                public void execute(Connection connection)
                    throws SQLException
                {
                    PreparedStatement ps = null;
                    ResultSet rs = null;
                    try
                    {
                        ps = connection.prepareStatement(sql);
                        for (int i = 0; values != null && i < values.length; i++)
                        {
                            setParameter(ps, i, values[i]);
                        }
                        rs = ps.executeQuery();
                        int index = 0;
                        while (rs.next())
                        {
                            Object obj = map.mapRow(rs, index++);
                            list.add(obj);
                        }
                    }
                    finally
                    {
                        if (rs != null)
                        {
                            rs.close();
                        }
                        if (ps != null)
                        {
                            ps.close();
                        }
                    }
                    
                }
            };
            
            session.doWork(jdbcWork);
            return list;
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * <根据HQL查询单个对象>
     * <HQL+参数要确保返回单个对象，如果查出记录重复，则只返回第一行的对象>
     * 
     * @param hql HQL语句
     * @param values 可选？占位符参数
     * @return
     * @see com.itany.net.dao.IBaseDao#findObject(java.lang.String, java.lang.Object[])
     */
    
    public Object findObject(String hql, Object... values)
    {
        Session session = null;
        List list = null;
        Object result = null;
        try
        {
            session = HibernateUtil.getSession();
            Query query = session.createQuery(hql);
            for (int i = 0; values != null && i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
            list = query.list();
            if (list != null && !list.isEmpty())
            {
                result = list.get(0);
            }
            return result;
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * 
     * @param hql
     * @param countHql
     * @param countParams
     * @param pageNo
     * @param pageSize
     * @param values
     * @return
     * @see com.itany.net.dao.IBaseDao#findPageByFetchedHql(java.lang.String, java.lang.String, java.lang.Object[], int,
     *      int, java.lang.Object[])
     */
    
    public PageResults findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize, Object... values)
    {
        Session session = null;
        PageResults retValue = new PageResults();
        try
        {
            session = HibernateUtil.getSession();
            Query query = session.createQuery(hql);
            for (int i = 0; values != null && i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
            int curpage = pageNo > 1 ? pageNo : 1;
            retValue.setCurrentPage(curpage);
            retValue.setPageSize(pageSize);
            
            if (countHql == null)
            {
                ScrollableResults results = query.scroll();
                results.last();
                // 设置总行记录数
                retValue.setTotalCount(results.getRowNumber() + 1);
            }
            else
            {
                int count = countByHql(countHql, values).intValue();
                retValue.setTotalCount(count);
            }
            retValue.resetPageNo();
            @SuppressWarnings("unchecked")
            List<T> itemList = query.setFirstResult((curpage - 1) * pageSize).setMaxResults(pageSize).list();
            if (itemList == null)
            {
                itemList = new ArrayList<T>();
            }
            retValue.setResults(itemList);
            return retValue;
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * 
     * @param hql
     * @param pageNo
     * @param pageSize
     * @param values
     * @return
     * @see com.itany.net.dao.IBaseDao#findPageByHql(java.lang.String, int, int, java.lang.Object[])
     */
    
    public PageResults findPageByHql(String hql, int pageNo, int pageSize, Object... values)
    {
        return findPageByFetchedHql(hql, null, pageNo, pageSize, values);
    }
    
    /**
     * <强制加载Lazy对象>
     * <强制查询并获得Lazy属性对象或集合>
     * 
     * @param proxyObj 需要强制加载的对象
     * @see com.itany.net.dao.IBaseDao#forceInitialize(java.lang.Object)
     */
    
    public void forceInitialize(Object proxyObj)
    {
        try
        {
            Hibernate.initialize(proxyObj);
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * <根据主键获得对象，如果对象不存在，抛出异常ObjectNotFoundException>
     * 
     * @param id 主键
     * @return
     * @see com.itany.net.dao.IBaseDao#load(java.io.Serializable)
     */
    
    public T load(PK id)
    {
        Session session = null;
        session = HibernateUtil.getSession();
        return (T)session.load(entityClass, id);
    }
    
    /**
     * 
     * @param paramPK
     * @return
     * @see com.itany.net.dao.IBaseDao#read(java.io.Serializable)
     */
    
    public T read(PK paramPK)
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSession();
            return (T)session.get(entityClass, paramPK);
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * 
     * @param entity
     * @see com.itany.net.dao.IBaseDao#save(java.lang.Object)
     */
    
    public void save(Object entity)
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSession();
            session.save(entity);
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * 
     * @param entity
     * @see com.itany.net.dao.IBaseDao#saveOrUpdate(java.lang.Object)
     */
    
    public void saveOrUpdate(Object entity)
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSession();
            session.saveOrUpdate(entity);
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * 
     * @param entity
     * @see com.itany.net.dao.IBaseDao#update(java.lang.Object)
     */
    
    public void update(Object entity)
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSession();
            session.update(entity);
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    /**
     * 
     * @param sql
     * @param dataLst
     * @return
     * @see com.itany.net.dao.IBaseDao#updateBatchBySql(java.lang.String, java.util.List)
     */
    
    public int updateBatchBySql(String sql, List<Object[]> dataLst)
    {
        return updateBatchBySql(sql, dataLst, true);
    }
    
    /**
     * <批量更新方法>
     * <该方法是批量更新sql，并且默认采用对立事务，更新结束后提交事务>
     * 
     * @param sql
     * @param dataLst 每行记录参数
     * @param innerTrans 是否是内置事务管理
     * @return 返回成功执行的批量处理条数
     * @see com.itany.net.dao.IBaseDao#updateBatchBySql(java.lang.String, java.util.List, boolean)
     */
    
    public int updateBatchBySql(final String sql, final List<Object[]> dataLst, final boolean innerTrans)
    {
        Session session = null;
        final int[] result = new int[1];
        try
        {
            session = HibernateUtil.getSession();
            if (innerTrans)
            {
                session.beginTransaction();
            }
            // 执行jdbc的数据批量保存
            Work jdbcWork = new Work()
            {
                
                public void execute(Connection connection)
                    throws SQLException
                {
                    PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    try
                    {
                        for (Object[] row : dataLst)
                        {
                            for (int i = 0; i < row.length; i++)
                            {
                                setParameter(ps, i, row[i]);
                            }
                            ps.addBatch();
                        }
                        int[] results = ps.executeBatch();
                        // 返回执行成功的批处理个数
                        result[0] = results.length;
                    }
                    finally
                    {
                        if (ps != null)
                        {
                            ps.close();
                        }
                        
                    }
                    
                }
            };
            session.doWork(jdbcWork);
        }
        catch (Exception e)
        {
            if (innerTrans)
            {
                session.getTransaction().rollback();
            }
            throw new DataAccessException("数据访问异常", e);
            
        }
        if (innerTrans)
        {
            session.getTransaction().commit();
        }
        return result[0];
    }
    
    /**
     * 
     * @param hql
     * @param paraMap
     * @return
     * @see com.itany.net.dao.IBaseDao#updateByHsql(java.lang.String, java.util.Map)
     */
    
    public int updateByHsql(String hql, Object... values)
    {
        Session session = null;
        session = HibernateUtil.getSession();
        Query query = session.createQuery(hql);
        for (int i = 0; values != null && i < values.length; i++)
        {
            query.setParameter(i, values[i]);
        }
        int i = query.executeUpdate();
        return i;
    }
    
    /**
     * 
     * @param sql
     * @param pageNo
     * @param pageSize
     * @param values
     * @return
     * @see com.itany.net.dao.IBaseDao#findPageBySql(java.lang.String, int, int, java.lang.Object[])
     */
    
    public PageResults findPageBySql(String sql, int pageNo, int pageSize, Object... values)
    {
        Session session = null;
        PageResults retValue = new PageResults();
        try
        {
            session = HibernateUtil.getSession();
            Query query = session.createSQLQuery(sql);
            for (int i = 0; values != null && i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
            int curpage = pageNo > 1 ? pageNo : 1;
            retValue.setCurrentPage(curpage);
            retValue.setPageSize(pageSize);
            ScrollableResults results = query.scroll();
            results.last();
            // 设置总行记录数
            retValue.setTotalCount(results.getRowNumber() + 1);
            retValue.resetPageNo();
            List<T> itemList = query.setFirstResult((curpage - 1) * pageSize).setMaxResults(pageSize).list();
            if (itemList == null)
            {
                itemList = new ArrayList<T>();
            }
            retValue.setResults(itemList);
            return retValue;
        }
        catch (Exception e)
        {
            throw new DataAccessException("数据访问异常", e);
        }
    }
    
    private void setParameter(PreparedStatement ps, int pos, Object data)
        throws SQLException
    {
        if (data == null)
        {
            ps.setNull(pos + 1, Types.VARCHAR);
        }
        Class dataCls = data.getClass();
        if (String.class.equals(dataCls))
        {
            ps.setString(pos + 1, (String)data);
        }
        else if (boolean.class.equals(dataCls))
        {
            ps.setBoolean(pos + 1, (Boolean)data);
        }
        else if (int.class.equals(dataCls))
        {
            ps.setInt(pos + 1, (Integer)data);
        }
        else if (double.class.equals(dataCls))
        {
            ps.setDouble(pos + 1, (Double)data);
        }
        else if (Date.class.equals(dataCls))
        {
            Date val = (Date)data;
            ps.setTimestamp(pos + 1, new Timestamp(val.getTime()));
        }
        else
        {
            // 未知的类型
            ps.setObject(pos + 1, data);
        }
    }
    
    /**
     * 
     * <获取HBM配置文件中的的SQL查询语句>
     * 
     * @param session
     * @param queryName 定义的查询语句名字
     * @return
     */
    public String getNamedSql(Session session, String queryName)
    {
        SessionFactoryImpl factory = (SessionFactoryImpl)session.getSessionFactory();
        
        NamedSQLQueryDefinition nsqlqd = factory.getNamedSQLQuery(queryName);
        if (nsqlqd == null)
        {
            return null;
        }
        return nsqlqd.getQueryString();
    }
}
