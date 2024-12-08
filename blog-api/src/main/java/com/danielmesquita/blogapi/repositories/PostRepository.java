package com.danielmesquita.blogapi.repositories;

import com.danielmesquita.blogapi.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {}
