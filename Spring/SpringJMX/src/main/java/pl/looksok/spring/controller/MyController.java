package pl.looksok.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@ManagedResource(objectName = "jmxDemo:name=MyController")
public class MyController {

    private int myJmxValue = 25;

    private Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ManagedAttribute
    public void setMyJmxValue(int myJmxValue) {
        this.myJmxValue = myJmxValue;
    }

    @ManagedAttribute
    public int getMyJmxValue(){
        return myJmxValue;
    }

    @RequestMapping(value = "/api/my-jmx-value", method = GET)
    public int getManagedAttributeValue(){
        return myJmxValue;
    }

    @ManagedOperation
    public void logMyJmxValue(String requester){
        log.info("{} requested to log JmxManagedValue which is: {}", requester, myJmxValue);
    }
}
