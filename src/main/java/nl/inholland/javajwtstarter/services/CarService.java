package nl.inholland.javajwtstarter.services;

import nl.inholland.javajwtstarter.exceptions.InhollandValidationException;
import nl.inholland.javajwtstarter.models.Car;

import java.util.List;

public interface CarService {
    List<Car> getAll();

    Car getById(long id);

    Car add(Car car);

    Car update(Car car, long id) throws InhollandValidationException;

    void delete(long id);
}
