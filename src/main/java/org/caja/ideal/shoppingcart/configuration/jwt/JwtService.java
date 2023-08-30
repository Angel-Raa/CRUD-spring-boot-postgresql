package org.caja.ideal.shoppingcart.configuration.jwt;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.caja.ideal.shoppingcart.entity.models.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${spring.security.secrets.key}")
    private String SECRETE_KEY;
    @Value("${spring.jwt.expired}")
    private long EXPIRATION_IN_SECONDS_MILLIS;

    public String generateToken(Users user, Map<String, Object> claims) {
        Date issued = new Date(System.currentTimeMillis());
        Date expiration = new Date(issued.getTime() + (EXPIRATION_IN_SECONDS_MILLIS * 60 * 1000));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(issued)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generatorKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Key generatorKey() {
        byte[] key = Decoders.BASE64.decode(SECRETE_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    public Map<String, Object> generateExtraClaims(Users user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getUsername());
        extraClaims.put("Role", user.getRole().name());
        extraClaims.put("permissions", user.getAuthorities());
        return extraClaims;
    }

    public String extractUsername(String jwts) {
        return extractAllClaims(jwts);

    }

    private String extractAllClaims(String jwts) {
        return Jwts.parserBuilder()
                .setSigningKey(generatorKey())
                .build()
                .parseClaimsJws(jwts)
                .getBody()
                .getSubject();
    }
}
