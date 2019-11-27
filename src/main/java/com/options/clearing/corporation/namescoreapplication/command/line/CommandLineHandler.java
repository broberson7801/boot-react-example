package com.options.clearing.corporation.namescoreapplication.command.line;

import com.options.clearing.corporation.namescoreapplication.config.ApplicationConfig;
import com.options.clearing.corporation.namescoreapplication.handler.FileReaderHandler;
import com.options.clearing.corporation.namescoreapplication.handler.ScoringHandler;
import com.options.clearing.corporation.namescoreapplication.util.Constants;
import com.options.clearing.corporation.namescoreapplication.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

@Component
@Profile("commandline")
public class CommandLineHandler {

    @Autowired
    private FileReaderHandler fileReaderHandler;

    @Autowired
    private ScoringHandler scoringHandler;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Bean
    public void handleInputFile() {
        menu();
    }

   private void menu() {

       String filePath = null;
       Scanner input = new Scanner(System.in);

       try {

           System.out.println("Enter full path to a .txt file to score: ");
           filePath = input.nextLine();

          String [] unsortedListOfNames = fileReaderHandler.getListOfNamesFromFileInput(filePath);
          String [] sortedNames = GeneralUtil.getSortedList(unsortedListOfNames);

          Map<String, Long> scoredNameMap = scoringHandler.getMapOfScoredNames(sortedNames);
          printScoreMap(sortedNames, scoredNameMap);

       } catch (Exception e) {
           handleCommandLineExceptions(e, filePath);
       } finally {
          handleFinallyBlock(input);
       }
   }

   private void printScoreMap(String [] sortedNames, Map<String, Long> nameScoreMap) {
        for(String name: sortedNames) {
            System.out.println(name + " has a score of " + nameScoreMap.get(name));
        }
       System.out.println("The entire list has a score of " + scoringHandler.getScoreOfEntireList(nameScoreMap));
   }

   private boolean doInvokeMenuMethod(String input) {
        return input.equalsIgnoreCase(Constants.LETTER_Y);
   }

   private void handleCommandLineExceptions(Exception e, String filePath) {
       if(e.getClass().equals(FileNotFoundException.class)) {
           System.out.println("File not found for path " + filePath);
       } else if(e.getClass().equals(IOException.class)) {
           System.out.println("Error parsing file at path " + filePath);
       } else {
           System.out.println("General error for file at path " + filePath);
       }
   }

   private void handleFinallyBlock(Scanner input) {
       System.out.println("Do go again? Enter Y for yes, another key for program exit");
       if(doInvokeMenuMethod(input.next())) {
           menu();
       } else {
           System.out.println("Exiting program");
           applicationConfig.invokeProgramTermination();
       }
   }
}
