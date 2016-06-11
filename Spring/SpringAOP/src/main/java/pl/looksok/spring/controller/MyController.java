package pl.looksok.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @RequestMapping(value = "/api/my-resource")
    public String getMyResource(){
        return "Your resource is here";
    }

}
