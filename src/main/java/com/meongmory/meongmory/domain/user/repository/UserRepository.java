package com.meongmory.meongmory.domain.user.repository;

import com.meongmory.meongmory.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserIdAndIsEnable(long userId, boolean isEnable);
}
