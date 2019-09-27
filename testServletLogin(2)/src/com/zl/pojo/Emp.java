package com.zl.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Emp {
    /**
     * emp表对应的实体类
     */
    private Integer empNo;
    private String eName;
    private String job;
    private Integer mgr;
    private Date hireDate;
    private Double sal;
    private Double comm;
    private Integer deptNo;//0
    private String pic;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public Emp setEmpNo(Integer empNo) {
        this.empNo = empNo;
        return this;
    }

    public String geteName() {
        return eName;
    }

    public Emp seteName(String eName) {
        this.eName = eName;
        return this;
    }

    public String getJob() {
        return job;
    }

    public Emp setJob(String job) {
        this.job = job;
        return this;
    }

    public Integer getMgr() {
        return mgr;
    }

    public Emp setMgr(Integer mgr) {
        this.mgr = mgr;
        return this;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public Emp setHireDate(Date hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public Double getSal() {
        return sal;
    }

    public Emp setSal(Double sal) {
        this.sal = sal;
        return this;
    }

    public Double getComm() {
        return comm;
    }

    public Emp setComm(Double comm) {
        this.comm = comm;
        return this;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public Emp setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
        return this;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empNo=" + empNo +
                ", eName='" + eName + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hireDate=" + hireDate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptNo=" + deptNo +
                '}';
    }
}
