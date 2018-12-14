package com.doug.ibotta;

import com.doug.ibotta.anagram.dto.Anagram;
import com.doug.ibotta.anagram.service.AnagramService;
import com.doug.ibotta.words.dao.Dictionary;
import com.doug.ibotta.words.util.WordsUtil;
import com.doug.ibotta.words.vo.Word;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class AnagramServiceTest {

    @Mock
    Dictionary dictionary;


    @InjectMocks
    AnagramService anagramService;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAnagramsForWordTest()
    {
        //given
        String word = "eat";
        String alphabeticalWord = "aet";
        Integer limit = 2;
        Boolean includeProperNouns = true;
        Word wordOne = WordsUtil.generateDictionaryWord("tea");
        Word wordTwo = WordsUtil.generateDictionaryWord("ate");
        List<Word> dictionaryList = new ArrayList<>();
        dictionaryList.add(wordOne);
        dictionaryList.add(wordTwo);
        when(dictionary.findByWordAlphabeticalAndWordNotIn(alphabeticalWord, word)).thenReturn(dictionaryList);

        //when
        Anagram anagram = anagramService.getAnagramsForWord(word, limit, includeProperNouns);

        //then
        assert anagram.anagrams.size() == dictionaryList.size();
        assert anagram.anagrams.contains(wordOne.getWord());
        assert anagram.anagrams.contains(wordTwo.getWord());
    }

    @Test
    public void getAnagramGroupsBySizeTest()
    {
        //given
        Integer size = 2;
        Word word = WordsUtil.generateDictionaryWord("tea");
        Word wordTwo = WordsUtil.generateDictionaryWord("ate");
        Word wordThree = WordsUtil.generateDictionaryWord("jar");
        List<Word> dictionaryList = new ArrayList<>();

        dictionaryList.add(wordThree);
        dictionaryList.add(word);
        dictionaryList.add(wordTwo);

        when(dictionary.findAll()).thenReturn(dictionaryList);

        //when
        Map<String, List<String>> anagramMap = anagramService.getAnagramGroupsBySize(size);

        //then
        assert anagramMap.get("aet").size() == 2;
        assert !anagramMap.containsKey("ajr");
    }

    @Test
    public void getAnagramsForWordWithoutProperNounsTest()
    {
        //given
        String word = "gab";
        String alphabeticalWord = "abg";
        Integer limit = 2;
        Boolean includeProperNouns = false;
        Word wordOne = WordsUtil.generateDictionaryWord("Bag");
        Word wordTwo = WordsUtil.generateDictionaryWord("bag");
        List<Word> dictionaryList = new ArrayList<>();
        dictionaryList.add(wordOne);
        dictionaryList.add(wordTwo);
        when(dictionary.findByWordAlphabeticalAndWordNotIn(alphabeticalWord, word)).thenReturn(dictionaryList);

        //when
        Anagram anagram = anagramService.getAnagramsForWord(word, limit, includeProperNouns);

        //then
        assert anagram.anagrams.size() == 1;
        assert !anagram.anagrams.contains(wordOne.getWord());
        assert anagram.anagrams.contains(wordTwo.getWord());
    }

    @Test
    public void getAnagramsWithLimit()
    {
        //given
        String word = "gab";
        String alphabeticalWord = "abg";
        Integer limit = 1;
        Boolean includeProperNouns = true;
        Word wordOne = WordsUtil.generateDictionaryWord("Bag");
        Word wordTwo = WordsUtil.generateDictionaryWord("bag");
        List<Word> dictionaryList = new ArrayList<>();
        dictionaryList.add(wordOne);
        dictionaryList.add(wordTwo);
        when(dictionary.findByWordAlphabeticalAndWordNotIn(alphabeticalWord, word)).thenReturn(dictionaryList);

        //when
        Anagram anagram = anagramService.getAnagramsForWord(word, limit, includeProperNouns);

        //then
        assert anagram.anagrams.size() == 1;
    }
}
