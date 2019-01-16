package com.ps.server.service;

import com.ps.server.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by pdybka on 03.06.16.
 *
 *
 */
public class ContextUser extends org.springframework.security.core.userdetails.User {

    private final UserEntity user;


    public ContextUser(UserEntity user) {

        super(user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                Collections.singleton(new SimpleGrantedAuthority("create")
                ));

        this.user = user;
    }

    public UserEntity getUserEntity() {
        return user;
    }
}