package com.ds.springbootdeep.controller;

import com.ds.springbootdeep.configuration.BasicConfiguration;
import com.ds.springbootdeep.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WelcomeController {

    @Autowired
    private WelcomeService welcomeService;
    @Autowired
    private BasicConfiguration basicConfiguration;

    @RequestMapping("/welcome")
    @ResponseBody
    public String welcome() {
        return welcomeService.retrieveWelcomeMessage();
    }
    @RequestMapping("/dynamic-configuration")
    public Map dynamicConfiguration() {

        Map map = new HashMap();

        map.put("message",basicConfiguration.getMessage());
        map.put("number",basicConfiguration.getNumber());
        map.put("value",basicConfiguration.isValue());

    return map;
    }
}
