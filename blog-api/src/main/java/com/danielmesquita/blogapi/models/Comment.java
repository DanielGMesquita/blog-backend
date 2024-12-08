package com.danielmesquita.blogapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id")
  private Long commentId;

  @Column(name = "comment_text")
  private String commentText;

  @ManyToOne private User user;

  @ManyToOne private Post post;
}
