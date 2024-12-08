package com.danielmesquita.blogapi.controllers;

import com.danielmesquita.blogapi.models.User;
import com.danielmesquita.blogapi.services.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {
  @Autowired private UserService service;

  @PostMapping("/save")
  public @ResponseBody User save(@RequestBody User user) {
    return service.save(user);
  }

  @Cacheable(value = "usersCache", key = "'allUsers'")
  @GetMapping(path = "/getAll")
  public @ResponseBody List<User> getAllUsers() {
    return service.getAll();
  }

  @GetMapping(path = "/get")
  public @ResponseBody Optional<User> getUser(@RequestParam final Long id) {
    return service.get(id);
  }

  @PutMapping(path = "/update")
  public @ResponseBody User updateUser(@RequestParam final Long id, @RequestBody User user) {
    return service.update(id, user);
  }

  @DeleteMapping(path = "/delete")
  public ResponseEntity<?> delete(@RequestParam final Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
