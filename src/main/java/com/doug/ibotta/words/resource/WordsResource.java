package com.doug.ibotta.words.resource;

import com.doug.ibotta.words.dto.WordsDto;
import com.doug.ibotta.words.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WordsResource {

    @Autowired
    WordsService wordsService;

    @PostMapping(value = "/words.json")
    public ResponseEntity addWordsToDictionary(
            @RequestBody WordsDto words)
    {
        wordsService.addWordsToDictionary(words.getWords());
        return ResponseEntity.status(HttpStatus.CREATED).build();
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

}
