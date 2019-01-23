package com.ps.server.controller;

import com.ps.server.dto.LoginResponseDTO;
import com.ps.server.dto.UserDTO;
import com.ps.server.dto.UserDetailsDTO;
import com.ps.server.entity.UserEntity;
import com.ps.server.exception.EmailNotAvailableException;
import com.ps.server.exception.GameNotExistException;
import com.ps.server.exception.UserNotFoundException;
import com.ps.server.exception.UsernameNotAvailableException;
import com.ps.server.exceptions.UserNotActiveException;
import com.ps.server.service.EmailService;
import com.ps.server.service.PlayerService;
import com.ps.server.service.RankingService;
import com.ps.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private UserService userService;

    @Autowired
    private RankingService rankingService;


    /**
     * Log in user.
     *
     * @param userDTO describes user to be logged in
     * @return LoginResponseDTO which contains info about success or fail
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponseDTO loginUser(@RequestBody UserDTO userDTO) {
        String message = "Hello!";
        boolean isUserAvailable = true;
        try {
            isUserAvailable = userService.areCredentialsValid(userDTO.getUsername(), userDTO.getPassword());
        } catch (UserNotFoundException e) {
            isUserAvailable = false;
            message = "Zły login lub hasło";
        } catch (UserNotActiveException e) {
            isUserAvailable = false;
            message = "Użytkownik nie aktywny";
        } finally {
            LoginResponseDTO response = new LoginResponseDTO(isUserAvailable, message);
            return response;
        }
    }


    /**
     * Registers user.
     *
     * @param userDTO describes user to be registered
     * @param request
     * @return LoginResponseDTO which contains info about success or fail
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public LoginResponseDTO registerPlayer(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        String message = "Hello!";
        boolean success = true;
        try {
            UserEntity newUser = userService.createNewUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
            String appUrl = request.getScheme() + "://" + request.getServerName();
            emailService.sendVerificationEmail(newUser, appUrl);
        } catch (UsernameNotAvailableException e) {
            message = "Nazwa użytkownika zajęta";
            success = false;
        } catch (EmailNotAvailableException e) {
            message = "Email zajęty";
            success = false;
        }
        LoginResponseDTO response = new LoginResponseDTO(success, message);
        return response;
    }

    /**
     * Reterns info about user.
     *
     * @param username username of user
     * @return UserDetailsDTO which contains info about user
     * @throws UserNotFoundException when there is no user with given username
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public UserDetailsDTO getUserInfo(@RequestBody String username) throws UserNotFoundException {
        return rankingService.getUserDetails(username);
    }

    /**
     * Logouts user and finished all his started games.
     *
     * @param username username of user
     * @throws UserNotFoundException when there is no user with given username
     * @throws GameNotExistException when there is no game for user
     */
    @RequestMapping(value = "/bye", method = RequestMethod.POST)
    public void logoutUser(@RequestBody String username) throws UserNotFoundException, GameNotExistException {
        UserEntity userEntity = userService.findByUsername(username);
        playerService.finishAllGamesForUser(userEntity);
    }


}
