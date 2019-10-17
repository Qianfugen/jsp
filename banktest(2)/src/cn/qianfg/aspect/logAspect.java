package cn.qianfg.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Aspect
public class logAspect {
    public void before(JoinPoint jp){
        System.out.println(new Date()+":"+jp.getSignature().getName()+"方法执行前...");
    }

    public void after(JoinPoint jp){
        System.out.println(new Date()+":"+jp.getSignature().getName()+"方法执行后...");
    }
}
