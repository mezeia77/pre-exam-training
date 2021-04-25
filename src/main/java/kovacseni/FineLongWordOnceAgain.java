package kovacseni;

public class FineLongWordOnceAgain {
    public char[][] getFineLongWordOnceAgainButNowInAReverseWay(String s, int i) {
        if(i>s.length()){
            throw new IllegalArgumentException("Number of letters cannot be more than length of the word!");
        }
        char[][] result = new char[i][i];



        return result;
    }
}

//Szép hosszú szavak - még egyszer ugyanaz, de megfordítva
//A FineLongWordOnceAgain osztályban írj egy metódust, amely egy tömböt add vissza.
// Ez a tömb a következőhöz hasonló formában kell, hogy tartalmazzon egy szép hosszú szót:
//
//EXAM
//XAMP
//AMPL
//MPLE
//A metódus neve legyen getFineLongWordOnceAgainButNowInAReverseWay() (ha már "szép hosszú szó" :) ),
// és paraméterként várja magát a szót, valamint azt, hogy hány betű szélességben adja vissza a szót (a sorok száma ugye innentől kezdve adott lesz).
// Ellenőrizz rá arra is, hogy ez alapján a szám alapján a feladat megoldható-e!
