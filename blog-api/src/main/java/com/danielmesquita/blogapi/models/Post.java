package com.danielmesquita.blogapi.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_id")
  private Long postId;

  @Column(name = "post_title")
  private String title;

  @Column(name = "post_content")
  private String content;

  @Column(name = "post_date")
  private Date date;

  @ManyToOne private User userId;

  @OneToMany private List<Tag> tagId;
}
