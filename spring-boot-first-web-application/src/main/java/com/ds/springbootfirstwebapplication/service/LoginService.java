//now not use this service

package com.ds.springbootfirstwebapplication.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {

    public boolean validateUSer(String userName, String password) {
        return userName.equalsIgnoreCase("ds") && password.equalsIgnoreCase("123");
    }
}
