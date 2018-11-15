package com.ps.server.domain;

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
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "origin_row", nullable = false)
    private int originRow;

    @Column(name = "origin_column", nullable = false)
    private int originColumn;

    @Column(name = "destination_row", nullable = false)
    private int destinationRow;

    @Column(name = "destination_column", nullable = false)
    private int destinationColumn;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = true)
    private Player player;

    @Column(name = "created", nullable = false)
    private Date created;
}
