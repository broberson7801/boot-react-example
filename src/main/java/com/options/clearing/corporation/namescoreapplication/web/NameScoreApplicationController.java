package com.options.clearing.corporation.namescoreapplication.web;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("web")
public class NameScoreApplicationController {

    @GetMapping("/server/up")
    public ResponseEntity<String> getServerStatus() {
        return new ResponseEntity<>("Spring Boot server is running", HttpStatus.OK);
    }
}
