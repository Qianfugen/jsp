package cn.qianfg.pojo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class FenYe {
    /**
     * 分页工具
     */
    private Integer page;//当前页码...
    private Integer rows=3;//每页多少条
    private Integer pageCount;//总页数
    private Integer rowsCount;//总条数...
    private Integer rowsStart;//当前页码的开始条数
    private Integer rowsEnd;//当前页码的结束条
    private QueryNews queryNews;//查询条件...

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

    public Integer getRowsStart() {
        rowsStart=(getPage()-1)*getRows();
        return rowsStart;
    }

    public void setRowsStart(Integer rowsStart) {
        this.rowsStart = rowsStart;
    }

    public Integer getRowsEnd() {
        rowsEnd=getPage()*getRows();
        return rowsEnd;
    }

    public void setRowsEnd(Integer rowsEnd) {
        this.rowsEnd = rowsEnd;
    }

    public QueryNews getQueryNews() {
        return queryNews;
    }

    public void setQueryNews(QueryNews queryNews) {
        this.queryNews = queryNews;
    }

    @Override
    public String toString() {
        return "FenYe{" +
                "page=" + page +
                ", rows=" + rows +
                ", pageCount=" + pageCount +
                ", rowsCount=" + rowsCount +
                ", rowStart=" + rowsStart +
                ", rowEnd=" + rowsEnd +
                ", query=" + queryNews +
                '}';
    }

}
