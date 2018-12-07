package com.ps.server.controller;

import com.ps.server.dto.UserDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.POST)
    public boolean loginPlayer(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getUsername());
        System.out.println(userDTO.getPassword());
        return true;
    }


}
