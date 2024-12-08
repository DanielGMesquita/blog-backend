package com.danielmesquita.blogapi.services;

import com.danielmesquita.blogapi.request.AuthRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {
  String getToken(AuthRequest authRequest);

  String validateJwt(String token);
}
