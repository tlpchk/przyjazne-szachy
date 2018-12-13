package com.ps.server.entity;

import com.ps.server.enums.PlayerType;
import com.ps.server.Logic.Color;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_type", nullable = false)
    private PlayerType playerType;

    public Long getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
