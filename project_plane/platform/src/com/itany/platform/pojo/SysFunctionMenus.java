package com.itany.platform.pojo;

import java.util.HashSet;
import java.util.Set;



public class SysFunctionMenus  implements java.io.Serializable {



     /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private int sfmId;
     private String sfmName;
     private int sfmParent;
     private String sfmUrl;
     private Set sysRoleMenuses = new HashSet(0);


    public SysFunctionMenus() {
    }

    public SysFunctionMenus(String sfmName) {
        this.sfmName = sfmName;
    }
    
    public SysFunctionMenus(String sfmName, int sfmParent, String sfmUrl, Set sysRoleMenuses) {
        this.sfmName = sfmName;
        this.sfmParent = sfmParent;
        this.sfmUrl = sfmUrl;
        this.sysRoleMenuses = sysRoleMenuses;
    }

   

    public int getSfmId() {
        return this.sfmId;
    }
    
    public void setSfmId(int sfmId) {
        this.sfmId = sfmId;
    }

    public String getSfmName() {
        return this.sfmName;
    }
    
    public void setSfmName(String sfmName) {
        this.sfmName = sfmName;
    }

    public int getSfmParent() {
        return this.sfmParent;
    }
    
    public void setSfmParent(int sfmParent) {
        this.sfmParent = sfmParent;
    }

    public String getSfmUrl() {
        return this.sfmUrl;
    }
    
    public void setSfmUrl(String sfmUrl) {
        this.sfmUrl = sfmUrl;
    }

    public Set getSysRoleMenuses() {
        return this.sysRoleMenuses;
    }
    
    public void setSysRoleMenuses(Set sysRoleMenuses) {
        this.sysRoleMenuses = sysRoleMenuses;
    }
   

}