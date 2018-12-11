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

    public void deleteWordFromDictionary(String word)
    {
        dictonary.deleteByWord(word);
    }

    public void deleteAllWordsFromDictionary()
    {
        dictonary.deleteAll();
    }
}
