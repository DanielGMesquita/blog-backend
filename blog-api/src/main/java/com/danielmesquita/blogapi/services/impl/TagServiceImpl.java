package com.danielmesquita.blogapi.services.impl;

import com.danielmesquita.blogapi.models.Tag;
import com.danielmesquita.blogapi.repositories.TagRepository;
import com.danielmesquita.blogapi.services.TagService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
  @Autowired private TagRepository repository;

  @Override
  public Tag save(Tag tag) {
    try {
      return repository.save(tag);
    } catch (DataIntegrityViolationException e) {
      throw new RuntimeException("Tag already exists");
    }
  }

  @Override
  public List<Tag> getAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Tag> get(Long id) {
    return repository.findById(id);
  }

  @Override
  public Tag update(Long id, Tag tag) {
    Tag tagToEdit =
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tag not found"));

    tagToEdit.setTagTitle(tag.getTagTitle());

    return repository.save(tag);
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }
}
