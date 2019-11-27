package com.options.clearing.corporation.namescoreapplication.web.service;

import com.options.clearing.corporation.namescoreapplication.handler.FileReaderHandler;
import com.options.clearing.corporation.namescoreapplication.handler.ScoringHandler;
import com.options.clearing.corporation.namescoreapplication.util.GeneralUtil;
import com.options.clearing.corporation.namescoreapplication.web.interfaces.ScoreModelInterface;
import com.options.clearing.corporation.namescoreapplication.web.model.ErrorModel;
import com.options.clearing.corporation.namescoreapplication.web.model.ScoreResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Profile("web")
public class NameScoreService {

    @Autowired
    private FileReaderHandler fileReaderHandler;

    @Autowired
    private ScoringHandler scoringHandler;

    public ScoreModelInterface getScoreFromTextFile(String filePath)  {
        try {
            String[] unsortedListOfNames = fileReaderHandler.getListOfNamesFromFileInput(filePath);
            String [] sortedNames = GeneralUtil.getSortedList(unsortedListOfNames);

            Map<String, Long> scoredNameMap = scoringHandler.getMapOfScoredNames(sortedNames);

            return getScoreResponseModel(scoredNameMap, sortedNames);

        } catch (Exception e) {
            return getErrorModel(e, filePath);
        }
    }

    private ScoreResponseModel getScoreResponseModel(Map<String, Long> scoredNameMap, String [] sortedNames) {
        ScoreResponseModel scoreResponseModel = new ScoreResponseModel();
        scoreResponseModel.setScoreMap(scoredNameMap);
        scoreResponseModel.setSortedNames(sortedNames);
        scoreResponseModel.setListTotalScore(scoringHandler.getScoreOfEntireList(scoredNameMap));

        return scoreResponseModel;
    }

    private ErrorModel getErrorModel(Exception e, String filePath) {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setErrorMessage(e, filePath);

        return errorModel;
    }
}
