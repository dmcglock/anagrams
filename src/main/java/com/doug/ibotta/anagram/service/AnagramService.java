package com.doug.ibotta.anagram.service;

import com.doug.ibotta.anagram.dto.Anagram;
import com.doug.ibotta.anagram.dto.AnagramCheckerDto;
import com.doug.ibotta.words.dao.Dictionary;
import com.doug.ibotta.words.util.WordsUtil;
import com.doug.ibotta.words.vo.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnagramService {

    @Autowired
    Dictionary dictionary;

    public Anagram getAnagramsForWord(String word, Integer limit, Boolean includeProperNouns)
    {
        Anagram anagram = new Anagram();
        String alphabeticalWord = WordsUtil.sortLettersOfWord(word);
        List<Word> anagrams = dictionary.findByWordAlphabeticalAndWordNotIn(alphabeticalWord, word);
        List<String> anagramWords;

        if(includeProperNouns != null && includeProperNouns)
        {
            anagramWords = getAnagramsIncludeProperNouns(anagrams, limit);
        }
        else
        {
            anagramWords = getAnagramsWithoutProperNouns(anagrams, limit);
        }

        anagram.setAnagrams(anagramWords);

        return anagram;
    }

    public void deleteWordAndAnagrams(String word)
    {
        String alphabeticalWord = WordsUtil.sortLettersOfWord(word);
        dictionary.deleteByWordAlphabetical(alphabeticalWord);
    }


    private List<String> getAnagramsWithoutProperNouns(List<Word> anagramWords, Integer limit)
    {
        return  anagramWords.stream()
                .filter(word-> !word.isProperNoun())
                .limit(limit == null ? anagramWords.size() : limit)
                .map(Word::getWord)
                .collect(Collectors.toList());
    }

    private List<String> getAnagramsIncludeProperNouns(List<Word> anagramWords, Integer limit)
    {
        return anagramWords.stream()
                .limit(limit == null ? anagramWords.size() : limit)
                .map(Word::getWord)
                .collect(Collectors.toList());
    }

    public AnagramCheckerDto checkIfWordsAreAnagrams(List<String> words)
    {

        String sortedWordOne = WordsUtil.sortLettersOfWord(words.get(0));
        String sortedWordTwo = WordsUtil.sortLettersOfWord(words.get(1));

        Boolean areAnagrams = sortedWordOne.equals(sortedWordTwo);

        return new AnagramCheckerDto(words, areAnagrams);
    }

    public Map<String, List<String>> getAnagramGroupsBySize(Integer size)
    {
        Iterable<Word> allWords = dictionary.findAll();
        Map<String, List<String>> anagramMap = new HashMap<>();

        for(Word word : allWords)
        {
            if(!anagramMap.containsKey(word.getWordAlphabetical()))
            {
                anagramMap.put(word.getWordAlphabetical(), new ArrayList<>());
            }
            anagramMap.get(word.getWordAlphabetical()).add(word.getWord());
        }

        anagramMap = anagramMap.entrySet().stream()
                .filter(anagramEntry -> anagramEntry.getValue().size() >= size)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return anagramMap;
    }


}
