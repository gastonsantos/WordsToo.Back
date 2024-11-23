package com.api.wordToo.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService implements IJwtService {
	
	 @Value("${secret.key.jwt}")
	    private  String SECRET_KEY;
	  

	    public String generateToken(Map<String, Object> claims) {
	        return Jwts.builder()
	                .setClaims(claims)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) 
	              
	                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
	                .compact();
	    }


	    public boolean isTokenValid(String token) {
	    	if(!isTokenExpired(token)) {
	    		return true;
	    	}else {
	    		return false;
	    	}
	     
	    }

	    private boolean isTokenExpired(String token) {
	        Date expiration = Jwts.parser()
	                .setSigningKey(SECRET_KEY)
	                .parseClaimsJws(token)
	                .getBody()
	                .getExpiration();
	        return expiration.before(new Date());
	    }
	
}
