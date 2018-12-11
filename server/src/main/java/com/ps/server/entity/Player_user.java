package com.ps.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "Player_user")
public class Player_user {

    @Column(name = "ID_user", nullable = false)
    private int ID_user;
    @Column(name = "ID_player", nullable = false)
    private int ID_player;

    public int getID_user() {
        return ID_user;
    }

    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }

    public int getID_player() {
        return ID_player;
    }

    public void setID_player(int ID_player) {
        this.ID_player = ID_player;
    }
}
