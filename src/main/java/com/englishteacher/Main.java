package com.englishteacher;

import com.englishteacher.tools.EnglishTeacher;
import com.englishteacher.tools.Menu;

public class Main {
    public static void main(String[] args) {
            EnglishTeacher et = new EnglishTeacher();
        try {
            Menu menu = new Menu(et);
            menu.welcome();
        } finally {
            et.quitDriver();
        }
    }

}

