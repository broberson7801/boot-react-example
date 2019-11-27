package com.options.clearing.corporation.namescoreapplication.web;

import com.options.clearing.corporation.namescoreapplication.web.interfaces.ScoreModelInterface;
import com.options.clearing.corporation.namescoreapplication.web.model.ErrorModel;
import com.options.clearing.corporation.namescoreapplication.web.model.ScoreResponseModel;
import com.options.clearing.corporation.namescoreapplication.web.service.NameScoreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NameScoreApplicationControllerTest {

    @Mock
    private NameScoreService nameScoreService;

    @InjectMocks
    private NameScoreApplicationController nameScoreApplicationController;

    @Test
    @DisplayName("should return 200 okay when server is up")
    void testGetServerStatusForStatusCode200() {
        ResponseEntity<String> response = nameScoreApplicationController.getServerStatus();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("should return http status 200 and score response model")
    void testGetScoreResponseModelFor200StatusCode() {
        ScoreModelInterface serviceResponse = new ScoreResponseModel();
        when(nameScoreService.getScoreFromTextFile(any())).thenReturn(serviceResponse);

        ResponseEntity<ScoreModelInterface> response = nameScoreApplicationController
                .getScoreResponseModel("path/to/file");

        assertAll("result",
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(ScoreResponseModel.class, response.getBody().getClass()));
    }

    @Test
    @DisplayName("should return http status 500 and error model")
    void testGetScoreResponseModelFor500StatusCode() {
        ScoreModelInterface serviceResponse = new ErrorModel();
        when(nameScoreService.getScoreFromTextFile(any())).thenReturn(serviceResponse);

        ResponseEntity<ScoreModelInterface> response = nameScoreApplicationController
                .getScoreResponseModel("path/to/file");

        assertAll("result",
                () -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()),
                () -> assertEquals(ErrorModel.class, response.getBody().getClass()));
    }
}
