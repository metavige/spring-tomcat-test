package com.example.springtomcattest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springtomcattest.data.User;
import com.example.springtomcattest.data.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class DemoController {

  private UserRepository userRepo;

  @GetMapping("/")
  public String hello() {

    return "Hello World!";
  }

  @GetMapping("/users")
  public List<User> getUsers() { return userRepo.findAll(); }
}
