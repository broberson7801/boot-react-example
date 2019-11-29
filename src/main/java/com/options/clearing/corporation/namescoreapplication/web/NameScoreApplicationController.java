package com.options.clearing.corporation.namescoreapplication.web;

import com.options.clearing.corporation.namescoreapplication.web.interfaces.ScoreModelInterface;
import com.options.clearing.corporation.namescoreapplication.web.model.ScoreRequest;
import com.options.clearing.corporation.namescoreapplication.web.model.ScoreResponseModel;
import com.options.clearing.corporation.namescoreapplication.web.service.NameScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Profile("web")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class NameScoreApplicationController {

    @Autowired
    private NameScoreService nameScoreService;

    @GetMapping("/server/up")
    public ResponseEntity<String> getServerStatus() {
        return new ResponseEntity<>("Spring Boot server is running", HttpStatus.OK);
    }

    @PostMapping("/score")
    public ResponseEntity<ScoreModelInterface> getScoreResponseModel(@RequestBody ScoreRequest scoreRequest) {
        log.info("NameScoreApplicationController.getScoreResponseModel() : received {} ",scoreRequest.toString());

        ScoreModelInterface serviceResponse = nameScoreService.getScoreFromTextFile(scoreRequest.getFileToScore());

        return serviceResponse.getClass().equals(ScoreResponseModel.class) ?
                new ResponseEntity<>(serviceResponse, HttpStatus.OK) :
                new ResponseEntity<>(serviceResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
