package com.options.clearing.corporation.namescoreapplication.handler;

import com.options.clearing.corporation.namescoreapplication.command.line.CommandLineHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FileReaderHandlerTest {

    @Autowired
    private FileReaderHandler fileReaderHandler;

    @MockBean
    private CommandLineHandler commandLineHandler;

    @Test
    @DisplayName("should return a map of three names from the format of the expected input")
    void testReadFileForReturningThreeNames() throws Exception {
        String filePath = ".\\src\\test\\resources\\OCC_Three_Names.txt";

        String [] result = fileReaderHandler.getListOfNamesFromFileInput(filePath);
        assertAll("result",
                () -> assertEquals("MARY", result[0]),
                () -> assertEquals("PATRICIA", result[1]),
                () -> assertEquals("LINDA", result[2])
                );
    }

}
