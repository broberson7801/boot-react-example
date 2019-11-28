package com.options.clearing.corporation.namescoreapplication.shared.handler;

import com.options.clearing.corporation.namescoreapplication.command.line.CommandLineHandler;
import com.options.clearing.corporation.namescoreapplication.shared.util.GeneralUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ScoringHandlerTest {

    @MockBean
    private CommandLineHandler commandLineHandler;

    @Autowired
    private ScoringHandler scoringHandler;

    @Test
    @DisplayName("should correctly score one name")
    void testScoreNameForCorrectScore() {
        String name = "LINDA";
        long expected = 40L;

        long result = scoringHandler.getTheBaseScoreForASingleName(name);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("should return 160 for Linda in position 4")
    void testGetMapOfScoredNamesForCorrectValue() {
        String [] input = new String [] {"MARY","PATRICIA","LINDA","BARBARA","VINCENZO","SHON","LYNWOOD","JERE","HAI"};

        String [] sortedList = GeneralUtil.getSortedList(input);

        Map<String, Long> result = scoringHandler.getMapOfScoredNames(sortedList);

        assertEquals(result.get("LINDA"), 160L);
    }

    @Test
    @DisplayName("should return 3194 for sample list")
    void testGetScoreOfEntireListForSampleList() {
        String [] input = new String [] {"MARY","PATRICIA","LINDA","BARBARA","VINCENZO","SHON","LYNWOOD","JERE","HAI"};

        String [] sortedList = GeneralUtil.getSortedList(input);

        Map<String, Long> nameMap = scoringHandler.getMapOfScoredNames(sortedList);

        long expected = 3194;

        long result = scoringHandler.getScoreOfEntireList(nameMap);

        assertEquals(expected, result);
    }
}
