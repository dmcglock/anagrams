package com.doug.ibotta;

import com.doug.ibotta.words.dao.Dictionary;
import com.doug.ibotta.words.util.WordsUtil;
import com.doug.ibotta.words.vo.Word;
import org.assertj.core.util.IterableUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DictionaryTest {


    @Autowired
    Dictionary dictionary;

    @Test
    public void findByWordAlphabeticalAndWordNotInTest()
    {
        //given
        Word queriedWord = WordsUtil.generateDictionaryWord("read");
        Word expectedWord = WordsUtil.generateDictionaryWord("dear");
        dictionary.save(expectedWord);

        //when
        List<Word> wordsFound = dictionary.findByWordAlphabeticalAndWordNotIn(queriedWord.getWordAlphabetical(), queriedWord.getWord());

        //then
        assert wordsFound.size() == 1;
        assert wordsFound.get(0).getWord().equals("dear");
    }

    @Test
    public void deleteByWordTest()
    {
        //given
        Word queriedWord = WordsUtil.generateDictionaryWord("date");
        dictionary.save(queriedWord);

        //when
        dictionary.deleteByWord(queriedWord.getWord());
        List<Word> wordsFound = dictionary.findByWordAlphabeticalAndWordNotIn(queriedWord.getWordAlphabetical(), queriedWord.getWord());

        //then
        assert !wordsFound.contains(queriedWord);
    }

    @Test
    public void deleteByWordAlphabeticalTest()
    {
        //given
        Word anagramOne = WordsUtil.generateDictionaryWord("read");
        Word anagramTwo = WordsUtil.generateDictionaryWord("dear");
        dictionary.save(anagramOne);
        dictionary.save(anagramTwo);

        //when
        dictionary.deleteByWordAlphabetical(anagramOne.getWordAlphabetical());
        List<Word> wordsFound = dictionary.findByWordAlphabeticalAndWordNotIn(anagramOne.getWordAlphabetical(), anagramOne.getWord());

        //then
        assert wordsFound.size() == 0;
    }

    @Test
    public void deleteAllTest()
    {
        //given
        Word anagramOne = WordsUtil.generateDictionaryWord("random");
        dictionary.save(anagramOne);

        //when
        dictionary.deleteAll();
        Iterable<Word> anagrams = dictionary.findAll();

        //then
        assert IterableUtil.sizeOf(anagrams) == 0;
    }
}
