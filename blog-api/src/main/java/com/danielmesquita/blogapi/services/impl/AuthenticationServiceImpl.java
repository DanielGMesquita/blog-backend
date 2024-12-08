package com.danielmesquita.blogapi.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.danielmesquita.blogapi.models.User;
import com.danielmesquita.blogapi.repositories.UserRepository;
import com.danielmesquita.blogapi.request.AuthRequest;
import com.danielmesquita.blogapi.services.AuthenticationService;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link AuthenticationService} interface that provides methods for
 * authenticating users and managing JWT tokens.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  @Autowired private UserRepository repository;

  /**
   * Loads a user by their username.
   *
   * @param login the username of the user to load
   * @return the details of the user
   * @throws UsernameNotFoundException if the user is not found
   */
  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    return repository.findByUsername(login);
  }

  /**
   * Generates a JWT token for the given authentication request.
   *
   * @param authRequest the authentication request containing user credentials
   * @return a JWT token as a string
   */
  @Override
  public String getToken(AuthRequest authRequest) {
    User user = repository.findByUsername(authRequest.getUsername());
    return generateToken(user);
  }

  /**
   * Generates a JWT token for the specified user.
   *
   * @param user the user for whom the token is generated
   * @return a JWT token as a string
   * @throws RuntimeException if token creation fails
   */
  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256("my-secret");

      return JWT.create()
          .withIssuer("blog-api")
          .withSubject(user.getUsername())
          .withExpiresAt(getExpirationDate())
          .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Fail to generate token: " + exception.getMessage());
    }
  }

  /**
   * Validates a JWT token and returns the subject (username) if the token is valid.
   *
   * @param token the JWT token to validate
   * @return the subject (username) if the token is valid, or an empty string if not
   */
  @Override
  public String validateJwt(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256("my-secret");

      return JWT.require(algorithm).withIssuer("blog-api").build().verify(token).getSubject();
    } catch (JWTVerificationException e) {
      return "";
    }
  }

  /**
   * Calculates the expiration date of the JWT token.
   *
   * @return the expiration date as an {@link Instant}
   */
  private Instant getExpirationDate() {
    return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
  }
}
