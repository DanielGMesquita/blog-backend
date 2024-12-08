package com.danielmesquita.blogapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthRequest {
  final String username;
  final String password;
}
