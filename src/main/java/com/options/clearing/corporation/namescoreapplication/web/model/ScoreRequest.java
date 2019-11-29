package com.options.clearing.corporation.namescoreapplication.web.model;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreRequest {

    private String fileToScore;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
