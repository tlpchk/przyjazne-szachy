package com.ps.server.entity;

import com.ps.server.Logic.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PieceSetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;

    @OneToMany(mappedBy = "pieceSet")
    private List<PieceEntity> pieceSet;

    @ManyToOne
    private GameEntity game;

}
