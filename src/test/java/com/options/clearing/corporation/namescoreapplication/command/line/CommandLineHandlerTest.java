package com.options.clearing.corporation.namescoreapplication.command.line;

import com.options.clearing.corporation.namescoreapplication.config.ApplicationConfig;
import com.options.clearing.corporation.namescoreapplication.handler.FileReaderHandler;
import com.options.clearing.corporation.namescoreapplication.handler.ScoringHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.reflect.Whitebox;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommandLineHandlerTest {

    @Mock
    private FileReaderHandler fileReaderHandler;

    @Mock
    private ScoringHandler scoringHandler;

    @Mock
    private ApplicationConfig applicationConfig;

    @InjectMocks
    private CommandLineHandler commandLineHandler;

    @Test
    @DisplayName("should handle file not found exception")
    void shouldCatchFileNotFoundException() throws Exception {
        doThrow(new FileNotFoundException()).when(fileReaderHandler).getListOfNamesFromFileInput(any());

        String input = "bad/file/path" + System.getProperty("line.separator") + "exit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Mockito.doNothing().when(applicationConfig).invokeProgramTermination();

        Whitebox.invokeMethod(commandLineHandler, "menu");

        verify(fileReaderHandler).getListOfNamesFromFileInput(any());
    }

    @Test
    @DisplayName("should handle IO exception")
    void shouldCatchIOException() throws Exception {
        doThrow(new IOException()).when(fileReaderHandler).getListOfNamesFromFileInput(any());

        String input = "unParsableFile.txt" + System.getProperty("line.separator") + "exit";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Whitebox.invokeMethod(commandLineHandler, "menu");

        verify(fileReaderHandler).getListOfNamesFromFileInput(any());
    }

    @Test
    @DisplayName("should handle general exceptions")
    void shouldHandleAnyOtherExceptions() throws Exception {
        doThrow(new RuntimeException()).when(fileReaderHandler).getListOfNamesFromFileInput(any());

        String input = "unParsableFile.txt" + System.getProperty("line.separator") + "exit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Mockito.doNothing().when(applicationConfig).invokeProgramTermination();

        Whitebox.invokeMethod(commandLineHandler, "menu");

        verify(fileReaderHandler).getListOfNamesFromFileInput(any());
    }

    @Test
    @DisplayName("should call scoring handler")
    void testPrintScoreMapForCallingScoringHandler() throws Exception {
        String [] sortedNames = new String [] {"name"};
        Map<String, Long> nameScoreMap = new HashMap<>();
        nameScoreMap.put("name", 1L);

        Whitebox.invokeMethod(commandLineHandler, "printScoreMap", sortedNames, nameScoreMap);

        verify(scoringHandler).getScoreOfEntireList(nameScoreMap);
    }

    @Test
    @DisplayName("should execute program correctly with no exceptions")
    void testForExecutingMenuMethodWithNoExceptions() throws Exception{
        String [] unsortedNames = new String [] {"name"};

        Map<String, Long> scoredNameMap = new HashMap<>();
        scoredNameMap.put("name", 1L);

        when(fileReaderHandler.getListOfNamesFromFileInput(any()))
                .thenReturn(unsortedNames);
        when(scoringHandler.getMapOfScoredNames(any()))
                .thenReturn(scoredNameMap);
        when(scoringHandler.getScoreOfEntireList(any())).thenReturn(1L);

        String input = "unParsableFile.txt" +
                System.getProperty("line.separator") + "N";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Mockito.doNothing().when(applicationConfig).invokeProgramTermination();

        commandLineHandler.handleInputFile();

        verify(scoringHandler).getScoreOfEntireList(any());
    }

}
