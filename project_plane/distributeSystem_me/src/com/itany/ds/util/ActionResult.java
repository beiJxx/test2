package com.itany.ds.util;

/**
 * <一句话功能简述>
 * Action[Ajax]请求响应结果
 * <功能详细描述>
 * 
 * @author  崔译
 * @version  [V1.00, 2012-6-28]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class ActionResult {
    private boolean success;

    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message [success=" + success + ", message=" + message + "]";
    }

}

