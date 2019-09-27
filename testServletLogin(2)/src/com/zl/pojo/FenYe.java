package com.zl.pojo;

public class FenYe {
    /**
     * 分页工具类
     */
    private Integer page;//当前页码....
    private Integer rows=3;//每页显示多少条
    private Integer pageCount;//总页面
    private Integer rowsCount;//符合要求的记录总数....
    private Integer rowStart;//当前页码的开始条
    private Integer rowEnd;//当前页码的结束条
    private Query query;//当前请求的查询条件....

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPageCount() {
        pageCount=(int)Math.ceil(getRowsCount()/(getRows()*1.0));
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getRowsCount() {
        return rowsCount;
    }

    public void setRowsCount(Integer rowsCount) {
        this.rowsCount = rowsCount;
    }

    public Integer getRowStart() {
        rowStart=(getPage()-1)*getRows();
        return rowStart;
    }

    public void setRowStart(Integer rowStart) {
        this.rowStart = rowStart;
    }

    public Integer getRowEnd() {
        rowEnd=getPage()*getRows();
        return rowEnd;
    }

    public void setRowEnd(Integer rowEnd) {
        this.rowEnd = rowEnd;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "FenYe{" +
                "page=" + page +
                ", rows=" + rows +
                ", pageCount=" + pageCount +
                ", rowsCount=" + rowsCount +
                ", rowStart=" + rowStart +
                ", rowEnd=" + rowEnd +
                ", query=" + query +
                '}';
    }
}
