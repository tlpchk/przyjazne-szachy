package com.ps.server.repository;

import com.ps.server.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
    UserEntity findByResetToken(String resetToken);
    UserEntity findByVerificationToken(String verificationToken);
}
