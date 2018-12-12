package com.doug.ibotta.anagram.service;

import com.doug.ibotta.anagram.dto.Anagram;
import com.doug.ibotta.anagram.dto.AnagramCheckerDto;
import com.doug.ibotta.words.dao.Dictionary;
import com.doug.ibotta.words.util.WordsUtil;
import com.doug.ibotta.words.vo.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnagramService {

    @Autowired
    Dictionary dictionary;

    public Anagram getAnagramsForWord(String word, Integer limit, Boolean includeProperNouns)
    {
        Anagram anagram = new Anagram();
        String alphabeticalWord = WordsUtil.sortLettersOfWord(word);
        List<Word> anagrams = dictionary.findByWordAlphabeticalAndWordNotIn(alphabeticalWord, word);
        List<String> anagramWords;

        if(includeProperNouns != null && includeProperNouns)
        {
            anagramWords = getProperNounAnagrams(anagrams, limit);
        }
        else
        {
            anagramWords = anagrams.stream()
                    .limit(limit == null ? anagrams.size() : limit)
                    .map(Word::getWord)
                    .collect(Collectors.toList());
        }

        anagram.setAnagrams(anagramWords);

        return anagram;
    }

    public void deleteWordAndAnagrams(String word)
    {
        String alphabeticalWord = WordsUtil.sortLettersOfWord(word);
        dictionary.deleteByWordAlphabetical(alphabeticalWord);
    }


    private List<String> getProperNounAnagrams(List<Word> anagramWords, Integer limit)
    {
        return  anagramWords.stream()
                .filter(Word::isProperNoun)
                .limit(limit == null ? anagramWords.size() : limit)
                .map(Word::getWord)
                .collect(Collectors.toList());
    }

    public AnagramCheckerDto checkIfWordsAreAnagrams(String wordOne, String wordTwo)
    {
        List<String> words = new ArrayList<String>();
        words.add(wordOne);
        words.add(wordTwo);

        String sortedWordOne = WordsUtil.sortLettersOfWord(wordOne);
        String sortedWordTwo = WordsUtil.sortLettersOfWord(wordTwo);

        Boolean areAnagrams = sortedWordOne.equals(sortedWordTwo);

        return new AnagramCheckerDto(words, areAnagrams);
    }


}
