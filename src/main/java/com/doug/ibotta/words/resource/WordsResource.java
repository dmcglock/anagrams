package com.doug.ibotta.words.resource;

import com.doug.ibotta.words.dto.WordCountDto;
import com.doug.ibotta.words.dto.WordsDto;
import com.doug.ibotta.words.service.WordsService;
import com.doug.ibotta.words.vo.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WordsResource {

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
        WordCountDto wordCountDto = wordsService.calculateWordStatistics(includeProperNouns);
        return ResponseEntity.ok(wordCountDto);
    }

}
