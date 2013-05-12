package com.suhorukov.klyuchevsky;

public interface ExceptionGenerator {
    void generateNullPointerException();

    void generateClassCastException();

    void generateNumberFormatException();

    void generateStackOverFlowError();

    void generateOutOfMemoryError();

    void generateMyException(String message) throws MyException;
}
