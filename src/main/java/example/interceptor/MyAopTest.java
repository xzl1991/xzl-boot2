package example.interceptor;

import example.holders.DBContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by ${xzl} on 2017/7/13.
 */
@Order(2)
@Aspect
@Component
public class MyAopTest {
    @Around("@annotation(example.annotations.MyAnnotation)")
    public  Object getAnnotation(ProceedingJoinPoint joinPoint){
        Object result = null;
        DBContextHolder.setDbType("testDataSource");
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            DBContextHolder.clearDBType();
        }
        return result;
    }
    @Before("execution(* example.helloworld.*.set*(..))")
    public void testBefore(){
        System.out.println("这是aop 的before ---->>>>>");
    }
}
