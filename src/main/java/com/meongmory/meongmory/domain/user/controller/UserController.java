package com.meongmory.meongmory.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

  @GetMapping("/test")
  public ResponseEntity<?> testController() {
    return ResponseEntity.ok("test");
  }
}
