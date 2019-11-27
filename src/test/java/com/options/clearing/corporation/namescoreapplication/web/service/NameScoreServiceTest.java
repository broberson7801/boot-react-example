package com.options.clearing.corporation.namescoreapplication.web.service;

import com.options.clearing.corporation.namescoreapplication.handler.FileReaderHandler;
import com.options.clearing.corporation.namescoreapplication.handler.ScoringHandler;
import com.options.clearing.corporation.namescoreapplication.web.interfaces.ScoreModelInterface;
import com.options.clearing.corporation.namescoreapplication.web.model.ErrorModel;
import com.options.clearing.corporation.namescoreapplication.web.model.ScoreResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NameScoreServiceTest {

    @Mock
    private FileReaderHandler fileReaderHandler;

    @Mock
    private ScoringHandler scoringHandler;

    @InjectMocks
    private NameScoreService nameScoreService;

    @Test
    @DisplayName("should return score response model")
    void tstGetScoreFromTextFileForGettingScoreResponseModel() throws Exception {
        String [] unsortedNames = new String[] {"name"};

        Map<String, Long> scoredNameMap = new HashMap<>();
        scoredNameMap.put("name", 1L);

        when(fileReaderHandler.getListOfNamesFromFileInput(any())).thenReturn(unsortedNames);
        when(scoringHandler.getMapOfScoredNames(any())).thenReturn(scoredNameMap);
        when(scoringHandler.getScoreOfEntireList(any())).thenReturn(1L);

        ScoreModelInterface scoreResponseModel = nameScoreService.getScoreFromTextFile("file/path");

        assertEquals(ScoreResponseModel.class, scoreResponseModel.getClass());
    }

    @Test
    @DisplayName("should return error model")
    void tstGetScoreFromTextFileForGettingErrorModel() throws Exception {
        when(fileReaderHandler.getListOfNamesFromFileInput(any())).thenThrow(new IOException("error"));

        ScoreModelInterface scoreResponseModel = nameScoreService.getScoreFromTextFile("file/path");

        assertEquals(ErrorModel.class, scoreResponseModel.getClass());
    }
}
