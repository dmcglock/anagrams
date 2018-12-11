package com.doug.ibotta.words.resource;

import com.doug.ibotta.words.dto.WordsDto;
import com.doug.ibotta.words.service.WordsService;
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
            @RequestBody List<String> words)
    {
        wordsService.addWordsToDictionary(words);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
