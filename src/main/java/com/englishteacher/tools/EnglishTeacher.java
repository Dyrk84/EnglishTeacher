package com.englishteacher.tools;

import com.englishteacher.pageobjects.GoogleTranslate;
import com.englishteacher.utils.TasksPaths;
import com.englishteacher.webdriver.DriverProvider;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.englishteacher.utils.TextColors.Colors.BLUE;
import static com.englishteacher.utils.TextColors.Colors.CYAN;
import static com.englishteacher.utils.TextColors.Colors.GREEN;
import static com.englishteacher.utils.TextColors.Colors.PURPLE;
import static com.englishteacher.utils.TextColors.Colors.RED;
import static com.englishteacher.utils.TextColors.Colors.RESET;

public class EnglishTeacher extends DriverProvider {

    public boolean testMode = true;
    private boolean upreading = true; //ha true, működik a felolvasás.
    private boolean originalTaskPathSwitch = false;
    String originalTaskPath = "";

    public void spellWord(String word) {
        if (upreading) {
            setupBrowser();
            GoogleTranslate googleTranslate = new GoogleTranslate(driver);
            googleTranslate.enterText(word);
            googleTranslate.clickListen();
        }
    }

    public void upreading() { //itt lehet a felolvasást ki/bekapcsolni
        System.out.println(RED.typeOfColor + "Kéred a felolvasást?" + RESET.typeOfColor +
                " Ha igen, írd be: " + RED.typeOfColor + "\"yes\" " + RESET.typeOfColor + "vagy csak: " + RED.typeOfColor +
                "\"y\"" + RESET.typeOfColor + " bármi más beírásával kikapcsolod a felolvasást!");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        if (answer.equals("yes") || answer.equals("y")) {
            upreading = true;
            String readingOn = "Felolvasás bekapcsolva.";
            System.out.println(GREEN.typeOfColor + readingOn + RESET.typeOfColor);
            spellWord(readingOn);
        } else {
            upreading = false;
            System.out.println(RED.typeOfColor + "Felolvasás kikapcsolva." + RESET.typeOfColor);
        }
    }

    public void lessonOperator(String lessons) { //ez kezeli a leckét
        List<List<String>> listLesson = new ArrayList<>();
        listFromFile(listLesson, lessons);
        listReader(listLesson);
        lessonEnd(lessons);
    }

    public void listFromFile(List<List<String>> list, String path) {  //ez rakja össze a fileból a listát
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] thingsInFile = line.split("%");
                list.add(Arrays.asList(thingsInFile));
            }
        } catch (IOException e) {
            System.out.println("readFromFileToListList(List<List<String>> dictionaryInList, String path) exception: " + e);
        }
    }

    public void listReader(List<List<String>> list) { //nyomtatja és felolvassa ami a listában van szöveg. Ha a második helyen "n" betü van, nem veszi figyelembe.

        for (int i = 0; i < list.size(); i++) {
            System.out.print(BLUE.typeOfColor + list.get(i).get(0));
            if (!list.get(i).get(1).equals("n")) {
                System.out.print(": " + list.get(i).get(1));
            }
            System.out.println(RESET.typeOfColor);
            spellWord(list.get(i).get(0));
            if (!list.get(i).get(1).equals("n")) {
                spellWord(list.get(i).get(1));
            }
        }
    }

    private void lessonEnd(String lessons) {
        String lessonEndMessage = "Itt a lecke vége.";
        System.out.println(RED.typeOfColor + lessonEndMessage + RESET.typeOfColor);
        spellWord(lessonEndMessage);
        Menu.lessonEndMenu(lessons);
    }

    private void tasksEnd(){
        String tasksEndMessage = "Végigértél a gyakorlófeladatokon.";
        System.out.println(RED.typeOfColor + tasksEndMessage + RESET.typeOfColor);
        spellWord(tasksEndMessage);
        String taskPath = originalTaskPath;
        originalTaskPathSwitch = false;
        Menu.taskEndMenu(taskPath);
    }

    private void multiMapCreator(List<List<String>> list, MultiValuedMap<String, String> multimap) { //feltölti a multiMapet a fileból kiolvasott lista elemeivel
        for (int i = 0; i < list.size(); i++) {
            multimap.put(list.get(i).get(0), list.get(i).get(1));
        }
    }

    public void scoresMapCreatorFromListFromFile0Row(List<String> list, Map<String, Integer> scoresMap) { //ez a map fogja tartalmazni a file első oszlopának Stringjeit, a második oszlopba 0 kerül. Ezt majd át kell alakítani inté.
        for (int i = 0; i < list.size(); i++) {
            scoresMap.put(list.get(i), 0);
        }
    }

    private void questionListCreator(List<String> questionList, Map<String, Integer> mapWithWordsAndValues, List<String> noDuplicatesList, int repeatNumber) {
        for (int i = 0; i < mapWithWordsAndValues.size(); i++) {
            if (mapWithWordsAndValues.get(noDuplicatesList.get(i)) < repeatNumber) { //ez megnézi a map szavaihoz tartozó integereket, melyik kisebb mint a randomNum
                questionList.add(noDuplicatesList.get(i)); //ha kisebb a pontszám, betölti a questionListbe a Stringet.
            }
        }
    }

    private String scanner() {
        while (true) {
            try {
                Scanner reader = new Scanner(System.in);
                return reader.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(RED.typeOfColor + "Biztos ezt akardtad ide írni?!" + RESET.typeOfColor);
            }
        }
    }

    private String question(List<String> questionList) {
        String askedWord = "";
        Collections.shuffle(questionList);
        spellWord(questionList.get(0));
        askedWord = questionList.get(0);
        System.out.println(BLUE.typeOfColor + askedWord + RESET.typeOfColor);
        return askedWord;
    }

    public String answer() {
        System.out.print("A válaszodat írd ide: ");
        String answer = scanner();
        return answer;
    }

    private void answerChecker
            (MultiValuedMap<String, String> multiMap, Map<String, Integer> scoresMap, String
                    ask, String answer) {
        if (multiMap.get(ask).contains(answer)) { //ezzel nézem meg, hogy a kérdezett szóhoz tartozik-e olyan value, mint a válasz szó a könyvtármapban.
            spellWord(answer);
            System.out.print(GREEN.typeOfColor + "Jó a válasz!" + RESET.typeOfColor);
            scoresMap.put(ask, scoresMap.get(ask) + 1); //ezzel módosítom a mapban a kérdezett szó-hoz (key) tartozó value-t.
            System.out.println(" \"" + CYAN.typeOfColor + ask + RESET.typeOfColor + "\" kérdés pontszáma növekedett, így most az értéke: " + scoresMap.put(ask, scoresMap.get(ask)));
        } else {
            scoresMap.put(ask, scoresMap.get(ask) - 3);
            System.out.println(RED.typeOfColor + "Sajnos nem ez a jó válasz! " + RESET.typeOfColor
                    + " \"" + PURPLE.typeOfColor + ask + RESET.typeOfColor + "\" kérdés pontszáma lecsökkent, így most az értéke: " + scoresMap.put(ask, scoresMap.get(ask)));
            System.out.println("A várt válasz: " + GREEN.typeOfColor + multiMap.get(ask) + RESET.typeOfColor); //TODO megoldani, hogy kimondja a spellWord()
        }
    }

    private void listCreatorFromFile0Row(List<List<String>> listTasks, List<String> listFromFile0Row) {
        for (int i = 0; i < listTasks.size(); i++) {
            listFromFile0Row.add(listTasks.get(i).get(0));
        }
    }

    public void tasksOperator(String taskPath) {
        System.out.println("tasks futtatóban az elején a taskPath " + taskPath);
        if (!originalTaskPathSwitch) {
            originalTaskPath = taskPath;
        }
        originalTaskPathSwitch = true;
        List<List<String>> listTasks = new ArrayList<>(); //ez kell a file listába töltéséhez (ebből lesz a többi lista)
        listFromFile(listTasks, taskPath);
        if (testMode) {
            System.out.println("Itt jön a gyakorlófeladat"); //TODO ha véget ért a tesztelgetés, ezt kiszedni
        }
        if (!testMode) {
        for (int i = 0; i < listTasks.size(); i++) { //ha a file-ban van olyan sor, ahol a 2. oszlopban "n" betü van, akkor az 1. oszlopot nyomtatja,felolvassa (instrukciók a feladathoz).
            if (listTasks.get(i).get(1).equals("n")) {
                System.out.println(listTasks.get(i).get(0));
                spellWord(listTasks.get(i).get(0));
            }
        }
        for (int j = 0; j < listTasks.size(); j++) { //kitörli az instrukciókat, hogy csak a feladatok maradjanak bent a listában.
             if (listTasks.get(j).get(1).equals("n")) {
                 listTasks.remove(listTasks.get(j));
                 j--; //ha nem ugrik vissza, kimarad a következő sor vizsgálata
             }
        }

        List<String> listFromFile0Row = new ArrayList<>(); //ebben a listában lesz a file 0. oszlopa. A noDuplicates listához fog kelleni, ebből tölti fel a Set-et.
        listCreatorFromFile0Row(listTasks, listFromFile0Row);

        Set<String> noDuplicatesSet = new HashSet(listFromFile0Row); //ez a set azért kell, hogy ha van ugyanolyan kérdés a 0. oszlopban, akkor ne jelenleg meg duplán (olyankor lehetséges, ha több válasz is adható rá)
        List<String> noDuplicatesList = new ArrayList<>(noDuplicatesSet); //ez csinál egy String listát a setből. Sajnos nem tudtam rájönni, hogy hogy lehet setből kiszedni a Stringeket iteráláshoz.
        List<String> questionList = new ArrayList<>(); //ebből a listából fog kérdezni

        Map<String, Integer> scoresMap = new HashMap<>(); //Ebben lesznek a pontszámok, 3 után nem kérdezi többet az adott kérdést.
        scoresMapCreatorFromListFromFile0Row(listFromFile0Row, scoresMap);

        MultiValuedMap<String, String> multiMap = new ArrayListValuedHashMap<>(); //ez kell a kérdéses listához és a válasz ellenőrzéséhez (több value lehet, mert több megoldás is előfordúlhat)
        multiMapCreator(listTasks, multiMap);

        for (int i = 0; i < 1000; i++) {
            questionList.removeAll(questionList); //lenullázom a questionList tartalmát, azzal hogy kitörlöm a tartalmát önmaga tartalmával.
            questionListCreator(questionList, scoresMap, noDuplicatesList, 3); //összerakja a questionList-et mindig újra
            if (questionList.size() > 0) {
                String ask = question(questionList);
                String answer = answer();
                answerChecker(multiMap, scoresMap, ask, answer);
            }
        }
        }
        if (testMode) {
            System.out.println("itt ért véget a gyakorló feladat rész"); //TODO ha véget ért a tesztelgetés, ezt kiszedni
        }
        taskPath = repeater(taskPath); //a feladatok path-ját változtatja, hogy a következőt returnolja vissza, de csak adott leckén bellül!

        boolean haveSameTaskPath = false; //arra van szükség, hogy ha már a leckéhez nincs több file, akkor a metódus befejezze a futást.
        TasksPaths[] values = TasksPaths.values(); //létrehoz egy tömböt, amibe belerakja az Enumokat
        for (TasksPaths taskpaths : values) { //végigiterál az Enumokon
            if (taskpaths.getTasksPaths().equals(taskPath)) { //ha talál olyant, ami az enumok konstruktorával megegyezik, akkor a boolean truera vált.
                haveSameTaskPath = true; //ez azt jelenti, hogy van még lejátszható file ehhez a gyakorló leckéhez.
                break;
            }
        }

        if (haveSameTaskPath) { //ha talált ehhez a gyakorló leckéhez további fileokat, akkor újra futtatja a metódust.
            tasksOperator(taskPath);
        } else {
            tasksEnd();
        }
    }

    private String repeater(String taskPath) { //a feladatok path-ját változtatja, hogy a következőt returnolja vissza adott leckén bellül
        boolean haveNewPath = false;
        for (int e = 0; e < 10 && !haveNewPath; e++) {
            Pattern patterne = Pattern.compile(e + "\\.csv$"); //a pattern az, amit keresek / regex magyarázat: a \\ a . elött arra kell, hogy a . ne azt jelentse, hogy bármelyik karakter jó ide. A $ jel azt jelenti, hogy a String végétől keressen.
            Matcher matchere = patterne.matcher(taskPath); //a matcher az, amiben keresem)
            if (matchere.find()) {
                e++;
                Integer eI = e;
                taskPath = taskPath.replaceFirst("\\d\\.csv$", eI.toString() + "\\.csv");
                haveNewPath = true;
            }
        }
        return taskPath;
    }
}
