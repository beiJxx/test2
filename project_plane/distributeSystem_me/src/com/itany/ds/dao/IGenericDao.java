package com.itany.ds.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.itany.ds.util.PageResults;
import com.itany.ds.util.RowMapper;

/**
 * 
 * <一句话功能简述>
 *  
 * @author  崔译
 * @version  [V1.00, 2014-11-3]
 * @see  [相关类/方法]
 * @since V1.00
 */
public interface IGenericDao<T, PK extends Serializable>
{
    /**
     * 
     * <强制加载Lazy对象>
     * <强制查询并获得Lazy属性对象或集合
     * 
     * @param proxyObj
     */
    public void forceInitialize(Object proxyObj);
    
    /**
     * 
     * <根据主键删除对象>
     * <根据主键删除对象，实体类型以当前T指定的具体类型为主
     * 
     * @param Id 当前T对应的实体主键
     */
    public abstract boolean deleteById(PK Id);
    
    public abstract void delete(Object entity);
    
    public abstract void delete(Class entityClass, Serializable Id);
    
    public abstract void deleteObjects(String hql,Object...values);
    
    /**
     * 
     * <p>
     * 根据Hql+?参数占位符查询List
     * </p>
     * 
     * @param hql HQL语句
     * @param values 可选？占位符参数
     * @return 满足查询的记录List对象
     */
    public abstract List findListByHql(String hql, Object... values);
    
    public abstract List findByHql(String hql, int pageNo, int pageSize);
    
    public abstract List findBySql(String sql, int pageNo, int pageSize);
    
    /**
     * 
     * <根据配置文件中SqlName和参数查询对�?
     * 
     * @param sqlName 配置sql语句的name
     * @param values [可�?]参数
     * @return
     * @see [类�?�?方法、类#成员]
     */
    public List findBySql(String sqlName, Object... values);
    
    /**
     * 
     * <根据配置文件中SqlName和参数查询对�?
     * 
     * @param sqlName 配置的sql语句
     * @param values [可�?]参数
     * @return
     * @see [类�?�?方法、类#成员]
     */
    public List findListBySql(String sql, Object... values);
    
    /**
     * 
     * <根据配置文件中SqlName和参数查询对�?
     * 
     * @param sqlName 配置的sql语句
     * @param values [可�?]参数
     * @param map 行映�?
     * @return
     * @see [类�?�?方法、类#成员]
     */
    public List findListBySql(String sql, RowMapper map, Object... values);
    
    /**
     * 
     * <根据Hbm文件中配置的Sql执行查询>
     * <根据hbm配置的文件执行Sql查询，RowMapper对查询结果集ResultSet进行封装>
     * 
     * @param sqlName 配置文件中对应的sql name
     * @param map
     * @param values
     * @return
     */
    public List findListByNamedSql(String sqlName, RowMapper map, Object... values);
    
    /**
     * 
     * <分页hql查询>
     * <简单分页hql查询，
     * hql中不包含join fetch或着查询的实体对象，
     * 关联对象不采用fetch方式加载>
     * 
     * @param hql hql查询语句
     * @param pageNo 当前页数
     * @param pageSize 每页记录数
     * @param values ?占位符的参数值，可选
     * @return
     */
    public abstract PageResults findPageByHql(String hql, int pageNo, int pageSize, Object... values);
    
    public abstract PageResults findPageBySql(String sql, int pageNo, int pageSize, Object... values);
    
    /**
     * 
     * <含fetch分页hql查询>
     * <该方法针对hql代码中含有join fetch或者
     * 实体对象的关联属性采用fetch方式加载的的。>
     * 
     * @param hql 查询明细记录hql语句
     * @param countHql 计算主记录总数的hql语句，例如select count(user) from User user。
     * @param pageNo
     * @param pageSize
     * @param values
     * @return
     */
    public PageResults findPageByFetchedHql(String hql, String countHql, int pageNo, int pageSize, Object... values);
    
    public abstract T read(PK paramPK);
    
    /**
     * <根据主键获取对象，如果对象不存在，抛出异常ObjectNotFoundEcception>
     * 
     * @param id
     * @return
     */
    public T load(PK id);
    
    public abstract void saveOrUpdate(Object entity);
    
    public abstract void save(Object entity);
    
    public abstract void update(Object entity);
    
    /**
     * 
     * <执行HQL更新、删除操作>
     * <执行基础HQL的更新、删除操作，支持HQL ？占位符方式赋值参数，不支持集合参数赋值>
     * 
     * @param hql hql语句，可加入?参数占位符
     * @param values hql参数，以?占位符的方式顺序赋值
     * @return
     * @see
     */
    public int executeByHql(String hql, Object... values);
    
    public abstract int updateByHsql(String hql, Object... values);
    
    /**
     * 
     * <批量更新方法>
     * <功能详细描述>
     * 
     * @param sql
     * @param dataLst 每行记录参数
     * @return
     */
    public int updateBatchBySql(String sql, List<Object[]> dataLst);
    
    /**
     * 
     * <批量更新方法>
     * <功能详细描述>
     * 
     * @param sql
     * @param dataLst 每行记录参数
     * @param singleTrans 是否内置事务提交
     * @return
     */
    public int updateBatchBySql(String sql, List<Object[]> dataLst, boolean innerTrans);
    
    /**
     * 
     * 根据HQL查询对象的记录数
     * <功能详细描述>
     * 
     * @param hql
     * @param values 可选 ?占位符参数
     * @return
     */
    public abstract Long countByHql(String hql, Object... values);
    
    /**
     * 
     * <根据HQL查询单个对象>
     * <HQL+参数要确保返回单个对象，如果查出记录重复，则只返回第一个对象
     * 
     * @param hql
     * @param values 可选 ?占位符参数
     * @return
     * @see
     */
    public abstract Object findObject(String hql, Object... values);
    
    /**
     * 
     * <获取HBM配置文件中的的SQL查询语句>
     * 
     * @param session
     * @param queryName 定义的查询语句名字
     * @return
     */
    public String getNamedSql(Session session, String queryName);
}
