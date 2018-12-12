package com.doug.ibotta.anagram.resource;

import com.doug.ibotta.anagram.dto.Anagram;
import com.doug.ibotta.anagram.dto.AnagramCheckerDto;
import com.doug.ibotta.anagram.service.AnagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anagrams")
public class AnagramResource {

    @Autowired
    AnagramService anagramService;

    @GetMapping("/{word}.json")
    public ResponseEntity getAnagramWordList(
            @PathVariable String word,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Boolean includeProperNouns
    )
    {
        String wordWithoutSpaces = word.replaceAll("\\s+","");
        if(wordWithoutSpaces.equals(""))
        {
            return ResponseEntity.badRequest().build();
        }

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

    @GetMapping("/check")
    public ResponseEntity getAnagramsCheck(
            @RequestParam String wordOne,
            @RequestParam String wordTwo
    )
    {
        AnagramCheckerDto anagramCheckerDto = anagramService.checkIfWordsAreAnagrams(wordOne, wordTwo);

        return ResponseEntity.ok(anagramCheckerDto);
    }
}
