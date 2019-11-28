package com.options.clearing.corporation.namescoreapplication.shared.config;

import com.options.clearing.corporation.namescoreapplication.command.line.CommandLineHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicationConfigTest {

    @Autowired
    private ApplicationConfig applicationConfig;

    @MockBean
    private CommandLineHandler commandLineHandler;

    @Test
    @DisplayName("should return correct numeric values for all 26 letters in alphabet")
    void testPropertyMapForCorrectValues() {

        Map<Character, Integer> result = applicationConfig.getScoreMap();

        assertAll("result",
                () -> assertEquals(1, result.get('A')),
                () -> assertEquals(2, result.get('B')),
                () -> assertEquals(3, result.get('C')),
                () -> assertEquals(4, result.get('D')),
                () -> assertEquals(5, result.get('E')),
                () -> assertEquals(6, result.get('F')),
                () -> assertEquals(7, result.get('G')),
                () -> assertEquals(8, result.get('H')),
                () -> assertEquals(9, result.get('I')),
                () -> assertEquals(10, result.get('J')),
                () -> assertEquals(11, result.get('K')),
                () -> assertEquals(12, result.get('L')),
                () -> assertEquals(13, result.get('M')),
                () -> assertEquals(14, result.get('N')),
                () -> assertEquals(15, result.get('O')),
                () -> assertEquals(16, result.get('P')),
                () -> assertEquals(17, result.get('Q')),
                () -> assertEquals(18, result.get('R')),
                () -> assertEquals(19, result.get('S')),
                () -> assertEquals(20, result.get('T')),
                () -> assertEquals(21, result.get('U')),
                () -> assertEquals(22, result.get('V')),
                () -> assertEquals(23, result.get('W')),
                () -> assertEquals(24, result.get('X')),
                () -> assertEquals(25, result.get('Y')),
                () -> assertEquals(26, result.get('Z'))
                );

    }
}
