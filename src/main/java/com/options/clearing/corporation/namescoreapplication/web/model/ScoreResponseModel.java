package com.options.clearing.corporation.namescoreapplication.web.model;

import com.options.clearing.corporation.namescoreapplication.web.interfaces.ScoreModelInterface;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class ScoreResponseModel implements ScoreModelInterface {

    private Map<String, Long> scoreMap;

    private String[] sortedNames;

    private Long listTotalScore;

}