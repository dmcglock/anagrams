package com.doug.ibotta.words.dto;

public class WordCountDto {
    private Long wordCount = 0L;
    private Integer minWordLength = 0;
    private Integer maxWordLength = 0;
    private Double medianWordLength = 0.0;
    private Double averageWordLength = 0.0;

    public WordCountDto() {
    }

    public WordCountDto(Long wordCount, Integer minWordLength, Integer maxWordLength, Double medianWordLength, Double averageWordLength) {
        this.wordCount = wordCount;
        this.minWordLength = minWordLength;
        this.maxWordLength = maxWordLength;
        this.medianWordLength = medianWordLength;
        this.averageWordLength = averageWordLength;
    }

    public Long getWordCount() {
        return wordCount;
    }

    public void setWordCount(Long wordCount) {
        this.wordCount = wordCount;
    }

    public Integer getMinWordLength() {
        return minWordLength;
    }

    public void setMinWordLength(Integer minWordLength) {
        this.minWordLength = minWordLength;
    }

    public Integer getMaxWordLength() {
        return maxWordLength;
    }

    public void setMaxWordLength(Integer maxWordLength) {
        this.maxWordLength = maxWordLength;
    }

    public Double getMedianWordLength() {
        return medianWordLength;
    }

    public void setMedianWordLength(Double medianWordLength) {
        this.medianWordLength = medianWordLength;
    }

    public Double getAverageWordLength() {
        return averageWordLength;
    }

    public void setAverageWordLength(Double averageWordLength) {
        this.averageWordLength = averageWordLength;
    }

    @Override
    public String toString() {
        return "WordCountDto{" +
                "wordCount=" + wordCount +
                ", minWordLength=" + minWordLength +
                ", maxWordLength=" + maxWordLength +
                ", medianWordLength=" + medianWordLength +
                ", averageWordLength=" + averageWordLength +
                '}';
    }
}
