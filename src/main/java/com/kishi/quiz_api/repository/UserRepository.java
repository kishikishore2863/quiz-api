package com.kishi.quiz_api.repository;

import com.kishi.quiz_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
  public User findByEmail(String email);
}
