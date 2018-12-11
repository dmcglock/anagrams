package com.doug.ibotta.words.service;

import com.doug.ibotta.anagram.dto.Anagram;
import com.doug.ibotta.words.dao.Dictionary;
import com.doug.ibotta.words.util.WordsUtil;
import com.doug.ibotta.words.vo.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordsService {

    @Autowired
    Dictionary dictonary;

    public void addWordsToDictionary(List<String> words)
    {
        for(String word : words)
        {

            Word dictionaryWord = WordsUtil.generateDictionaryWord(word);
            dictonary.save(dictionaryWord);
        }
    }

//    private Word generateDictionaryWord(String word) {
//
//    }

    public Anagram getAnagramsForWord(String word, Integer limit, Boolean isProperNoun)
    {
        Anagram anagram = new Anagram();
        String alphabeticalWord = WordsUtil.sortLettersOfWord(word);
        List<Word> anagrams = dictonary.findByWordAlphabetical(alphabeticalWord);

        List<String> anagramWords = anagrams.stream()
                .map(Word::getWord)
                .filter(storedWord -> !storedWord.equals(word))
                .collect(Collectors.toList());

        anagram.setAnagrams(anagramWords);

        return anagram;
    }
}
