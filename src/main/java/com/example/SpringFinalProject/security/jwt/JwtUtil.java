package com.example.SpringFinalProject.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "12b506ae8a6f690256ca6a04506b68ef82f57217be9f8f752af15c413a48d9f0ebc0cd1cd" +
            "59f501b57fe07c2cc14534b138bff0513486e13498ed07b794b07b50fb62575c58b3007c1ff1e31acf1df8d27324d8eb25bcc60ead" +
            "22cdf8b185e271bef248838f65d49dcec370149194a63efae53ea644faafa8643fe52b12e4d6762b6d406b9a8a6f6566b5dc84d38d" +
            "42f684ac9b7005f419ada4757378e6f1aceb554532bb495478238b35a45bc762cbf0be06f2fb13917e0540900e960915c683face20" +
            "9ae1dbaf7e45c7c63cb3e75376c99a1210e5010b0e27fe2ebd36183a64228519228103f3d5863b8a960d7045318eba18de300c64a8" +
            "2c17079f2d7a47c";

    public static String generateToken(User user) {
        String authority = user.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("USER");

        return Jwts.builder()
                .subject(user.getUsername())
                .claim("authority", authority)
                .expiration(new Date(System.currentTimeMillis() + 3_600_000))
                .signWith(getSigningKey())
                .compact();
    }

    public static Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static boolean isTokenValid(String token) {
        return !isExpired(token);
    }

    private static boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private static SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static String getAuthorityFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.get("authority", String.class);
    }
}