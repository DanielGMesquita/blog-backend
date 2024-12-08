package com.danielmesquita.blogapi.exceptions;

import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Exception {
  private Instant timestamp;
  private Integer status;
  private String error;
  private String message;
  private String path;
}
