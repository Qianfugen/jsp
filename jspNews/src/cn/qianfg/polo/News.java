package cn.qianfg.polo;

import java.util.Date;

public class News {
    private Integer id;         //新闻id
    private Integer newsId;     //新闻编号
    private String title;       //新闻标题
    private String context;     //新闻内容
    private String newsMan;     //新闻记者
    private Date newsDate;      //新闻日期
    private Integer typeId;     //新闻类型
    private String pic;         //新闻图片

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getNewsMan() {
        return newsMan;
    }

    public void setNewsMan(String newsMan) {
        this.newsMan = newsMan;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }

    @Override
    public String toString() {
        return "新闻编号:"+newsId+"\n新闻标题:"+title+"\n新闻内容:"+context+"\n新闻记者:"+newsMan+
                "\n新闻日期:"+newsDate+"\n新闻类型:"+typeId+"\n新闻图片:"+pic;
    }
}
