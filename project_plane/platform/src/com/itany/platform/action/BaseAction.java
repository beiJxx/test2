package com.itany.platform.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  崔译
 * @version [V1.00, 2012-10-21]
 * @see [相关类/方法]
 * @since V1.00
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ServletContextAware, SessionAware
{
	private static final long serialVersionUID = 1L;

	protected HttpServletRequest request;
    
    protected HttpServletResponse response;
    
    protected ServletContext appliacation;
    
    protected Map<String, Object> session;
    
    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }
    
    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }
    
    public void setServletContext(ServletContext appliacation)
    {
        this.appliacation = appliacation;
    }
    
    public void setSession(Map<String, Object> session)
    {
        this.session = session;
    }
    
}
