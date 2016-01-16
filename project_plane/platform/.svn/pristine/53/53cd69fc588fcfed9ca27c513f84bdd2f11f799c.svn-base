/*
 * 文 件 名:  MenuAction.java
 * 版    权:  Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 创 建 人:  崔译 cuiyi@itany.com
 * 创建时间:  2014-11-11
 */
package com.itany.platform.action;

import java.util.List;

import com.itany.platform.pojo.SysFunctionMenus;
import com.itany.platform.service.IMenuService;
import com.itany.platform.service.impl.MenuServiceImpl;

/**
 * <一句话功能简述>
 *  
 * @author  崔译
 * @version  [V1.00, 2014-11-11]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class MenuAction extends BaseAction
{
    
    private IMenuService menuService = new MenuServiceImpl();
    
    private int id;
    
    private List<SysFunctionMenus> functionMenus;
    
    public List<SysFunctionMenus> getFunctionMenus()
    {
        return functionMenus;
    }
    
    public void setFunctionMenus(List<SysFunctionMenus> functionMenus)
    {
        this.functionMenus = functionMenus;
    }
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String findMenu()
    {
        functionMenus = menuService.findMenuByUid(id);
        return "findMenu";
    }
    
}
