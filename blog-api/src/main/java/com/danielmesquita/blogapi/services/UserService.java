package com.danielmesquita.blogapi.services;

import com.danielmesquita.blogapi.models.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
  User save(User user);

  List<User> getAll();

  Optional<User> get(Long id);

  User update(Long id, User user);

  void delete(Long id);
}
