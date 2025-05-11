package nl.inholland.javajwtstarter.repositories;

import nl.inholland.javajwtstarter.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
