package com.doug.ibotta.anagram.dto;

import java.util.List;

public class AnagramCheckerDto {

    public List<String> words;

    public Boolean anagrams;

    public AnagramCheckerDto() {
    }

    public AnagramCheckerDto(List<String> words, Boolean anagrams) {
        this.words = words;
        this.anagrams = anagrams;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public Boolean getAnagrams() {
        return anagrams;
    }

    public void setAnagrams(Boolean anagrams) {
        this.anagrams = anagrams;
    }

    @Override
    public String toString() {
        return "AnagramCheckerDto{" +
                "words=" + words +
                ", anagrams=" + anagrams +
                '}';
    }
}
