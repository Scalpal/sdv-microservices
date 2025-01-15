package com.example.sdvauthapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    };

    public Date getExpirationDate(String token){
        return getClaims(token).getExpiration();
    }

    public boolean isExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }

    public Claims getClaims(String token) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generate(String userId, String tokenType){
        Map<String, String> claims = Map.of("id", userId, "type", tokenType);

        long expirationMillisecond = "ACCESS".equalsIgnoreCase(tokenType) ?
                Long.parseLong(expiration) * 1000 :
                Long.parseLong(expiration) * 1000 * 5;

        final Date now = new Date();
        final Date exp = new Date(now.getTime() * expirationMillisecond);

        return Jwts.builder()
                .claims(claims)
                .subject(userId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(exp)
                .signWith((SecretKey) key(), Jwts.SIG.HS256)
                .compact();
    }
}
