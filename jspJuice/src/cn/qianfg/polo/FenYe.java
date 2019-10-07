package cn.qianfg.polo;

public class FenYe {
    private Integer page;       //当前页
    private Integer rows=3;       //每页记录数
    private Integer pageCount;  //总页数
    private Integer rowsCount;  //总记录数
    private Integer rowStart;   //开始记录条数
    private Integer rowEnd;     //结束记录条数
    private Query query;        //查询条件

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
        System.out.println("总页数:"+pageCount);
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

    //每页第一条记录
    public Integer getRowStart() {
        rowStart=(getPage()-1)*getRows();
        return rowStart;
    }

    public void setRowStart(Integer rowStart) {
        this.rowStart = rowStart;
    }

    //每页最后一条记录
    public Integer getRowEnd() {
        rowEnd=(getPage())*getRows();
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
// 测试
//    public static void main(String[] args) {
//        FenYe fy=new FenYe();
//        System.out.println("总页数:"+fy.getPageCount());
//    }
}
