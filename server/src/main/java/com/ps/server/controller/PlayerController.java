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


    //TODO RS: change that to JSON
    @RequestMapping(method = RequestMethod.POST)
    public PlayerDTO createPlayer(@RequestBody String colorStr) {
        Color color = colorStr.toLowerCase().equals("white") ? Color.WHITE : Color.BLACK;
        PlayerEntity player = playerService.createNewPlayer(color);
        return new PlayerDTO(player.getId());
    }
}
