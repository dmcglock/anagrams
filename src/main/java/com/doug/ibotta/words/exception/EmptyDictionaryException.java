package com.doug.ibotta.words.exception;

public class EmptyDictionaryException extends Exception{
    public EmptyDictionaryException() {
        super();
    }

    public EmptyDictionaryException(String message) {
        super(message);
    }

    public EmptyDictionaryException(String message, Throwable cause) {
        super(message, cause);
    }
}
