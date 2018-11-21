package com.ps.server.controller;

import com.ps.server.Logic.Color;
import com.ps.server.dto.PlayerDTO;
import com.ps.server.entity.PlayerEntity;
import com.ps.server.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @RequestMapping(method = RequestMethod.POST)
    public PlayerDTO createPlayer(@RequestBody String colorString) {
        Color color = (colorString.toUpperCase().equals("WHITE")) ? Color.WHITE : Color.BLACK;
        PlayerEntity player = playerService.createNewPlayer(color);
        System.out.println("player created");
        return new PlayerDTO(player.getId());
    }
}
