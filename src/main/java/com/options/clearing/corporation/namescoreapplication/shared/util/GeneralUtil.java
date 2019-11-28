package com.options.clearing.corporation.namescoreapplication.shared.util;

import java.util.Arrays;

public class GeneralUtil {

    public static boolean doesFileHaveDotTxtExtension(String filPath) {
        return filPath.substring(filPath.lastIndexOf(".")).trim().equalsIgnoreCase(".txt");
    }

    public static String getNameFromFileWithoutQuotes(String name) {
        return name.replace("\"", "");
    }

    public static String [] getSortedList(String [] listOfUnsortedNames) {
        Arrays.sort(listOfUnsortedNames);
        return listOfUnsortedNames;
    }

    public static String [] getArrayWithoutQuotesOnNames(String [] input) {
        String [] result = new String [input.length];

        for(int i = 0; i < input.length; i++) {
            result[i] = GeneralUtil.getNameFromFileWithoutQuotes(input[i]);
        }
        return result;
    }
}
