package com.local.project.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

   private static final String KEY = "Practice project";

   public String generateToken(UserDetails userDetails) {
      return Jwts.builder()
         .setSubject(userDetails.getUsername())
         .setIssuedAt(new Date())
         .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
         .signWith(SignatureAlgorithm.HS256, KEY)
         .compact();
   }

   public boolean validatorToken(String jwt, UserDetails userDetails) {
      return userDetails.getUsername().equals(extractUsername(jwt)) && !isTokenExpired(jwt);
   }

   public String extractUsername(String jwt) {
      return getClaims(jwt).getSubject();
   }

   public boolean isTokenExpired(String jwt) {
      return getClaims(jwt).getExpiration().before(new Date());
   }

   private Claims getClaims(String jwt) {
      return Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt).getBody();
   }

}
