package com.doug.ibotta.words.util;

import com.doug.ibotta.words.dto.WordCountDto;
import com.doug.ibotta.words.vo.Word;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
public class WordCalculator {

    public WordCountDto findWordMetrics(List<String> allWords)
    {
        WordCountDto wordCountDto = new WordCountDto();
        List<Integer> sortedWordLengths =
                allWords.stream()
                        .map(String::length)
                        .sorted()
                        .collect(Collectors.toList());

        IntSummaryStatistics statistics = sortedWordLengths.stream().mapToInt((x) -> x).summaryStatistics();

        wordCountDto.setWordCount(statistics.getCount());
        wordCountDto.setAverageWordLength(statistics.getAverage());
        wordCountDto.setMaxWordLength(statistics.getMax());
        wordCountDto.setMinWordLength(statistics.getMin());
        wordCountDto.setMedianWordLength(getMedianWordLength(sortedWordLengths));

        return wordCountDto;
    }

    private Double getMedianWordLength(List<Integer> allWords) {
        return (allWords.get(allWords.size()/2).doubleValue() + allWords.get(allWords.size()/2 - 1).doubleValue())/2;
    }
}
