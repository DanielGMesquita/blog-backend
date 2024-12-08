package com.danielmesquita.blogapi.repositories;

import com.danielmesquita.blogapi.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {}
