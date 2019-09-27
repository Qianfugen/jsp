package com.zl.pojo;

import java.util.Date;
/**
 * Emp表的查询条件对象
 */
public class Query {
    private String qeName;
    private Date qStartHiredate;
    private Date qEndHiredate;

    public String getQeName() {
        return qeName;
    }

    public Query setQeName(String qeName) {
        this.qeName = qeName;
        return this;
    }

    public Date getqStartHiredate() {
        return qStartHiredate;
    }

    public Query setqStartHiredate(Date qStartHiredate) {
        this.qStartHiredate = qStartHiredate;
        return this;
    }

    public Date getqEndHiredate() {
        return qEndHiredate;
    }

    public Query setqEndHiredate(Date qEndHiredate) {
        this.qEndHiredate = qEndHiredate;
        return this;
    }
}
