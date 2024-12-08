package com.danielmesquita.blogapi.controllers;

import com.danielmesquita.blogapi.models.Comment;
import com.danielmesquita.blogapi.services.CommentService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/comments")
public class CommentController {
  @Autowired private CommentService service;

  @PostMapping("/save")
  private @ResponseBody Comment save(@RequestBody Comment comment) {
    return service.send(comment);
  }

  @GetMapping(path = "/getAll")
  private @ResponseBody List<Comment> getAllComments() {
    return service.getAll();
  }

  @GetMapping(path = "/get")
  private @ResponseBody Optional<Comment> getComment(@RequestParam final Long id) {
    return service.get(id);
  }

  @PutMapping(path = "/update")
  private @ResponseBody Comment updateComment(
      @RequestParam final Long id, @RequestBody Comment comment) {
    return service.update(id, comment);
  }

  @DeleteMapping(path = "/delete")
  private ResponseEntity<?> delete(@RequestParam final Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
