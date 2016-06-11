package pl.looksok.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.looksok.spring.aop.MyAnnotation;

import java.lang.invoke.MethodHandles;

@RestController
public class MyController {

    private Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @RequestMapping(value = "/api/my-resource")
    @MyAnnotation
    public String getMyResource(){
        log.info("Returning the resource");
        return "Your resource is here";
    }
}
