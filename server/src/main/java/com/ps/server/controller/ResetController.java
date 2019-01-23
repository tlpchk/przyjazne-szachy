package com.ps.server.controller;

import com.ps.server.entity.UserEntity;
import com.ps.server.exception.UserNotFoundException;
import com.ps.server.service.EmailService;
import com.ps.server.service.TokenService;
import com.ps.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reset")
public class ResetController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(method = RequestMethod.GET)
    public boolean verifyResetToken(@RequestParam(name = "token") String token) {
        return tokenService.checkResetToken(token);
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean sendMail(@RequestBody String email, HttpServletRequest request) {
        try {
            UserEntity user = userService.findByEmail(email);
            String appUrl = request.getScheme() + "://" + request.getServerName();
            emailService.sendPasswordResetEmail(user, appUrl);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        }
    }
}
