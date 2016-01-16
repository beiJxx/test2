package com.itany.platform.pojo;
// default package

import com.itany.platform.pojo.Distributors;
import java.util.Date;


/**
 * AdditionalTicket entity. @author MyEclipse Persistence Tools
 */

public class AdditionalTicket  implements java.io.Serializable {


     private int id;
     private Distributors distributors;
     private Date takeOffDate;
     private String planeNum;
     private Date submitDate;
     private int firstNum;
     private int economyNum;
     private boolean checkFlag;
     private boolean validFlag;


    // Constructors

    /** default constructor */
    public AdditionalTicket() {
    }

	/** minimal constructor */
    public AdditionalTicket(Date takeOffDate, String planeNum, Date submitDate) {
        this.takeOffDate = takeOffDate;
        this.planeNum = planeNum;
        this.submitDate = submitDate;
    }
    
    /** full constructor */
    public AdditionalTicket(Distributors distributors, Date takeOffDate, String planeNum, Date submitDate, short firstNum, short economyNum, boolean checkFlag, boolean validFlag) {
        this.distributors = distributors;
        this.takeOffDate = takeOffDate;
        this.planeNum = planeNum;
        this.submitDate = submitDate;
        this.firstNum = firstNum;
        this.economyNum = economyNum;
        this.checkFlag = checkFlag;
        this.validFlag = validFlag;
    }

   
    // Property accessors

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Distributors getDistributors() {
        return this.distributors;
    }
    
    public void setDistributors(Distributors distributors) {
        this.distributors = distributors;
    }

    public Date getTakeOffDate() {
        return this.takeOffDate;
    }
    
    public void setTakeOffDate(Date takeOffDate) {
        this.takeOffDate = takeOffDate;
    }

    public String getPlaneNum() {
        return this.planeNum;
    }
    
    public void setPlaneNum(String planeNum) {
        this.planeNum = planeNum;
    }

    public Date getSubmitDate() {
        return this.submitDate;
    }
    
    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public int getFirstNum() {
        return this.firstNum;
    }
    
    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getEconomyNum() {
        return this.economyNum;
    }
    
    public void setEconomyNum(int economyNum) {
        this.economyNum = economyNum;
    }

    public boolean getCheckFlag() {
        return this.checkFlag;
    }
    
    public void setCheckFlag(boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    public boolean getValidFlag() {
        return this.validFlag;
    }
    
    public void setValidFlag(boolean validFlag) {
        this.validFlag = validFlag;
    }
   

}