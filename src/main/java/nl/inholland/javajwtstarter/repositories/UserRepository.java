package nl.inholland.javajwtstarter.repositories;

import nl.inholland.javajwtstarter.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
