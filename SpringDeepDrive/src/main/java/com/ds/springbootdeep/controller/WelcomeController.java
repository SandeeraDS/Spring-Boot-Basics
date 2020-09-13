package com.ds.springbootdeep.controller;

import com.ds.springbootdeep.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private WelcomeService welcomeService;

    @RequestMapping("/welcome")
    @ResponseBody
    public String welcome() {
        return welcomeService.retrieveWelcomeMessage();
    }
}
