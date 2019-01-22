package com.ps.server.controller;

import com.ps.server.dto.LoginResponseDTO;
import com.ps.server.dto.UserDTO;
import com.ps.server.exception.UserNotFoundException;
import com.ps.server.exception.UsernameNotAvailableException;
import com.ps.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    //TODO RS: instead of sending message should send authentication token, or using session
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponseDTO loginUser(@RequestBody UserDTO userDTO) {
        String message = "Hello!";
        boolean isUserAvailable = true;
        try {
            isUserAvailable = userService.areCredentialsValid(userDTO.getUsername(), userDTO.getPassword());
        } catch (UserNotFoundException e) {
            isUserAvailable = false;
            message = "Zły login lub hasło";
        } finally {
            LoginResponseDTO response = new LoginResponseDTO(isUserAvailable, message);
            return response;
        }
    }

    //TODO RS: instead of sending message should send authentication token, or using session
    //and should autologin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public LoginResponseDTO registerPlayer(@RequestBody UserDTO userDTO) {
        String message = "Hello!";
        boolean success = true;
        try {
            userService.createNewUser(userDTO.getUsername(), userDTO.getPassword());
        } catch (UsernameNotAvailableException e) {
            message = "Nazwa użtykownika zajęta";
            success = false;
        }
        LoginResponseDTO response = new LoginResponseDTO(success, message);
        return response;
    }


}
