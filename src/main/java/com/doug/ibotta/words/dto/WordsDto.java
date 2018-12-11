package com.doug.ibotta.words.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class WordsDto {
    @NotNull
    List<String> words;

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "WordsDto{" +
                "words=" + words +
                '}';
    }
}
