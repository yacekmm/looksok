package com.looksok.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api")
public class SecuredController {

    @RequestMapping(value = "/secured", method = GET, produces = TEXT_PLAIN_VALUE)
    public String securedGet(){
        return "You have access to secured API!";
    }

    @RequestMapping(value = "/admin", method = GET, produces = TEXT_PLAIN_VALUE)
    public String adminGet(){
        return "Welcome, Admin";
    }

    @RequestMapping(value = "/open", method = GET, produces = TEXT_PLAIN_VALUE)
    public String openedGet(){ return "this API does not require authentication"; }
}
