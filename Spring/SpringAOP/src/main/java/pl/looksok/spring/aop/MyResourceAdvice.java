package pl.looksok.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.invoke.MethodHandles;

@Aspect
@Configuration
public class MyResourceAdvice {

    private Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Pointcut("within(pl.looksok.spring.controller.*)")
    private void searchWithinControllersOnly() {}

    @Pointcut("@annotation(pl.looksok.spring.aop.MyAnnotation)")
    private void annotatedWithMyAnnotation() {}

    @Before("annotatedWithMyAnnotation() && searchWithinControllersOnly()")
    public void performImportantJob(){
        log.info("Performing important job in an advice method");
    }
}
