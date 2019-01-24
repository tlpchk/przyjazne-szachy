package com.ps.server.service;

import com.ps.server.entity.UserEntity;
import com.ps.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Checks verify token
     *
     * @param token token to be verified
     * @return true when token was valid, false otherwise
     */
    public boolean checkVerifyToken(String token) {
        UserEntity userEntity = userRepository.findByVerificationToken(token);
        if (userEntity != null) {
            userEntity.setActive(true);
            userEntity.setVerificationToken("");
            userRepository.save(userEntity);
            return true;
        }
        return false;
    }

    /**
     * Checks reste token
     *
     * @param token token to be checked
     * @return true when token is valid, false otherwise
     */
    public boolean checkResetToken(String token) {
        UserEntity userEntity = userRepository.findByResetToken(token);
        if (userEntity != null) {
            return true;
        }
        return false;
    }

    /**
     * Changes password to new password with given token.
     *
     * @param token    token for password change
     * @param password new password
     * @return true if success, false oterwise
     */
    public boolean changePassword(String token, String password) {
        UserEntity userEntity = userRepository.findByResetToken(token);
        if (userEntity != null) {
            userEntity.setActive(true);
            userEntity.setResetToken("");
            userEntity.setPassword(bCryptPasswordEncoder.encode(password));
            userRepository.save(userEntity);
            return true;
        }
        return false;
    }
}
