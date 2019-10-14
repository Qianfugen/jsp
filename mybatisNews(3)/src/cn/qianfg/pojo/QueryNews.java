package cn.qianfg.pojo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class QueryNews {
    private String qTitle;
    private String qContext;

    public String getqTitle() {
        return qTitle;
    }

    public void setqTitle(String qTitle) {
        this.qTitle = qTitle;
    }

    public String getqContext() {
        return qContext;
    }

    public void setqContext(String qContext) {
        this.qContext = qContext;
    }

    @Override
    public String toString() {
        return "QueryNews{" +
                "qTitle='" + qTitle + '\'' +
                ", qContext='" + qContext + '\'' +
                '}';
    }
}
