package com.doug.ibotta;

import com.doug.ibotta.words.dto.WordCountDto;
import com.doug.ibotta.words.util.WordCalculator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WordCalculatorTest {

    WordCalculator wordCalculator = new WordCalculator();

    @Test
    public void findWordStatisticsSimpleTest()
    {
        //given
        List<String> wordList = new ArrayList<>();
        wordList.add("read");
        wordList.add("dare");

        //when
        WordCountDto wordCountDto = wordCalculator.findWordStatistics(wordList);

        //then
        assert wordCountDto.getAverageWordLength() == 4;
        assert wordCountDto.getMinWordLength() == 4;
        assert wordCountDto.getMedianWordLength() == 4.0;
        assert wordCountDto.getWordCount() == 2;
        assert wordCountDto.getMaxWordLength() == 4;
    }

    @Test
    public void findWordStatistics()
    {
        //given
        List<String> wordList = new ArrayList<>();
        wordList.add("read");
        wordList.add("dare");
        wordList.add("alter");
        wordList.add("tears");

        //when
        WordCountDto wordCountDto = wordCalculator.findWordStatistics(wordList);

        //then
        assert wordCountDto.getAverageWordLength() == 4.5;
        assert wordCountDto.getMinWordLength() == 4;
        assert wordCountDto.getMedianWordLength() == 4.5;
        assert wordCountDto.getWordCount() == 4;
        assert wordCountDto.getMaxWordLength() == 5;
    }
}
