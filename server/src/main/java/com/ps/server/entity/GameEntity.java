package com.ps.server.entity;

import com.ps.server.enums.GameStatus;
import com.ps.server.enums.GameType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "first_player_id", nullable = false)
    private PlayerEntity firstPlayer;

    @ManyToOne
    @JoinColumn(name = "second_player_id")
    private PlayerEntity secondPlayer;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    @Column(name = "starting_time")
    private Date startingTime;

}
