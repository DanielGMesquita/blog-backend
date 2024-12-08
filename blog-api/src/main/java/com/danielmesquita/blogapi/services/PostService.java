package com.danielmesquita.blogapi.services;

import com.danielmesquita.blogapi.models.Post;
import java.util.List;
import java.util.Optional;

public interface PostService {
  Post save(Post post);

  List<Post> getAll();

  Optional<Post> get(Long id);

  Post update(Long id, Post post);

  void delete(Long id);
}
