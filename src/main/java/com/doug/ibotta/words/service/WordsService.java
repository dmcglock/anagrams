package com.doug.ibotta.words.service;

import com.doug.ibotta.words.dao.Dictionary;
import com.doug.ibotta.words.util.WordsUtil;
import com.doug.ibotta.words.vo.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    private Word generateDictionaryWord(String word) {

    }

    public List<String> getAnagramsForWord(String word, Integer limit)
    {
        String sortedWord = WordsUtil.sortLettersOfWord(word);

        return new ArrayList<String>();
    }
}
