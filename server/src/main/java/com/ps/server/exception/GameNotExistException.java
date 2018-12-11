package com.ps.server.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Game with given Id does not exist")
public class GameNotExistException extends Exception {
}
