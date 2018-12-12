package com.doug.ibotta.anagram.resource;

import com.doug.ibotta.anagram.dto.Anagram;
import com.doug.ibotta.anagram.service.AnagramService;
import com.doug.ibotta.words.vo.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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

    @GetMapping("/mostCommon")
    public ResponseEntity getMostCommonAnagram()
    {
        List<Word> wordsWithMostCommonAnagram = anagramService.getMostCommonAnagram();

        return ResponseEntity.ok(wordsWithMostCommonAnagram);
    }
}
