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


//    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    //TODO RS: instead of sending message should send authentication token, or using session
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponseDTO loginUser(@RequestBody UserDTO userDTO) throws UserNotFoundException {
        boolean isUserAvailable = userService.areCredentialsValid(userDTO.getUsername(), userDTO.getPassword());
        LoginResponseDTO response = new LoginResponseDTO(isUserAvailable, "");
        return response;
    }

    //TODO RS: instead of sending message should send authentication token, or using session
    //and should autologin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public LoginResponseDTO registerPlayer(@RequestBody UserDTO userDTO) throws UsernameNotAvailableException {
        userService.createNewUser(userDTO.getUsername(),userDTO.getPassword());
        LoginResponseDTO response = new LoginResponseDTO(true, "");
        return response;
    }


}
