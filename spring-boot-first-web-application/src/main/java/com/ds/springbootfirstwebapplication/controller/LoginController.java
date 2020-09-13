package com.ds.springbootfirstwebapplication.controller;

import com.ds.springbootfirstwebapplication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
// now this is should be welcomeController - after spring security implementation
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

    // now use spring security - also not use login form
//    @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
//    public String loginMessageWithForm() {
//        return "loginForm";
//    }


//    @RequestMapping(value = "/loginForm", method = RequestMethod.POST)
//    public String welcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
//
//        boolean isValidUser = loginService.validateUSer(name, password);
//
//        if(!isValidUser){
//            model.put("error","Invalid Credentials");
//            return "loginForm";
//        }
//
//        model.put("name", name);
//        return "welcomePage";
//    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginMessageWithForm(ModelMap model) {
        model.put("name", getLoggedInUserName());
        return "welcomePage";
    }


    private String getLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }
// use default logout in spring security
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout(HttpServletRequest request,
//                         HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext()
//                .getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/";
    //}
}
