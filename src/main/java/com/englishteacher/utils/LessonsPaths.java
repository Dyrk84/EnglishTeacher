package com.englishteacher.utils;

import lombok.Getter;

public enum LessonsPaths {
    LESSON001("src/main/resources/Lessons/lesson001.csv"),
    LESSON002("src/main/resources/Lessons/lesson002.csv"),
    LESSON003("src/main/resources/Lessons/lesson003.csv"),
    LESSON004("src/main/resources/Lessons/lesson004.csv"),
    LESSON005("src/main/resources/Lessons/lesson005.csv");
    @Getter
    private final String lessonsPaths;

    LessonsPaths(String lessonsPaths) {
        this.lessonsPaths = lessonsPaths;
    }
}

