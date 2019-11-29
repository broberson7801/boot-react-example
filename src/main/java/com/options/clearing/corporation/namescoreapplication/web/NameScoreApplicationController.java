package com.options.clearing.corporation.namescoreapplication.web;

import com.options.clearing.corporation.namescoreapplication.web.interfaces.ScoreModelInterface;
import com.options.clearing.corporation.namescoreapplication.web.model.ScoreResponseModel;
import com.options.clearing.corporation.namescoreapplication.web.service.NameScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Profile("web")
@CrossOrigin("http://localhost:3000")
public class NameScoreApplicationController {

    @Autowired
    private NameScoreService nameScoreService;

    @GetMapping("/server/up")
    public ResponseEntity<String> getServerStatus() {
        return new ResponseEntity<>("Spring Boot server is running", HttpStatus.OK);
    }

    @PostMapping("/score/{fileToScore}")
    public ResponseEntity<ScoreModelInterface> getScoreResponseModel(@PathVariable String fileToScore) {
        ScoreModelInterface serviceResponse = nameScoreService.getScoreFromTextFile(fileToScore);

        return serviceResponse.getClass().equals(ScoreResponseModel.class) ?
                new ResponseEntity<>(serviceResponse, HttpStatus.OK) :
                new ResponseEntity<>(serviceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
