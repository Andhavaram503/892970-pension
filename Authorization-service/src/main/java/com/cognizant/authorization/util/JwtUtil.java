package com.cognizant.authorization.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class JwtUtil {

	private static final String SECRET_KEY = "sec2d2a2dakd21Zaksjywqekdemo[osejoew,askjoqawqh823-3904798sdjaolsdniuanxcxuret";

    public String extractUsername(String token) {
    	log.info("Extracting userName ---> JwtUtil");
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
    	log.info("Extracting Token Expiration ---> JwtUtil");
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(token);
        log.info("Extracting claims {} ---> JwtUtil", claims);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
    	
    	log.info("Extracting All Claims ---> JwtUtil");
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
    	log.info("Checking Token Expiration isTokenExpired ---> JwtUtil");
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
    	log.info("Checking Token Generation  ---> JwtUtil");
        Map<String, Object> claims = new HashMap<>();
    	log.info("Getting Claims of the User  ---> JwtUtil");
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
    	
    	log.info("Creating Jwt Token --->  JwtUtil");
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(SignatureAlgorithm.HS384, SECRET_KEY).compact(); //HS256 
    }
    public boolean validateToken(String token,UserDetails userDetails) {    	    	
		
		  String username = extractUsername(token);
		  log.info("UserName extraction from Jwt Token --->  JwtUtil");
		  if(username.equals(userDetails.getUsername()) && !isTokenExpired(token)) 
		  {
		  return true; 
		  } 
		  return false;
		         
        
		/*
		 * try { String username = extractUsername(token); return
		 * (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); }
		 * catch(SignatureException exception){ return false; } catch(Exception
		 * exception) { return false; }
		 */
        
        
        
    }

}
