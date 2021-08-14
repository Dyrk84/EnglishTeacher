package com.englishteacher.tools;

import com.englishteacher.webdriver.DriverProvider;

public class Assignments extends DriverProvider {
    static EnglishTeacher et = new EnglishTeacher();

    public void tasksForTheLesson1(){
        System.out.println("Kezdjük el az első lecke, azaz a személyes névmások feladatait:");
        System.out.println("A feladat a következő: meg kell adnod az angol megfelelőjét a magyar névmásoknak.");
        System.out.println("A cél az, hogy minden kérdésre a választ helyesen add meg.");
        System.out.println("A szavakat összekeverve kapod, és pontokat kapsz minden szóra.");
        System.out.println("Jó válaszra 1 pontot kapsz, rossz válasz miatt veszítesz 3-at.");
        System.out.println("Ha egy szónál eléred a 3 pontot, kikerül a kérdezendő szavak listájáról.");
        System.out.println("Ha nehéz, nézd meg újra a leckét, akár felolvasás nélkül!");
        System.out.println("Figyeld nagyon az angol kiejtést, ha van rá lehetőséged, ismételd el hangosan! (Ez nagyon fontos!)");


    }
}
