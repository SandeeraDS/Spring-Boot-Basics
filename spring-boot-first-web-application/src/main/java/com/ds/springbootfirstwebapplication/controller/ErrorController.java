package com.ds.springbootfirstwebapplication.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller("error")
public class ErrorController {
    private Log logger = LogFactory.getLog(ErrorController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        // below info should insert to a database
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
//        this is the jsp
        mav.setViewName("error");
        return mav;
    }
}
