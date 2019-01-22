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
@Table(name = "player")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", columnDefinition = "enum")
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_type", columnDefinition = "enum", nullable = false)
    private PlayerType playerType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
