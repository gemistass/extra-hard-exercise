package com.intrasoft.extrahardexercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/")

public class Controller {

  @GetMapping("/ping")
  public String ping() throws IOException {
    return "pong";
  }

  @GetMapping("/get")
  public String home() {
    return "This is the get request";
  }

  @GetMapping("/get/check")
  public String home1() {

    return "This is the get check request";
  }
}