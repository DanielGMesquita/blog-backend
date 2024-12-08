package com.danielmesquita.blogapi.consumers;

import com.danielmesquita.blogapi.models.Comment;
import com.danielmesquita.blogapi.models.User;
import com.danielmesquita.blogapi.repositories.CommentRepository;
import com.danielmesquita.blogapi.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {
  @Autowired CommentRepository commentRepository;

  @Autowired UserRepository userRepository;

  private static final Logger logger = LoggerFactory.getLogger(RabbitConsumer.class);

  @RabbitListener(queues = "${blog-api.rabbitmq.queue}")
  public void receivedMessage(Comment comment) {
    User user =
        userRepository
            .findById(comment.getUser().getUserId())
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
    comment.setUser(user);
    logger.info("Received message from RabbitMQ: {}", comment);
    commentRepository.save(comment);
  }
}
