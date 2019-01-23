package com.ps.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ranking_view")
public class RankingEntity {

    @Id
    @Column(name = "position")
    private Long position;

    @Column(name = "nick")
    private String user;

    @Column(name="score")
    private Double score;

    @Column(name="won")
    private Long numberOfWonGames;

    @Column(name="lost")
    private Long numberOfLostGames;

    @Column(name="draw")
    private Long numberOfDrawGames;
}
