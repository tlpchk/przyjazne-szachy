package com.ps.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "nick", nullable = false, length = 64)
    private String username;

    @Column(name = "e_mail", length = 128)
    private String email ;

    @Column(name = "password", nullable = false, length = 128)
    private String password ;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "verification_token")
    private String verificationToken;

    @Column(name = "reset_token")
    private String resetToken;

    public UserEntity() {
        verificationToken = UUID.randomUUID().toString();
        System.out.println(verificationToken);
    }
}
