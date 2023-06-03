package com.springProject.service;
import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.springProject.model.RefreshToken;
import com.springProject.repository.RefreshTokenRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {
	 private final RefreshTokenRepository refreshTokenRepository;

	    public RefreshToken generateRefreshToken() {
	        RefreshToken refreshToken = new RefreshToken();
	        refreshToken.setToken(UUID.randomUUID().toString());
	        refreshToken.setCreatedDate(Instant.now());

	        return refreshTokenRepository.save(refreshToken);
	    }
}
