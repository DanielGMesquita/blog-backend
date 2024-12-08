package com.danielmesquita.blogapi.controllers;

import com.danielmesquita.blogapi.clients.ToDoServiceClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoController {
  @Autowired ToDoServiceClient serviceClient;

  @GetMapping("/getFakeApiClient")
  public @ResponseBody List<Object> getAll() {
    return List.of(serviceClient.getAllToDos());
  }
}
