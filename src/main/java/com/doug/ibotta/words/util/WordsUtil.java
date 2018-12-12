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
        String alpahabeticalLetters = sortLettersOfWord(word);
        dictionaryWord.setProperNoun(isWordProperNoun(word));
        dictionaryWord.setWord(word);
        dictionaryWord.setWordAlphabetical(alpahabeticalLetters);

        return dictionaryWord;
    }

    private static boolean isWordProperNoun(String word)
    {
        return Character.isUpperCase(word.charAt(0));
    }
}
