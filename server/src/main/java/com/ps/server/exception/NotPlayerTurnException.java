package com.ps.server.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason="It is not your turn")
public class NotPlayerTurnException extends Exception {
}
