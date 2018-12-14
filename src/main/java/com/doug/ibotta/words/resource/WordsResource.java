package com.doug.ibotta.words.resource;

import com.doug.ibotta.words.dto.WordCountDto;
import com.doug.ibotta.words.dto.WordsDto;
import com.doug.ibotta.words.exception.EmptyDictionaryException;
import com.doug.ibotta.words.service.DictionaryService;
import com.doug.ibotta.words.service.WordsService;
import com.doug.ibotta.words.vo.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WordsResource {

    Logger logger = LoggerFactory.getLogger(WordsResource.class);

    @Autowired
    WordsService wordsService;

    @PostMapping(value = "/words.json")
    public ResponseEntity addWordsToDictionary(
            @RequestBody WordsDto words)
    {
        List<Word> addedWords = wordsService.addWordsToDictionary(words.getWords());
        return ResponseEntity.status(HttpStatus.CREATED).body(addedWords);
    }

    @DeleteMapping("/words/{word}.json")
    public ResponseEntity deleteWordFromDictionary(
            @PathVariable String word
    )
    {
        wordsService.deleteWordFromDictionary(word);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/words.json")
    public ResponseEntity deleteAllWordsFromDictionary()
    {
        wordsService.deleteAllWordsFromDictionary();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count-words")
    public ResponseEntity getCountOfWords(
            @RequestParam(required = false) Boolean includeProperNouns
    )
    {
        WordCountDto wordCountDto = null;
        try {
            wordCountDto = wordsService.calculateWordStatistics(includeProperNouns);
            return ResponseEntity.ok(wordCountDto);
        } catch (EmptyDictionaryException e) {
            logger.warn("Dictionary is empty when trying to get statistics");
            return ResponseEntity.ok(new WordCountDto());
        }
    }

}
