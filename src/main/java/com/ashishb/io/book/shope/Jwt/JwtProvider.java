package com.ashishb.io.book.shope.Jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtProvider {

    private static final Integer TOKEN_VALID = 3600 * 5;
    private String SECRET = "yoyobees";

    private Claims getAllClaimsFromToken(String token ){

        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimResolver){
        final Claims claims =    getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    public String getUsernameFromToken(String token){
        return getClaimsFromToken(token,Claims::getSubject);
    }

    private Date getExpirationDateFromToken(String token){
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String userName =  getUsernameFromToken(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }

    private boolean isTokenExpired(String token){
        final Date expirationTime = getExpirationDateFromToken(token);
        return expirationTime.before(new Date());
    }

    public String generateJwtToken(UserDetails userDetails){
        Map<String , Object> claims =new HashMap<>();
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+TOKEN_VALID*1000))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
}
