package com.itany.platform.pojo;

import java.util.Date;


public class ReturnFee implements java.io.Serializable
{
    
    private int rfId;
    
    private Date changeDate;
    
    private Date lastChangeDate;
    
    private double fee;
    
    public ReturnFee()
    {
    }
    
    public ReturnFee(Date changeDate, Date lastChangeDate, double fee)
    {
        this.changeDate = changeDate;
        this.lastChangeDate = lastChangeDate;
        this.fee = fee;
    }
    
    
    public int getRfId()
    {
        return this.rfId;
    }
    
    public void setRfId(int rfId)
    {
        this.rfId = rfId;
    }
    
    public Date getChangeDate()
    {
        return this.changeDate;
    }
    
    public void setChangeDate(Date changeDate)
    {
        this.changeDate = changeDate;
    }
    
    public Date getLastChangeDate()
    {
        return this.lastChangeDate;
    }
    
    public void setLastChangeDate(Date lastChangeDate)
    {
        this.lastChangeDate = lastChangeDate;
    }
    
    public double getFee()
    {
        return this.fee;
    }
    
    public void setFee(double fee)
    {
        this.fee = fee;
    }
    
}