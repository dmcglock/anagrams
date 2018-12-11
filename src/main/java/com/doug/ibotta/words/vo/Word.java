package com.doug.ibotta.words.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Word {
    @Id
    @GeneratedValue
    private Long id;

    private String word;

    private String wordAlphabetical;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordAlphabetical() {
        return wordAlphabetical;
    }

    public void setWordAlphabetical(String wordAlphabetical) {
        this.wordAlphabetical = wordAlphabetical;
    }
}
