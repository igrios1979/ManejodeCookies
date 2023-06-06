package org.ignaciorios.apiservlet.webapp.headers.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

public class LoginServiceImpl implements LoginService {


    @Override
    public Optional<String> getUsername(HttpServletRequest req) {

        //Cookie[] cookies = req.getCookies()!=null ? req.getCookies(): new Cookie[0];

        Cookie[] cookies;
        if (req.getCookies() != null) {
            cookies = req.getCookies();
        } else {
            cookies = new Cookie[0];
        }


        return  Arrays.stream(cookies)
                .filter(c->"username".equals(c.getName()))
                .map(c->{return c.getValue();})
                .findAny();


    }
}
