package nl.inholland.javajwtstarter.configuration;

import nl.inholland.javajwtstarter.models.Car;
import nl.inholland.javajwtstarter.models.Person;
import nl.inholland.javajwtstarter.models.Role;
import nl.inholland.javajwtstarter.models.User;
import nl.inholland.javajwtstarter.services.CarService;
import nl.inholland.javajwtstarter.services.PersonService;
import nl.inholland.javajwtstarter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class DataSeeder implements ApplicationRunner {

    @Autowired
    CarService carService;

    @Autowired
    PersonService personService;

    @Autowired
    UserService userService;

    @Override
    public void run(ApplicationArguments args) {

        Person person1 = new Person(0, "Luc", "Besson", new ArrayList<>());
        personService.add(person1);

        Car car1 = new Car(0, "Mercedes", 2000, "A class", person1);
        carService.add(car1);

        User user1 = new User(0, "admin", "admin", Collections.singletonList(Role.ROLE_ADMIN));
        userService.createUser(user1);
    }
}
