package com.ps.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "matches")
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;

    @OneToOne
    @JoinColumn(name = "player_id")
    private UserEntity player;

    @OneToOne
    @JoinColumn(name = "opponent_id")
    private UserEntity opponent;

    @Column(name = "player_points")
    private Short playerPoints;

}
