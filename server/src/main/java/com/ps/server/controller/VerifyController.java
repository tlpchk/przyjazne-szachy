package com.ps.server.controller;

import com.ps.server.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify")
public class VerifyController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(method = RequestMethod.GET)
    public String verifyEmail(@RequestParam(name = "token") String token) {
        tokenService.checkVerifyToken(token);
        return "redirect: /auth/login";
    }
}
