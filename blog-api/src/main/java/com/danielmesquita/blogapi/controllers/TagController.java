package com.danielmesquita.blogapi.controllers;

import com.danielmesquita.blogapi.models.Tag;
import com.danielmesquita.blogapi.services.TagService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/tags")
public class TagController {
  @Autowired private TagService service;

  @PostMapping("/save")
  private @ResponseBody Tag save(@RequestBody Tag tag) {
    return service.save(tag);
  }

  @GetMapping(path = "/getAll")
  private @ResponseBody List<Tag> getAllTags() {
    return service.getAll();
  }

  @GetMapping(path = "/get")
  private @ResponseBody Optional<Tag> getTag(@RequestParam final Long id) {
    return service.get(id);
  }

  @PutMapping(path = "/update")
  private @ResponseBody Tag updateTag(@RequestParam final Long id, @RequestBody Tag tag) {
    return service.update(id, tag);
  }

  @DeleteMapping(path = "/delete")
  private ResponseEntity<?> delete(@RequestParam final Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
