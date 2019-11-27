package com.options.clearing.corporation.namescoreapplication.web.model;

import com.options.clearing.corporation.namescoreapplication.web.interfaces.ScoreModelInterface;
import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.IOException;

@Getter
public class ErrorModel implements ScoreModelInterface {

    private String errorMessage;

    public void setErrorMessage(Exception e, String filePath) {
        if(e.getClass().equals(FileNotFoundException.class)) {
            this.errorMessage = "File not found for path " + filePath;
        } else if(e.getClass().equals(IOException.class)) {
            this.errorMessage = "Error parsing file at path " + filePath;
        } else {
            this.errorMessage = "General error for file at path " + filePath;
        }
    }
}
