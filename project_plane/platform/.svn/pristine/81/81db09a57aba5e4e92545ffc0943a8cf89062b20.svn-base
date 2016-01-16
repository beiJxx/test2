package com.itany.platform.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DistributionPlan implements java.io.Serializable
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private int dpId;
    
    private String planeNum;
    
    private String name;
    
    private Date takeOffDate;
    
    private short assignTicket;
    
    private byte discount;
    
    private Date createTime;
    
    private boolean flag;
    
    private Set ticketPlans = new HashSet(0);
    
    
    public DistributionPlan()
    {
    }
    
    public DistributionPlan(Date createTime, boolean flag)
    {
        this.createTime = createTime;
        this.flag = flag;
    }
    
    public DistributionPlan(String planeNum, String name, Date takeOffDate, short assignTicket, byte discount, Date createTime, boolean flag, Set ticketPlans)
    {
        this.planeNum = planeNum;
        this.name = name;
        this.takeOffDate = takeOffDate;
        this.assignTicket = assignTicket;
        this.discount = discount;
        this.createTime = createTime;
        this.flag = flag;
        this.ticketPlans = ticketPlans;
    }
    
    
    public long getDpId()
    {
        return this.dpId;
    }
    
    public void setDpId(int dpId)
    {
        this.dpId = dpId;
    }
    
    public String getPlaneNum()
    {
        return this.planeNum;
    }
    
    public void setPlaneNum(String planeNum)
    {
        this.planeNum = planeNum;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Date getTakeOffDate()
    {
        return this.takeOffDate;
    }
    
    public void setTakeOffDate(Date takeOffDate)
    {
        this.takeOffDate = takeOffDate;
    }
    
    public short getAssignTicket()
    {
        return this.assignTicket;
    }
    
    public void setAssignTicket(short assignTicket)
    {
        this.assignTicket = assignTicket;
    }
    
    public byte getDiscount()
    {
        return this.discount;
    }
    
    public void setDiscount(byte discount)
    {
        this.discount = discount;
    }
    
    public Date getCreateTime()
    {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    public boolean getFlag()
    {
        return this.flag;
    }
    
    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }
    
    public Set getTicketPlans()
    {
        return this.ticketPlans;
    }
    
    public void setTicketPlans(Set ticketPlans)
    {
        this.ticketPlans = ticketPlans;
    }
    
}