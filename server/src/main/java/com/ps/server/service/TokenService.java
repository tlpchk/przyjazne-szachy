package com.ps.server.service;

import com.ps.server.entity.UserEntity;
import com.ps.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    public boolean checkVerifyToken(String token) {
        UserEntity userEntity = userRepository.findByVerificationToken(token);
        if (userEntity != null) {
            userEntity.setIsActive(true);
            userEntity.setVerificationToken("");
            userRepository.save(userEntity);
            return true;
        }
        return false;
    }

    public boolean checkResetToken(String token) {
        UserEntity userEntity = userRepository.findByResetToken(token);
        if (userEntity != null) {
            userEntity.setIsActive(true);
            userEntity.setResetToken("");
            userRepository.save(userEntity);
            return true;
        }
        return false;
    }
}
