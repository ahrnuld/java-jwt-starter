package nl.inholland.javajwtstarter.repositories;

import nl.inholland.javajwtstarter.models.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
