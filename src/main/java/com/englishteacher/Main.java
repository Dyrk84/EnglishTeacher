package com.englishteacher;

import com.englishteacher.tools.Menu;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Menu menu = new Menu();
        menu.welcome();
    }

}

