package com.englishteacher.utils;

import lombok.Getter;

public enum TasksPaths {
    TASK001p1("src/main/resources/Assignments/001/tasks1.csv"),
    TASK001p2("src/main/resources/Assignments/001/tasks2.csv"),
    TASK001p3("src/main/resources/Assignments/001/tasks3.csv"),
    TASK002p1("src/main/resources/Assignments/002/tasks1.csv"),
    TASK002p2("src/main/resources/Assignments/002/tasks2.csv"),
    TASK003p1("src/main/resources/Assignments/003/tasks1.csv"),
    TASK003p2("src/main/resources/Assignments/003/tasks2.csv"),
    TASK004p1("src/main/resources/Assignments/004/tasks1.csv"),
    TASK004p2("src/main/resources/Assignments/004/tasks2.csv"),
    TASK005p1("src/main/resources/Assignments/005/tasks1.csv"),
    TASK005p2("src/main/resources/Assignments/005/tasks2.csv"),
    TASK005p3("src/main/resources/Assignments/005/tasks3.csv");

    @Getter
    private final String tasksPaths;

    TasksPaths(String tasksPaths) {
        this.tasksPaths = tasksPaths;
    }
}
