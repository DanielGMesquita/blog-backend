package com.danielmesquita.blogapi.services.impl;

import com.danielmesquita.blogapi.models.Comment;
import com.danielmesquita.blogapi.models.User;
import com.danielmesquita.blogapi.repositories.CommentRepository;
import com.danielmesquita.blogapi.services.CommentService;
import com.danielmesquita.blogapi.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
  @Autowired private CommentRepository repository;

  @Autowired private UserService userService;

  @Autowired private AmqpTemplate template;

  @Value("${blog-api.rabbitmq.exchange}")
  private String exchange;

  @Value("${blog-api.rabbitmq.rountingkey")
  private String routingKey;

  private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

  @Override
  public Comment save(Comment comment) {
    return repository.save(comment);
  }

  @Override
  public Comment send(Comment comment) {
    User commentUser =
        userService
            .get(comment.getUser().getUserId())
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
    comment.setUser(commentUser);

    template.convertAndSend(exchange, routingKey, comment);
    logger.info("Send msg = {}", comment);
    return comment;
  }

  @Override
  public List<Comment> getAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Comment> get(Long id) {
    return repository.findById(id);
  }

  @Override
  public Comment update(Long id, Comment comment) {
    Comment commentToEdit =
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment not found"));

    commentToEdit.setCommentText(comment.getCommentText());

    return repository.save(comment);
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }
}
