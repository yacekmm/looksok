package pl.looksok.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MyResourceAdvice {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("@annotation(pl.looksok.spring.aop.MyAnnotation)")
    public void performImportantJob(){
        log.info("Performing important job in an advice method");
    }
}
