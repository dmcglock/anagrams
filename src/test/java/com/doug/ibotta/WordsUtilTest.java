package com.doug.ibotta;


import com.doug.ibotta.words.util.WordsUtil;
import com.doug.ibotta.words.vo.Word;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

public class WordsUtilTest {

    @Test
    public void sortLettersOfWordTest()
    {
        //given
        String word = "read";

        //when
        String sortedWord = WordsUtil.sortLettersOfWord(word);
        String expectedWord = "ader";

        //then
        assert sortedWord.equals(expectedWord);

    }

    @Test
    public void longWordSortLettersTest()
    {
        //given
        String longestWord = "pneumonoultramicroscopicsilicovolcanoconiosis";

        //when
        String sortedWord = WordsUtil.sortLettersOfWord(longestWord);
        String expectedWord = "aacccccceiiiiiilllmmnnnnooooooooopprrsssstuuv";

        //then
        assert expectedWord.equals(sortedWord);
    }

    @Test
    public void generateWordTest()
    {
        //given
        String word = "Doug";

        //when
        Word result = WordsUtil.generateDictionaryWord(word);
        String expectedAlphabeticalWord = "dgou";
        Boolean expectedProperNounResult = true;
        //then
        assert result.getWord().equals(word);
        assert result.getWordAlphabetical().equals(expectedAlphabeticalWord);
        assert result.isProperNoun() == expectedProperNounResult;
    }
}
