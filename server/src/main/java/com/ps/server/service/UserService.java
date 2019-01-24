package com.ps.server.service;

import com.ps.server.entity.UserEntity;
import com.ps.server.exception.EmailNotAvailableException;
import com.ps.server.exception.UserNotFoundException;
import com.ps.server.exception.UsernameNotAvailableException;
import com.ps.server.exceptions.UserNotActiveException;
import com.ps.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Finds user by username
     *
     * @param username
     * @return
     * @throws UserNotFoundException
     */
    public UserEntity findByUsername(String username) throws UserNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    /**
     * Finds user by email
     *
     * @param email
     * @return
     * @throws UserNotFoundException
     */
    public UserEntity findByEmail(String email) throws UserNotFoundException {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    /**
     * Checks if credentials are valid
     *
     * @param username
     * @param password
     * @return
     * @throws UserNotFoundException
     * @throws UserNotActiveException
     */
    public boolean areCredentialsValid(String username, String password) throws UserNotFoundException, UserNotActiveException {
        UserEntity user = findByUsername(username);
        if (!user.isActive()) {
            throw new UserNotActiveException();
        }
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }

    /**
     * Creates new User
     *
     * @param username
     * @param password
     * @param email
     * @return
     * @throws UsernameNotAvailableException
     * @throws EmailNotAvailableException
     */
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
        newUser.setEmail(email);
        newUser.setActive(false);
        newUser.setVerificationToken(UUID.randomUUID().toString());
        userRepository.save(newUser);
        return newUser;

    }

    /**
     * Checks if given email is available
     *
     * @param email
     * @return
     */
    private boolean isEmailAvailable(String email) {
        return null == userRepository.findByEmail(email);
    }

    /**
     * Checks if given username is available
     *
     * @param username
     * @return
     */
    private boolean isUsernameAvailable(String username) {
        return null == userRepository.findByUsername(username);
    }

    /**
     * Findes user by ResetToken
     *
     * @param resetToken
     * @return
     */
    public UserEntity findUserByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }

}
