package com.doug.ibotta.words.service;

import com.doug.ibotta.words.dao.Dictionary;
import com.doug.ibotta.words.dto.WordCountDto;
import com.doug.ibotta.words.util.WordCalculator;
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

    @Autowired
    WordCalculator wordCalculator;

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

    public WordCountDto calculateWordStatistics(Boolean includeProperNouns)
    {
        List<String> allWords;
        if(includeProperNouns != null && includeProperNouns)
        {
            allWords = dictonary.selectAllProperNouns();
        }
        else{
            allWords = dictonary.selectAllWords();
        }

       return wordCalculator.findWordStatistics(allWords);
    }
}
