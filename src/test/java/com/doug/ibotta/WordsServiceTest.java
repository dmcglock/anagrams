package com.doug.ibotta;


import com.doug.ibotta.words.dao.Dictionary;
import com.doug.ibotta.words.service.WordsService;
import com.doug.ibotta.words.util.WordsUtil;
import com.doug.ibotta.words.vo.Word;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class WordsServiceTest {

    @Mock
    Dictionary dictionary;

    @InjectMocks
    WordsService wordsService;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addWordsToDictionaryTest()
    {
        //given
        List<String> wordList = new ArrayList<>();
        wordList.add("read");
        wordList.add("dare");
        Word wordOne = WordsUtil.generateDictionaryWord("read");
        Word wordTwo = WordsUtil.generateDictionaryWord("dare");

        //when
        when(dictionary.save(wordOne)).thenReturn(wordOne);
        when(dictionary.save(wordTwo)).thenReturn(wordTwo);
        List<Word> resultList = wordsService.addWordsToDictionary(wordList);

        //then
        assert resultList.size() == wordList.size();
    }
}
