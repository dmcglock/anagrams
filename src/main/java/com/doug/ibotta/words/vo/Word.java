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

    private boolean isProperNoun;

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

    public boolean isProperNoun() {
        return isProperNoun;
    }

    public void setProperNoun(boolean properNoun) {
        isProperNoun = properNoun;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", wordAlphabetical='" + wordAlphabetical + '\'' +
                ", isProperNoun=" + isProperNoun +
                '}';
    }
}
