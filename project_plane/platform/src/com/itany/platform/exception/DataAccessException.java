package com.itany.platform.exception;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author 崔译
 * @version [V1.00, 2012-11-6]
 * @see [相关类/方法]
 * @since V1.00
 */
public class DataAccessException extends RuntimeException
{
    
    private static final long serialVersionUID = 1L;
    
    public DataAccessException()
    {
        super();
    }
    
    public DataAccessException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    public DataAccessException(String message)
    {
        super(message);
    }
    
    public DataAccessException(Throwable cause)
    {
        super(cause);
    }
    
}
