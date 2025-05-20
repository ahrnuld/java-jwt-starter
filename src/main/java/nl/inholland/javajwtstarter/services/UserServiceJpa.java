package nl.inholland.javajwtstarter.services;

import nl.inholland.javajwtstarter.models.DTO.LoginRequestDTO;
import nl.inholland.javajwtstarter.models.DTO.LoginResponseDTO;
import nl.inholland.javajwtstarter.models.User;
import nl.inholland.javajwtstarter.repositories.UserRepository;
import nl.inholland.javajwtstarter.security.JwtProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceJpa implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UserServiceJpa(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User createUser(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public LoginResponseDTO loginUser(LoginRequestDTO request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtProvider.createToken(user.getUsername(), user.getRoles());
        return new LoginResponseDTO(token);
    }
}
