package com.doug.ibotta.anagram.resource;

import com.doug.ibotta.words.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/anagram")
public class AnagramResource {

    @Autowired
    WordsService wordsService;

    @GetMapping("/{word}")
    public ResponseEntity getAnagramWordList(
            @PathVariable @NotNull  String word,
            @RequestParam(required = false) Integer limit
    )
    {
        List<String> anagramsForWord = wordsService.getAnagramsForWord(word, limit);

        return ResponseEntity.ok(anagramsForWord);
    }
}
