package com.doug.ibotta.anagram.resource;

import com.doug.ibotta.anagram.dto.Anagram;
import com.doug.ibotta.words.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/anagrams")
public class AnagramResource {

    @Autowired
    WordsService wordsService;

    @GetMapping("/{word}")
    public ResponseEntity getAnagramWordList(
            @PathVariable @NotNull  String word,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Boolean includeProperNouns
    )
    {
        Anagram anagrams = wordsService.getAnagramsForWord(word, limit, includeProperNouns);

        return ResponseEntity.ok(anagrams);
    }
}
