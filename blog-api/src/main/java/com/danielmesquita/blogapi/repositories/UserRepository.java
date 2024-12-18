package com.danielmesquita.blogapi.repositories;

import com.danielmesquita.blogapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String login);
}
