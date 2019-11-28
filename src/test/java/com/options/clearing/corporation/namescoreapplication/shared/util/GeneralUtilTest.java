package com.options.clearing.corporation.namescoreapplication.shared.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeneralUtilTest {

    @Test
    @DisplayName("should return true if file ends with .txt")
    void testDoesFileHaveDotTxtExtensionForTrue() {
        String filePath = "/a/file/path/names.txt";

        assertTrue(GeneralUtil.doesFileHaveDotTxtExtension(filePath));
    }

    @Test
    @DisplayName("should remove quotes from name")
    void testGetNameFromFileWithoutQuotesForRemovingQuotes() {
        String name = "\"LINDA\"";
        String expected = "LINDA";

        String result = GeneralUtil.getNameFromFileWithoutQuotes(name);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("should return Linda in position 4 from expected input")
    void testGetSortedListForReturningLindaInCorrectPosition() {
        String expected = "LINDA";
        String [] input = new String [] {"MARY","PATRICIA","LINDA","BARBARA","VINCENZO","SHON","LYNWOOD","JERE","HAI"};

        String [] result = GeneralUtil.getSortedList(input);

        assertEquals(expected, result[3]);
    }
}
