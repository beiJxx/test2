package com.itany.platform.pojo;

public class Settlement implements java.io.Serializable
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private int id;
    
    private Distributors distributors;
    
    private String serial;
    
    private int num;
    
    private String cred;
    
    private boolean status;
    
    public Settlement()
    {
    }
    
    public Settlement(Distributors distributors, String serial, int num, String cred, boolean status)
    {
        this.distributors = distributors;
        this.serial = serial;
        this.num = num;
        this.cred = cred;
        this.status = status;
    }
    
    
    public long getSId()
    {
        return this.id;
    }
    
    public void setSId(int id)
    {
        this.id = id;
    }
    
    public Distributors getDistributors()
    {
        return this.distributors;
    }
    
    public void setDistributors(Distributors distributors)
    {
        this.distributors = distributors;
    }
    
    public String getSerial()
    {
        return this.serial;
    }
    
    public void setSerial(String serial)
    {
        this.serial = serial;
    }
    
    public int getNum()
    {
        return this.num;
    }
    
    public void setNum(int num)
    {
        this.num = num;
    }
    
    public String getCred()
    {
        return this.cred;
    }
    
    public void setCred(String cred)
    {
        this.cred = cred;
    }
    
    public boolean getStatus()
    {
        return this.status;
    }
    
    public void setStatus(boolean status)
    {
        this.status = status;
    }
    
}