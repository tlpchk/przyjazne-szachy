package com.ps.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "nick", nullable = false, length = 64)
    private String username;

    @Column(name = "e_mail", length = 128)
    private String email ;

    @Column(name = "password", nullable = false, length = 128)
    private String password ;
}
