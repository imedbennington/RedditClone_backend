package com.springProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springProject.model.VerificationToken;

public interface verificationTokenRepository extends JpaRepository<VerificationToken, Long>{

}
