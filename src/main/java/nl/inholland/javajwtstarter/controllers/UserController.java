package nl.inholland.javajwtstarter.controllers;

import nl.inholland.javajwtstarter.models.DTO.LoginRequestDTO;
import nl.inholland.javajwtstarter.models.DTO.LoginResponseDTO;
import nl.inholland.javajwtstarter.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody LoginRequestDTO request) {
        return userService.loginUser(request);
    }
}
