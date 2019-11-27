package com.options.clearing.corporation.namescoreapplication.handler;

import com.options.clearing.corporation.namescoreapplication.config.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ScoringHandler  {

    @Autowired
    private ApplicationConfig applicationConfig;

    public long getTheBaseScoreForASingleName(String name) {
        char [] chars = name.toCharArray();
        long result = 0L;

        for(char character: chars) {
            result += applicationConfig.getScoreMap().get(character);
        }

        return result;
    }

    public Map<String, Long> getMapOfScoredNames(String [] sortedNames) {
        Map<String, Long> result = new HashMap<>(sortedNames.length, 1);

        for(int i = 0; i < sortedNames.length; i ++) {
            result.put(sortedNames[i], (i + 1) * getTheBaseScoreForASingleName(sortedNames[i]));
        }

        return result;
    }

    public long getScoreOfEntireList(Map<String, Long> nameScoreMap) {
        long result = 0;

        for(Map.Entry<String, Long> entry : nameScoreMap.entrySet()) {
            result += entry.getValue();
        }

        return result;
    }
}
