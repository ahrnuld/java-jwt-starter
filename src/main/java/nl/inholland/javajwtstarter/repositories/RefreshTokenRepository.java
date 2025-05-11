package nl.inholland.javajwtstarter.repositories;

import nl.inholland.javajwtstarter.models.RefreshToken;
import nl.inholland.javajwtstarter.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    int deleteByUser(User user);
}
