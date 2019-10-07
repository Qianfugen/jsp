package cn.qianfg.polo;

import java.util.Date;

/**
 * 果汁实体类
 */
public class Juice {
    private Integer id;//id
    private Integer code;//条形码
    private String name;//品名
    private Double price;//价格
    private Integer amount;//数量
    private Date buyTime;//购入时间
    private String pic;//图片地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "juice{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", buyTime=" + buyTime +
                ", pic='" + pic + '\'' +
                '}';
    }
}
