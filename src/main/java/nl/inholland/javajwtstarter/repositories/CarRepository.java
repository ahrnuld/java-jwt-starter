package nl.inholland.javajwtstarter.repositories;

import nl.inholland.javajwtstarter.models.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    Iterable<Car> findAllByBrandLike(String brand);
}
