package com.ds.springbootfirstwebapplication.controller;

import com.ds.springbootfirstwebapplication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String loginMessage() {
        return "Hello World";
    }

    @RequestMapping(value = "/loginView", method = RequestMethod.GET)
    public String loginMessageWithView() {
        return "loginView";
    }

    @RequestMapping(value = "/loginParams", method = RequestMethod.GET)
    public String loginMessageWithParams(@RequestParam String name, ModelMap model) {
        model.put("name", name);
        return "loginParams";
    }

    @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
    public String loginMessageWithForm() {
        return "loginForm";
    }

    @RequestMapping(value = "/loginForm", method = RequestMethod.POST)
    public String welcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {

        boolean isValidUser = loginService.validateUSer(name, password);

        if(!isValidUser){
            model.put("error","Invalid Credentials");
            return "loginForm";
        }

        model.put("name", name);
        return "welcomePage";
    }

}
