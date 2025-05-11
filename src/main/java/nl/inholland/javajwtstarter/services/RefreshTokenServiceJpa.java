package nl.inholland.javajwtstarter.services;

import nl.inholland.javajwtstarter.models.RefreshToken;
import nl.inholland.javajwtstarter.models.User;
import nl.inholland.javajwtstarter.repositories.RefreshTokenRepository;
import nl.inholland.javajwtstarter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenServiceJpa implements RefreshTokenService {

    @Value("${app.jwt.refreshExpirationMs:604800000}") // 7 days
    private Long refreshExpirationMs;

    private final RefreshTokenRepository tokenRepository;
    private final UserRepository userRepository;

    public RefreshTokenServiceJpa(RefreshTokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshToken createRefreshToken(User user) {
        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Instant.now().plusMillis(refreshExpirationMs));
        return tokenRepository.save(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.isExpired()) {
            tokenRepository.delete(token);
            throw new RuntimeException("Refresh token expired. Please login again.");
        }
        return token;
    }

    public void deleteByUser(User user) {
        tokenRepository.deleteByUser(user);
    }
}
