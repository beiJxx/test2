package com.itany.platform.pojo;

import java.util.HashSet;
import java.util.Set;



public class SysRole  implements java.io.Serializable {



     /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private int srId;
     private String name;
     private Set sysUserRoleses = new HashSet(0);
     private Set sysRoleMenuses = new HashSet(0);


    public SysRole() {
    }

    public SysRole(String name) {
        this.name = name;
    }
    
    public SysRole(String name, Set sysUserRoleses, Set sysRoleMenuses) {
        this.name = name;
        this.sysUserRoleses = sysUserRoleses;
        this.sysRoleMenuses = sysRoleMenuses;
    }

   

    public int getSrId() {
        return this.srId;
    }
    
    public void setSrId(int srId) {
        this.srId = srId;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Set getSysUserRoleses() {
        return this.sysUserRoleses;
    }
    
    public void setSysUserRoleses(Set sysUserRoleses) {
        this.sysUserRoleses = sysUserRoleses;
    }

    public Set getSysRoleMenuses() {
        return this.sysRoleMenuses;
    }
    
    public void setSysRoleMenuses(Set sysRoleMenuses) {
        this.sysRoleMenuses = sysRoleMenuses;
    }
   

}