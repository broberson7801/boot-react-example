package com.options.clearing.corporation.namescoreapplication.handler;

import com.options.clearing.corporation.namescoreapplication.util.Constants;
import com.options.clearing.corporation.namescoreapplication.util.GeneralUtil;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
public class FileReaderHandler {

    //this component is called by both command line and web components;
    //the below throws an exception as the command line and web components have to handle the checked IO
    //exception differently
    public String [] getListOfNamesFromFileInput(String filePath) throws Exception {

        BufferedReader bufferedReader;
        String line;
        String [] names = null;

        bufferedReader = new BufferedReader(new FileReader(filePath));

        while ((line = bufferedReader.readLine()) != null) {
            names = GeneralUtil.getArrayWithoutQuotesOnNames(line.split(Constants.COMMA_SEPARATOR));
        }

        bufferedReader.close();

        return names;
    }
}
