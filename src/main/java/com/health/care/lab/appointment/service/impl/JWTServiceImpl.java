package com.health.care.lab.appointment.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JWTServiceImpl {


  private SecretKey secretKey;

  private static final long EXPIRE_TIME = 86400000;

  public JWTServiceImpl(){
   String secretKey ="404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
   byte[] keyBytes = Base64.getDecoder().decode(secretKey.getBytes(StandardCharsets.UTF_8));
   this.secretKey = new SecretKeySpec(keyBytes,"HmacSHA256");
  }
  public String generateToken (UserDetails userDetails){
    return Jwts.builder()
        .subject(userDetails.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))
        .signWith(secretKey)
        .compact();
  }

  public String extractUserName(String token){
    return extractClaim (token, Claims::getSubject);
  }

  private <T> T extractClaim(String token, Function<Claims,T> claimFunction) {
    return claimFunction.apply(Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload());
  }

  public boolean isTokenValid(String token,UserDetails userDetails){
    String userName = extractUserName(token);
    return (userName.equals(userDetails.getUsername())&& isTokenExpired(token));
  }

  private boolean isTokenExpired(String token) {
    return extractClaim(token,Claims::getExpiration).before(new Date());
  }
}
