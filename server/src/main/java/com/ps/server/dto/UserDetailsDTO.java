package com.ps.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {
    private String username;
    private Long games;
    private Long wonGames;
    private Long lostGames;
    private Long drawGames;
}

