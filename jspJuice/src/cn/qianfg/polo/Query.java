package cn.qianfg.polo;

import java.util.Date;

public class Query {
    private String qName;//品名
    private Date qStartDate;//开始日期
    private Date qEndDate;//结束日期

    public String getqName() {
        return qName;
    }

    public void setqName(String qName) {
        this.qName = qName;
    }

    public Date getqStartDate() {
        return qStartDate;
    }

    public void setqStartDate(Date qStartDate) {
        this.qStartDate = qStartDate;
    }

    public Date getqEndDate() {
        return qEndDate;
    }

    public void setqEndDate(Date qEndDate) {
        this.qEndDate = qEndDate;
    }
}
