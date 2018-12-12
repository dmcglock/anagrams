package com.doug.ibotta.words.service;

import com.doug.ibotta.words.dao.Dictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DictionaryService {

    Logger logger = LoggerFactory.getLogger(DictionaryService.class);

    @Autowired
    WordsService wordsService;

    @Autowired
    Dictionary dictionary;

    private static final String PATH_TO_DICTIONARY_WORDS = "src/main/resources/dictionary.txt";

    public void addPredefinedDictionary()
    {
        try(Stream<String> stream = Files.lines(Paths.get(PATH_TO_DICTIONARY_WORDS)))
        {
            List<String> dictionaryWords = stream.collect(Collectors.toList());
            wordsService.addWordsToDictionary(dictionaryWords);

            logger.info("Dictionary words processed: {} out of {}", dictionary.count(), dictionaryWords.size());
        } catch (IOException e) {
            logger.error("Trouble uploading dictionary: {}", e);
        }
    }
}
