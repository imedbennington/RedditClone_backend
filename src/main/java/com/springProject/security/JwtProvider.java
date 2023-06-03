package com.springProject.security;

import org.springframework.security.core.userdetails.User;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PublicKey;
import java.time.Instant;

import org.apache.tomcat.util.buf.Asn1Parser;
import org.springframework.security.core.Authentication;
@Service
@AllArgsConstructor
//@RequiredArgsConstructor
public class JwtProvider {
	private final JwtEncoder jwtEncoder;
    //@Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;
    public JwtProvider() {
		this.jwtEncoder = null;
        // Default constructor
    }
	 public JwtProvider(JwtEncoder jwtEncoder) {
	        this.jwtEncoder = jwtEncoder;
	    }
public String generatetoken (Authentication authentication) {
	User principal = (User) authentication.getPrincipal();
	return generateTokenWithUserName(principal.getUsername());
}
public String generateTokenWithUserName(String username) {
	 
	JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plusMillis(jwtExpirationInMillis))
            .subject(username)
            .claim("scope", "ROLE_USER")
            .build();

    return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
}
public long getJwtExpirationInMillis() {
	        return jwtExpirationInMillis;
	    }
}
