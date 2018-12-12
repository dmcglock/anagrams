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

    public List<Word> addWordsToDictionary(List<String> words)
    {
        List<Word> savedWords = new ArrayList<>();
        for(String word : words)
        {

            Word dictionaryWord = WordsUtil.generateDictionaryWord(word);
            Word savedWord = dictonary.save(dictionaryWord);
            savedWords.add(savedWord);
        }

        return savedWords;
    }

    public void deleteWordFromDictionary(String word)
    {
        dictonary.deleteByWord(word);
    }

    public void deleteAllWordsFromDictionary()
    {
        dictonary.deleteAll();
    }
}
