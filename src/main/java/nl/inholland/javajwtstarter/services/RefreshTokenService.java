package nl.inholland.javajwtstarter.services;

import nl.inholland.javajwtstarter.models.RefreshToken;
import nl.inholland.javajwtstarter.models.User;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(User user);
    RefreshToken verifyExpiration(RefreshToken token);
    void deleteByUser(User user);
}
