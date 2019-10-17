import cn.qianfg.service.Transfer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext app=new ClassPathXmlApplicationContext("classpath:springIOC.xml");
        Transfer tf=app.getBean(Transfer.class);
        tf.transfer();
    }
}
