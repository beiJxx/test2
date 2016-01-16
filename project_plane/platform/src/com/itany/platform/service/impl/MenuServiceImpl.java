/*
 * 文 件 名:  MenuServiceImpl.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  崔译 cuiyi@itany.com
 * 创建时间:  2014-11-11
 */
package com.itany.platform.service.impl;

import java.util.List;

import com.itany.platform.dao.IMenuDao;
import com.itany.platform.dao.impl.MenuDaoImpl;
import com.itany.platform.pojo.SysFunctionMenus;
import com.itany.platform.service.IMenuService;

/**
 * <一句话功能简述>
 *  
 * @author  崔译
 * @version  [V1.00, 2014-11-11]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class MenuServiceImpl implements IMenuService
{
    
    private IMenuDao menuDao = new MenuDaoImpl();
    
    /**
     * 重写方法
     * @param userId
     * @return
     * @see com.itany.platform.service.IMenuService#findMenuByUid(int)
     */
    public List<SysFunctionMenus> findMenuByUid(int userId)
    {
        return menuDao.selectByUid(userId);
    }
    
}
