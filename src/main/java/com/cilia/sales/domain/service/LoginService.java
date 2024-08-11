package com.cilia.sales.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserService userService;

    public Boolean validateLogin(String username, String password){
        return userService.signUp(username, password);
    }
}
