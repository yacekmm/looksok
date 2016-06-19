package pl.looksok.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
public class MyController {

    private int myJmxValue = 25;

    @RequestMapping(value = "/api/my-jmx-value")
    public int getMyJmxValue(){
        return myJmxValue;
    }
}
