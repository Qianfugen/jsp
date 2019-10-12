package cn.qianfg.pojo;

public class NewsType {
    private Integer id;     //ID
    private String type;    //类型

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NewsType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
