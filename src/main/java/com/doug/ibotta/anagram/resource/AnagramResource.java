package com.doug.ibotta.anagram.resource;

import com.doug.ibotta.anagram.dto.Anagram;
import com.doug.ibotta.anagram.service.AnagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/anagrams")
public class AnagramResource {

    @Autowired
    AnagramService anagramService;

    @GetMapping("/{word}.json")
    public ResponseEntity getAnagramWordList(
            @PathVariable @NotNull  String word,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Boolean includeProperNouns
    )
    {
        Anagram anagrams = anagramService.getAnagramsForWord(word, limit, includeProperNouns);

        return ResponseEntity.ok(anagrams);
    }

    @DeleteMapping("/{word}")
    public ResponseEntity deleteWordAndAnagrams(
            @PathVariable String word
    )
    {
        anagramService.deleteWordAndAnagrams(word);

        return ResponseEntity.noContent().build();
    }
}
