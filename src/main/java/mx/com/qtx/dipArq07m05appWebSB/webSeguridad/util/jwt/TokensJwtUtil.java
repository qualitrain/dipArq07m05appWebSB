package mx.com.qtx.dipArq07m05appWebSB.webSeguridad.util.jwt;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;

@Component
public class TokensJwtUtil implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(TokensJwtUtil.class);

    private static final String ISSUER = "mx.com.qtx";
    private static final String SECRET = UUID.randomUUID().toString();
    private static final long EXPIRATION_TIME = 86400000; // 24 horas

    public static String generarToken(String username) {

        Key llave = Keys.hmacShaKeyFor(SECRET.getBytes());
        //secretKeyFor(SignatureAlgorithm.HS512);

        return Jwts.builder()
                .issuer(ISSUER)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).
                .signWith(llave)
                .compact();
    }

    @Override
    public void run(String... args) throws Exception {
        String token = generarToken("alex");
        log.info("Token: " + token);

        Claims declaraciones = Jwts.claims()
                                    .putAll(Map.of("usuario","humberto","role", "ADMIN" ))
                                    .build();
    }

}
