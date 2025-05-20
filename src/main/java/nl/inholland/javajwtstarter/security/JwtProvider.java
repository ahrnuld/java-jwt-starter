package nl.inholland.javajwtstarter.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import nl.inholland.javajwtstarter.models.Role;
import nl.inholland.javajwtstarter.services.UserDetailsServiceJpa;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {

    private final String SECRET_KEY = "bWVnYXRocm9uLWJyb3duaWUtdG90YWwtY2FsaXR5IQ==";
    SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    private final UserDetailsServiceJpa userDetailsService;

    public JwtProvider(UserDetailsServiceJpa userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String createToken(String username, List<Role> roles)  {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000); // 1 hour

        return Jwts.builder()
                .subject(username)
                .claim("auth", roles.stream().map(Role::name).toList())
                .issuedAt(new Date())
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        try {
            Claims claims = Jwts.parser().verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String username = claims.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        } catch(Exception e) {
            throw new JwtException("Bearer token is invalid");
        }
    }

}
