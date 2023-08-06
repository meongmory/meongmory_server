package com.meongmory.meongmory.domain.user.repository;

import com.meongmory.meongmory.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUserIdAndIsEnable(Long userId, Boolean isEnable);
  Optional<User> findByPhone(String phone);

}
