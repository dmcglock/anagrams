package com.doug.ibotta.anagram.service;

import com.doug.ibotta.anagram.dto.Anagram;
import com.doug.ibotta.words.dao.Dictionary;
import com.doug.ibotta.words.util.WordsUtil;
import com.doug.ibotta.words.vo.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnagramService {

    @Autowired
    Dictionary dictionary;

    public Anagram getAnagramsForWord(String word, Integer limit, Boolean isProperNoun)
    {
        Anagram anagram = new Anagram();
        String alphabeticalWord = WordsUtil.sortLettersOfWord(word);
        List<Word> anagrams = dictionary.findByWordAlphabetical(alphabeticalWord);

        List<String> anagramWords = anagrams.stream()
                .map(Word::getWord)
                .filter(storedWord -> !storedWord.equals(word))
                .collect(Collectors.toList());

        anagram.setAnagrams(anagramWords);

        return anagram;
    }

    
}
