package com.danielmesquita.blogapi.clients;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ToDoServiceClient {
  public Object[] getAllToDos() {
    RestTemplate restTemplate = new RestTemplate();

    String fakeApiUrl = "https://jsonplaceholder.typicode.com/todos";
    return restTemplate.getForObject(fakeApiUrl, Object[].class);
  }
}
