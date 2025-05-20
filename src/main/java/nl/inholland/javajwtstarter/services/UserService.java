package nl.inholland.javajwtstarter.services;

import nl.inholland.javajwtstarter.models.DTO.LoginRequestDTO;
import nl.inholland.javajwtstarter.models.DTO.LoginResponseDTO;
import nl.inholland.javajwtstarter.models.User;

public interface UserService {
    User createUser(User user);
    LoginResponseDTO loginUser(LoginRequestDTO request);
}
