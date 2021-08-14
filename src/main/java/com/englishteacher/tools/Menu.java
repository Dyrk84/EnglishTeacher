package com.englishteacher.tools;

import com.englishteacher.webdriver.DriverProvider;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.englishteacher.utils.LessonsPaths.LESSON001;
import static com.englishteacher.utils.LessonsPaths.LESSON002;
import static com.englishteacher.utils.LessonsPaths.LESSON003;
import static com.englishteacher.utils.LessonsPaths.LESSON004;
import static com.englishteacher.utils.LessonsPaths.LESSON005;
import static com.englishteacher.utils.TasksPaths.TASK001p1;
import static com.englishteacher.utils.TasksPaths.TASK002p1;
import static com.englishteacher.utils.TasksPaths.TASK003p1;
import static com.englishteacher.utils.TasksPaths.TASK004p1;
import static com.englishteacher.utils.TasksPaths.TASK005p1;
import static com.englishteacher.utils.TextColors.Colors.RED;
import static com.englishteacher.utils.TextColors.Colors.RESET;

public class Menu extends DriverProvider {

    static EnglishTeacher et = new EnglishTeacher();

    public static void welcome() {
        String welcomeText = "Szia! Tanuljunk egy kis angolt!";
        //    et.spellWord(welcomeText); //TODO majd visszarakni, de tesztelésnél elég sok idő mindig meghallgatni
        System.out.println("********************************************************************");
        System.out.println("*               Szia! Tanuljunk egy kis angolt!                    *");
        System.out.println("********************************************************************");
        menu();
    }

    private static void menu() {
        printMenu();
        int chosenNumber = chosenNumber();
        switch (chosenNumber) {
            case 1:
                englishLessons();
                break;
            case 2:
                tasksForLessons();
                break;
            case 3:
                et.upreading();
                menu();
                break;
            case 4:
                et.testMode = false;
                System.out.println("Tesztmód kikapcsolva!");
                menu();
                break;
            case 0:
                exit();
                break;
            default:
                yourChooseIsNotAppropriate();
                menu();
        }
    }

    private static int chosenNumber() {
        do {
            System.out.print("Írd ide a kívánt menü sorszámát: ");
            Scanner scanner = new Scanner(System.in);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException hibafogo) {
                yourChooseIsNotAppropriate();
            }
        } while (true);
    }

    private static void yourChooseIsNotAppropriate() {
        System.out.println(RED.typeOfColor + "Nem értelmezhető ez a válasz!" + RESET.typeOfColor);
    }

    public static void exit() {
        System.out.println("Minden jót neked!");
        et.quitDriver();
    }

    private static void printMenu() {
        System.out.println("*************************** Főmenü *********************************");
        System.out.println("* " + RED.typeOfColor + " Válassz az alábbi menüpontok közül:" + RESET.typeOfColor + "                             *");
        System.out.println("* " + RED.typeOfColor + "1." + RESET.typeOfColor + " Angol leckék                                                  *");
        System.out.println("* " + RED.typeOfColor + "2." + RESET.typeOfColor + " Angol leckékhez tartozó gyakorlófeladatok                     *");
        System.out.println("* " + RED.typeOfColor + "3." + RESET.typeOfColor + " Felolvasás Ki/Be kapcsolása                                   *");
        System.out.println("* " + RED.typeOfColor + "4." + RESET.typeOfColor + " Teszt mód kikapcsolása                                        *"); //TODO kivenni majd később
        System.out.println("* " + RED.typeOfColor + "0." + RESET.typeOfColor + " Kilépés                                                       *");
        System.out.println("********************************************************************");
    }

    public static void englishLessons() {
        printEnglishLessons();
        int chosenNumber = chosenNumber();
        switch (chosenNumber) {
            case 0:
                menu();
                break;
            case 1:
                et.lessonOperator(LESSON001.getLessonsPaths());
                break;
            case 2:
                et.lessonOperator(LESSON002.getLessonsPaths());
                break;
            case 3:
                et.lessonOperator(LESSON003.getLessonsPaths());
                break;
            case 4:
                et.lessonOperator(LESSON004.getLessonsPaths());
                break;
            case 5:
                et.lessonOperator(LESSON005.getLessonsPaths());
                break;
            default:
                yourChooseIsNotAppropriate();
                englishLessons();
        }
    }

    private static void printEnglishLessons() {
        System.out.println("************************ Angol leckék ******************************");
        System.out.println("* " + RED.typeOfColor + " Válassz az alábbi menüpontok közül:" + RESET.typeOfColor + "                             *");
        System.out.println("* " + RED.typeOfColor + "1." + RESET.typeOfColor + "  Személyes névmások                                           *");
        System.out.println("* " + RED.typeOfColor + "2." + RESET.typeOfColor + "  Létige                                                       *");
        System.out.println("* " + RED.typeOfColor + "3." + RESET.typeOfColor + "  Határozott és határozatlan névelő                            *");
        System.out.println("* " + RED.typeOfColor + "4." + RESET.typeOfColor + "  Összevonás                                                   *");
        System.out.println("* " + RED.typeOfColor + "5." + RESET.typeOfColor + "  Igeidők általánosságban                                      *");
        System.out.println("* " + RED.typeOfColor + "0." + RESET.typeOfColor + "  Vissza a főmenübe                                            *");
        System.out.println("********************************************************************");
    }

    public static void tasksForLessons() {
        printTasksForLessons();
        int chosenNumber = chosenNumber();
        switch (chosenNumber) {
            case 0:
                menu();
                break;
            case 1:
                et.tasksOperator(TASK001p1.getTasksPaths());
                break;
            case 2:
                et.tasksOperator(TASK002p1.getTasksPaths());
                break;
            case 3:
                et.tasksOperator(TASK003p1.getTasksPaths());
                break;
            case 4:
                et.tasksOperator(TASK004p1.getTasksPaths());
                break;
            case 5:
                et.tasksOperator(TASK005p1.getTasksPaths());
                break;
            default:
                yourChooseIsNotAppropriate();
                englishLessons();
        }
    }

    private static void printTasksForLessons() {
        System.out.println("********************* Gyakorló feladatok ***************************");
        System.out.println("* " + RED.typeOfColor + " Válassz az alábbi menüpontok közül:" + RESET.typeOfColor + "                             *");
        System.out.println("* " + RED.typeOfColor + "1." + RESET.typeOfColor + "  Személyes névmások feladatok                                 *");
        System.out.println("* " + RED.typeOfColor + "2." + RESET.typeOfColor + "  Létige nyelvi feladatok                                      *");
        System.out.println("* " + RED.typeOfColor + "3." + RESET.typeOfColor + "  Határozott és határozatlan névelők feladatok                 *");
        System.out.println("* " + RED.typeOfColor + "4." + RESET.typeOfColor + "  Összevonás feladatok                                         *");
        System.out.println("* " + RED.typeOfColor + "5." + RESET.typeOfColor + "  Igeidőkről általánosságban feladatok                         *");
        System.out.println("* " + RED.typeOfColor + "0." + RESET.typeOfColor + "  Vissza a főmenübe                                            *");
        System.out.println("********************************************************************");
    }

    public static void lessonEndMenu(String lessonPath) {
        printLessonEndMenu();
        int chosenNumber = chosenNumber();
        switch (chosenNumber) {
            case 0:
                menu();
                break;
            case 1:
                et.lessonOperator(lessonPath);
                break;
            case 2:
                lessonPath = fromLessonToTasksPath(lessonPath);
                et.tasksOperator(lessonPath);
                break;
            case 3:
                lessonPath = fromLessonToNextLesson(lessonPath);
                et.lessonOperator(lessonPath);
                break;
            default:
                yourChooseIsNotAppropriate();
                lessonEndMenu(lessonPath);
        }
    }

    private static void printLessonEndMenu() {
        System.out.println("*********************** Lecke végi menü ****************************");
        System.out.println("* " + RED.typeOfColor + " Válassz az alábbi menüpontok közül:" + RESET.typeOfColor + "                             *");
        System.out.println("* " + RED.typeOfColor + "1." + RESET.typeOfColor + " Meg akarod ismételni ezt a leckét?                            *");
        System.out.println("* " + RED.typeOfColor + "2." + RESET.typeOfColor + " Jöhetnek a gyakorló feladatok?                                *");
        System.out.println("* " + RED.typeOfColor + "3." + RESET.typeOfColor + " Jöhet a következő lecke?                                      *");
        System.out.println("* " + RED.typeOfColor + "0." + RESET.typeOfColor + " Visszalépsz a főmenübe?                                       *");
        System.out.println("********************************************************************");
    }

    public static void taskEndMenu(String taskPath) {
        printTaskEndMenu();
        int chosenNumber = chosenNumber();
        switch (chosenNumber) {
            case 0:
                menu();
                break;
            case 1:
                et.tasksOperator(taskPath);
                break;
            case 2:
                taskPath = fromTasksToLesson(taskPath);
                et.lessonOperator(taskPath);
                break;
            case 3:
                taskPath = fromTasksToLesson(taskPath);
                taskPath = fromLessonToNextLesson(taskPath);
                et.lessonOperator(taskPath);
                break;
            case 4:
                taskPath = fromTaskstoNextTasks(taskPath);
                et.tasksOperator(taskPath);
                break;
            default:
                yourChooseIsNotAppropriate();
                taskEndMenu(taskPath);
        }
    }

    private static void printTaskEndMenu() {
        System.out.println("****************** Gyakorló feladat végi menü **********************");
        System.out.println("* " + RED.typeOfColor + " Válassz az alábbi menüpontok közül:" + RESET.typeOfColor + "                             *");
        System.out.println("* " + RED.typeOfColor + "1." + RESET.typeOfColor + " Meg akarod ismételni ezt a gyakorlatot?                       *");
        System.out.println("* " + RED.typeOfColor + "2." + RESET.typeOfColor + " Meg akarod ismételni az ehhez a gyakorlathoz tartozó leckét?  *");
        System.out.println("* " + RED.typeOfColor + "3." + RESET.typeOfColor + " Jöhet a következő angol lecke?                                *");
        System.out.println("* " + RED.typeOfColor + "4." + RESET.typeOfColor + " Jöhet a következő lecke gyakorlata?                           *");
        System.out.println("* " + RED.typeOfColor + "0." + RESET.typeOfColor + " Visszalépsz a főmenübe?                                       *");
        System.out.println("********************************************************************");
    }

    private static String fromTaskstoNextTasks(String taskPath) { //TP->next TP (a köv leckéé) //ez a metódus fogja a gyakorló feladat path útvonalat a következő gyakorló feladat path-ra változtatni.
        System.out.println("bejövő TP->next TP (a köv leckéé)" + taskPath);
        boolean haveNewPath = false;
        for (int s = 0; s < 10 && !haveNewPath; s++) { //százasok helyét nézi át, amikor megvan a találat átlép a tizesekre, kivéve, ha már át lett írva a path. A másik kettő for loop is így működik.
            Pattern patterns = Pattern.compile(s + "\\d\\d/tasks\\d\\.csv$"); //a pattern az, amit keresek / regex magyarázat: a \\ a . elött arra kell, hogy a . ne azt jelentse, hogy bármelyik karakter jó ide. A $ jel azt jelenti, hogy a String végétől keressen.
            Matcher matchers = patterns.matcher(taskPath); //a matcher az, amiben keresem)
            if (matchers.find()) {
                for (int t = 0; t < 10 && !haveNewPath; t++) {
                    Pattern patternt = Pattern.compile("\\d" + t + "\\d/tasks\\d\\.csv$");
                    Matcher matchert = patternt.matcher(taskPath);
                    if (matchert.find()) {
                        for (int e = 0; e < 10 && !haveNewPath; e++) {
                            Pattern patterne = Pattern.compile("\\d\\d" + e + "/tasks\\d\\.csv$");
                            Matcher matchere = patterne.matcher(taskPath);
                            if (matchere.find()) { //ha talált a matcher olyant ami a patternben van
                                if (e == 9) { //hozzá kell adni egyet, így a tizes egyel több lesz, ez pedig visszaugrik 0-ra
                                    t++;
                                    e = 0;
                                    if (t == 10) { //azért 10 mert az előző if már megnövelte, de eredetileg a path-ban 9 van.
                                        s++;
                                        t = 0;
                                    }
                                } else {
                                    e++; //ha nem kell helyi értékben váltani, akkor simán hozzá ad egyet.
                                }
                                Integer eI = e;
                                Integer tI = t;
                                Integer sI = s; //az int primitivet átcastoltam Integeré, hogy azt meg át lehessen váltani Stringé, és így már nem adja össze a három számot, hanem egymás után teszi.
                                taskPath = taskPath.replaceFirst("\\d\\d\\d/tasks\\d\\.csv$", sI.toString() + tI.toString() + eI.toString() + "/tasks1\\.csv");
                                haveNewPath = true;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("TP->next TP (a köv leckéé)" + taskPath);
        return taskPath;
    }

    private static String fromTasksToLesson(String taskPath) { //TP -> LP//ez a metódus fogja a gyakorló path útvonalat a gyakorló feladatokhoz tartozó lecke path-ra változtatni. //TODO ez nem jól működik!!!
        System.out.println("bejövő TP -> LP: " + taskPath);
        boolean haveNewPath = false;
        for (int s = 0; s < 10 && !haveNewPath; s++) { //százasok helyét nézi át, amikor megvan a találat átlép a tizesekre, kivéve, ha már át lett írva a path. A másik kettő for loop is így működik.
            Pattern patterns = Pattern.compile(s + "\\d\\d/tasks\\d\\.csv$"); //a pattern az, amit keresek / regex magyarázat: a \\ a . elött arra kell, hogy a . ne azt jelentse, hogy bármelyik karakter jó ide. A $ jel azt jelenti, hogy a String végétől keressen.
            Matcher matchers = patterns.matcher(taskPath); //a matcher az, amiben keresem)
            if (matchers.find()) {
                for (int t = 0; t < 10 && !haveNewPath; t++) {
                    Pattern patternt = Pattern.compile("\\d" + t + "\\d/tasks\\d\\.csv$");
                    Matcher matchert = patternt.matcher(taskPath);
                    if (matchert.find()) {
                        for (int e = 0; e < 10 && !haveNewPath; e++) {
                            Pattern patterne = Pattern.compile("\\d\\d" + e + "/tasks\\d\\.csv$");
                            Matcher matchere = patterne.matcher(taskPath);
                            if (matchere.find()) { //ha talált a matcher olyant ami a patternben van
                                Integer eI = e;
                                Integer tI = t;
                                Integer sI = s; //az int primitivet átcastoltam Integeré, hogy azt meg át lehessen váltani Stringé, és így már nem adja össze a három számot, hanem egymás után teszi.
                                taskPath = taskPath.replaceFirst("Assignments/\\d\\d\\d/tasks\\d\\.csv$",
                                        "Lessons/lesson" + sI.toString() + tI.toString() + eI.toString() + "\\.csv");
                                haveNewPath = true;
                                //"src/main/resources/Assignments/001/tasks1.csv"
                                //"src/main/resources/Lessons/lesson001.csv"
                            }
                        }
                    }
                }
            }
        }
        System.out.println("TP -> LP: " + taskPath);
        return taskPath;
    }

    private static String fromLessonToTasksPath(String lessonPath) { //LP -> TP
        System.out.println("bejövő LP -> TP" + lessonPath);

        boolean haveNewPath = false;
        for (int s = 0; s < 10 && !haveNewPath; s++) { //százasok helyét nézi át, amikor megvan a találat átlép a tizesekre, kivéve, ha már át lett írva a path. A másik kettő for loop is így működik.
            Pattern patterns = Pattern.compile(s + "\\d\\d\\.csv$"); //a pattern az, amit keresek / regex magyarázat: a \\ a . elött arra kell, hogy a . ne azt jelentse, hogy bármelyik karakter jó ide. A $ jel azt jelenti, hogy a String végétől keressen.
            Matcher matchers = patterns.matcher(lessonPath); //a matcher az, amiben keresem)
            if (matchers.find()) {
                for (int t = 0; t < 10 && !haveNewPath; t++) {
                    Pattern patternt = Pattern.compile("\\d" + t + "\\d\\.csv$");
                    Matcher matchert = patternt.matcher(lessonPath);
                    if (matchert.find()) {
                        for (int e = 0; e < 10 && !haveNewPath; e++) {
                            Pattern patterne = Pattern.compile("\\d\\d" + e + "\\.csv$");
                            Matcher matchere = patterne.matcher(lessonPath);
                            if (matchere.find()) { //ha talált a matcher olyant ami a patternben van
                                Integer eI = e;
                                Integer tI = t;
                                Integer sI = s; //az int primitivet átcastoltam Integeré, hogy azt meg át lehessen váltani Stringé, és így már nem adja össze a három számot, hanem egymás után teszi.
                                lessonPath = lessonPath.replaceFirst("Lessons/lesson\\d\\d\\d\\.csv$",
                                        "Assignments/" + sI.toString() + tI.toString() + eI.toString() + "/tasks1\\.csv");
                                haveNewPath = true;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("LP -> TP" + lessonPath);
        return lessonPath;
    }

    private static String fromLessonToNextLesson(String lessonPath) { //LP -> next LP//ez a metódus fogja a lecke path útvonalat a következő lecke path-ra változtatni.
        System.out.println("bejövő LP -> next LP" + lessonPath);
        boolean haveNewPath = false;
        for (int s = 0; s < 10 && !haveNewPath; s++) { //százasok helyét nézi át, amikor megvan a találat átlép a tizesekre, kivéve, ha már át lett írva a path. A másik kettő for loop is így működik.
            Pattern patterns = Pattern.compile(s + "\\d\\d\\.csv$"); //a pattern az, amit keresek / regex magyarázat: a \\ a . elött arra kell, hogy a . ne azt jelentse, hogy bármelyik karakter jó ide. A $ jel azt jelenti, hogy a String végétől keressen.
            Matcher matchers = patterns.matcher(lessonPath); //a matcher az, amiben keresem)
            if (matchers.find()) {
                for (int t = 0; t < 10 && !haveNewPath; t++) {
                    Pattern patternt = Pattern.compile("\\d" + t + "\\d\\.csv$");
                    Matcher matchert = patternt.matcher(lessonPath);
                    if (matchert.find()) {
                        for (int e = 0; e < 10 && !haveNewPath; e++) {
                            Pattern patterne = Pattern.compile("\\d\\d" + e + "\\.csv$");
                            Matcher matchere = patterne.matcher(lessonPath);
                            if (matchere.find()) { //ha talált a matcher olyant ami a patternben van
                                if (e == 9) { //hozzá kell adni egyet, így a tizes egyel több lesz, ez pedig visszaugrik 0-ra
                                    t++;
                                    e = 0;
                                    if (t == 10) { //azért 10 mert az előző if már megnövelte, de eredetileg a path-ban 9 van.
                                        s++;
                                        t = 0;
                                    }
                                } else {
                                    e++; //ha nem kell helyi értékben váltani, akkor simán hozzá ad egyet.
                                }
                                Integer eI = e;
                                Integer tI = t;
                                Integer sI = s; //az int primitivet átcastoltam Integeré, hogy azt meg át lehessen váltani Stringé, és így már nem adja össze a három számot, hanem egymás után teszi.
                                lessonPath = lessonPath.replaceFirst("\\d\\d\\d\\.csv$", sI.toString() + tI.toString() + eI.toString() + "\\.csv");
                                haveNewPath = true;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("LP -> next LP" + lessonPath);
        return lessonPath;
    }
}

