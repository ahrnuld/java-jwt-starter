package nl.inholland.javajwtstarter.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;
}
