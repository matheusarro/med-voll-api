package med.voll.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class ApiTokenService {
    private final String secret;

    public ApiTokenService(@Value("${api.security.token.secret}") String secret) {
        this.secret = secret;
    }

    public String generateToken(UserEntity user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Voll.med API")
                    .withSubject(user.getUsername())
                    .withExpiresAt(tokenExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    private Instant tokenExpirationDate() {
        return LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
