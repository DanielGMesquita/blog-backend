package com.danielmesquita.blogapi.controllers;

import com.danielmesquita.blogapi.models.Post;
import com.danielmesquita.blogapi.services.PostService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/posts")
public class PostController {
  @Autowired private PostService service;

  @PostMapping("/save")
  private @ResponseBody Post save(@RequestBody Post post) {
    return service.save(post);
  }

  @GetMapping(path = "/getAll")
  private @ResponseBody List<Post> getAllPosts() {
    return service.getAll();
  }

  @GetMapping(path = "/get")
  private @ResponseBody Optional<Post> getPost(@RequestParam final Long id) {
    return service.get(id);
  }

  @PutMapping(path = "/update")
  private @ResponseBody Post updatePost(@RequestParam final Long id, @RequestBody Post post) {
    return service.update(id, post);
  }

  @DeleteMapping(path = "/delete")
  private ResponseEntity<?> delete(@RequestParam final Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
