package com.itany.platform.pojo;

import java.util.HashSet;
import java.util.Set;



public class SysUser  implements java.io.Serializable {


     /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private int suId;
     private String suLoginname;
     private String suPwd;
     private String suName;
     private Set sysUserRoleses = new HashSet(0);
     private Set announcements = new HashSet(0);
     private Set distributorses = new HashSet(0);


    public SysUser() {
    }

    public SysUser(String suLoginname, String suPwd) {
        this.suLoginname = suLoginname;
        this.suPwd = suPwd;
    }
    
    public SysUser(String suLoginname, String suPwd, String suName, Set sysUserRoleses, Set announcements, Set distributorses) {
        this.suLoginname = suLoginname;
        this.suPwd = suPwd;
        this.suName = suName;
        this.sysUserRoleses = sysUserRoleses;
        this.announcements = announcements;
        this.distributorses = distributorses;
    }

   

    public int getSuId() {
        return this.suId;
    }
    
    public void setSuId(int suId) {
        this.suId = suId;
    }

    public String getSuLoginname() {
        return this.suLoginname;
    }
    
    public void setSuLoginname(String suLoginname) {
        this.suLoginname = suLoginname;
    }

    public String getSuPwd() {
        return this.suPwd;
    }
    
    public void setSuPwd(String suPwd) {
        this.suPwd = suPwd;
    }

    public String getSuName() {
        return this.suName;
    }
    
    public void setSuName(String suName) {
        this.suName = suName;
    }

    public Set getSysUserRoleses() {
        return this.sysUserRoleses;
    }
    
    public void setSysUserRoleses(Set sysUserRoleses) {
        this.sysUserRoleses = sysUserRoleses;
    }

    public Set getAnnouncements() {
        return this.announcements;
    }
    
    public void setAnnouncements(Set announcements) {
        this.announcements = announcements;
    }

    public Set getDistributorses() {
        return this.distributorses;
    }
    
    public void setDistributorses(Set distributorses) {
        this.distributorses = distributorses;
    }
}