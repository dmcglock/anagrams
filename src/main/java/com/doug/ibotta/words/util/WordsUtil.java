package com.doug.ibotta.words.util;


import com.doug.ibotta.words.vo.Word;

import java.util.Arrays;

public class WordsUtil {

    public static String sortLettersOfWord(String word)
    {
        char[] wordChars = word.toCharArray();
        Arrays.sort(wordChars);

        return new String(wordChars).toLowerCase();
    }

    public static Word generateDictionaryWord(String word)
    {
        Word dictionaryWord = new Word();
        dictionaryWord.setProperNoun(isWordProperNoun(word));
        String alpahabeticalLetters = sortLettersOfWord(word);
        dictionaryWord.setWord(word);
        dictionaryWord.setWordAlphabetical(alpahabeticalLetters);

        return dictionaryWord;
    }

    private static boolean isWordProperNoun(String word)
    {
        return Character.isUpperCase(word.charAt(0));
    }
}
