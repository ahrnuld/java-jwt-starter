package nl.inholland.javajwtstarter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    private long id;

    private String firstName;

    private String lastName;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Car> cars;
}
