package com.doug.ibotta.anagram.dto;

import java.util.List;

public class Anagram {
    public List<String> anagrams;

    public List<String> getAnagrams() {
        return anagrams;
    }

    public void setAnagrams(List<String> anagrams) {
        this.anagrams = anagrams;
    }

    @Override
    public String toString() {
        return "Anagram{" +
                "anagrams=" + anagrams +
                '}';
    }
}
