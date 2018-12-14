package com.ps.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/timer")
public class DummyTimerController {

    @RequestMapping
    public Long getTimer() {
        //returning 30 min in miliseconds
        return new Date(System.currentTimeMillis() + 30 * 60 * 1000L).getTime();
    }
}
