package com.ps.server.entity;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private int Game_ID;
    @Column(name = "nick", nullable = false, length = 64)
    private String nick;
    @Column(name = "e_mail", nullable = false, length = 128)
    private String email ;
    @Column(name = "password", nullable = false, length = 128)
    private String user_password ;

    public int getGame_ID() {
        return Game_ID;
    }

    public void setGame_ID(int game_ID) {
        Game_ID = game_ID;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
