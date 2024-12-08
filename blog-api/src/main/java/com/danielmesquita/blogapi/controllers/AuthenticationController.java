package com.danielmesquita.blogapi.controllers;

import com.danielmesquita.blogapi.request.AuthRequest;
import com.danielmesquita.blogapi.response.AuthResponse;
import com.danielmesquita.blogapi.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
  @Autowired AuthenticationManager authenticationManager;
  @Autowired AuthenticationService authenticationService;

  @PostMapping(path = "/login")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public AuthResponse login(@RequestBody final AuthRequest authRequest) {
    UsernamePasswordAuthenticationToken userAuthenticationToken =
        new UsernamePasswordAuthenticationToken(
            authRequest.getUsername(), authRequest.getPassword());

    authenticationManager.authenticate(userAuthenticationToken);

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    System.out.println(encoder.encode("password"));

    return new AuthResponse(authenticationService.getToken(authRequest));
  }
}
