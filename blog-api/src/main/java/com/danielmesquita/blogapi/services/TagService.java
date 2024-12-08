package com.danielmesquita.blogapi.services;

import com.danielmesquita.blogapi.models.Tag;
import java.util.List;
import java.util.Optional;

public interface TagService {
  Tag save(Tag tag);

  List<Tag> getAll();

  Optional<Tag> get(Long id);

  Tag update(Long id, Tag tag);

  void delete(Long id);
}
