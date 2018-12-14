package com.doug.ibotta.anagram.resource;

import com.doug.ibotta.anagram.dto.Anagram;
import com.doug.ibotta.anagram.dto.AnagramCheckerDto;
import com.doug.ibotta.anagram.service.AnagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Map;

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
            @RequestParam List<String> words
    )
    {
        AnagramCheckerDto anagramCheckerDto = anagramService.checkIfWordsAreAnagrams(words);

        return ResponseEntity.ok(anagramCheckerDto);
    }

    @GetMapping("/groups")
    public ResponseEntity getAnagramGroupsBySize(
            @RequestParam Integer size
    )
    {
        Map<String, List<String>> anagramMap = anagramService.getAnagramGroupsBySize(size);

        return ResponseEntity.ok(anagramMap);
    }

}
