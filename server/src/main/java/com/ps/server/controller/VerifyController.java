package com.ps.server.controller;

import com.ps.server.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/verify")
public class VerifyController {

    @Autowired
    private TokenService tokenService;

    /**
     * Verifies email token and activates account.
     *
     * @param token to be verified
     * @return true when success, false otherwise
     */
    @RequestMapping(method = RequestMethod.GET)
    public boolean verifyEmail(@RequestParam(name = "token") String token) {
        return tokenService.checkVerifyToken(token);
    }
}
