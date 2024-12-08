package com.danielmesquita.blogapi.services;

import com.danielmesquita.blogapi.models.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentService {
  Comment save(Comment comment);

  List<Comment> getAll();

  Optional<Comment> get(Long id);

  Comment update(Long id, Comment comment);

  void delete(Long id);

  Comment send(Comment comment);
}
