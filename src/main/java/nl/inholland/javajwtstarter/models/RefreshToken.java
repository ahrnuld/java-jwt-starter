package nl.inholland.javajwtstarter.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;

@Entity
@Data
public class RefreshToken {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    public boolean isExpired() {
        return expiryDate.isBefore(Instant.now());
    }
}