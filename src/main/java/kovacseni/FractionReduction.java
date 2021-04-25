package kovacseni;

import java.util.Set;
import java.util.TreeSet;

public class FractionReduction {
    public int getNumberOfNotReductiveFractions() {
        final int denominator = 144;
        Set<Integer> numbers = new TreeSet<>();

            for (int i = 2; i<=denominator/2; i++) {
                if (denominator % i != 0) {
                    numbers.add(i);
                }
            }
        return numbers.size();
    }
}

//Törtek egyszerűsítése
//A FractionReduction osztályba írj egy metódust, amely a következő feladat megoldását adja vissza:
// Hány olyan 1-nél kisebb pozitív törtszám van, amelynek nevezője 144 és nem egyszerűsíthető?
// (Ha esetleg valaki nem emlékszik a matekórákról: a tört egyszerűsítése azt jelenti, hogy a számlálóját és a nevezőjét ugyanazzal a számmal osztjuk el egyszerre.)
