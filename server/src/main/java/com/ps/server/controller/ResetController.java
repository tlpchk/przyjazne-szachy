package com.ps.server.controller;

import com.ps.server.dto.LoginResponseDTO;
import com.ps.server.dto.ResetPasswordDTO;
import com.ps.server.dto.UserDTO;
import com.ps.server.entity.UserEntity;
import com.ps.server.exception.EmailNotAvailableException;
import com.ps.server.exception.UserNotFoundException;
import com.ps.server.exception.UsernameNotAvailableException;
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

    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public LoginResponseDTO resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO, HttpServletRequest request) {
        String message = "OK!";
        boolean success = tokenService.changePassword(resetPasswordDTO.getToken(), resetPasswordDTO.getPassword());
        if (!success) {
            message = "Nastąpił błąd";
        }
        LoginResponseDTO response = new LoginResponseDTO(success, message);
        return response;
    }
}
