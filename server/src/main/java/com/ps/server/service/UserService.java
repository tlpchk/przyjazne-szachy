package com.ps.server.service;

import com.ps.server.dto.UserDetailsDTO;
import com.ps.server.entity.UserEntity;
import com.ps.server.exception.EmailNotAvailableException;
import com.ps.server.exception.UserNotFoundException;
import com.ps.server.exception.UsernameNotAvailableException;
import com.ps.server.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserEntity findByUsername(String username) throws UserNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    public boolean areCredentialsValid(String username, String password) throws UserNotFoundException {
        UserEntity user = findByUsername(username);
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }

    public UserEntity createNewUser(String username, String password, String email) throws UsernameNotAvailableException, EmailNotAvailableException {
        if (!isUsernameAvailable(username)) {
            throw new UsernameNotAvailableException();
        }
        if (!isEmailAvailable(email)) {
            throw new EmailNotAvailableException();
        }
        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(newUser);
        return newUser;

    }

    private boolean isEmailAvailable(String email) {
        return null == userRepository.findByEmail(email);
    }

    private boolean isUsernameAvailable(String username) {
        return null == userRepository.findByUsername(username);
    }


}
