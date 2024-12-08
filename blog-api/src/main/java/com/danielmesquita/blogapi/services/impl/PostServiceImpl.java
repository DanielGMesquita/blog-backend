package com.danielmesquita.blogapi.services.impl;

import com.danielmesquita.blogapi.models.Post;
import com.danielmesquita.blogapi.repositories.PostRepository;
import com.danielmesquita.blogapi.services.PostService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
  @Autowired private PostRepository repository;

  @Override
  public Post save(Post post) {
    return repository.save(post);
  }

  @Override
  public List<Post> getAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Post> get(Long id) {
    try {
      return repository.findById(id);
    } catch (Exception e) {
      throw new DataAccessException(e.getMessage()) {};
    }
  }

  @Override
  public Post update(Long id, Post post) {
    Post postToEdit =
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not found"));

    postToEdit.setContent(post.getContent());
    postToEdit.setTitle(post.getTitle());

    return repository.save(post);
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }
}
