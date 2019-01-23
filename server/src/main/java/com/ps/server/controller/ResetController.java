package com.ps.server.controller;

import com.ps.server.dto.LoginResponseDTO;
import com.ps.server.dto.ResetPasswordDTO;
import com.ps.server.entity.UserEntity;
import com.ps.server.exception.UserNotFoundException;
import com.ps.server.repository.UserRepository;
import com.ps.server.service.EmailService;
import com.ps.server.service.TokenService;
import com.ps.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("/api/reset")
public class ResetController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    /**
     * Verifies reset token.
     *
     * @param token to be verified
     * @return true when token is valid, false otherwise
     */
    @RequestMapping(method = RequestMethod.GET)
    public boolean verifyResetToken(@RequestParam(name = "token") String token) {
        return tokenService.checkResetToken(token);
    }

    /**
     * Sends password reset mail.
     *
     * @param email   on which mail should be sent
     * @param request
     * @return true if mail was valid, false otherwise
     */
    @RequestMapping(method = RequestMethod.POST)
    public boolean sendMail(@RequestBody String email, HttpServletRequest request) {
        try {
            UserEntity user = userService.findByEmail(email);
            user.setResetToken(UUID.randomUUID().toString());
            userRepository.save(user);
            String appUrl = request.getScheme() + "://" + request.getServerName();
            emailService.sendPasswordResetEmail(user, appUrl);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        }
    }

    /**
     * Resets password for user.
     *
     * @param resetPasswordDTO describes new password.
     * @param request
     * @return true when success, false otherwise
     */
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
