package exylaci.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmallerWith {
    public int smallerWith(int i, int[] numbers) {
        if(i<0){
            throw new IllegalArgumentException("Value is out of range!");
        }

        if (numbers==null){
            throw new IllegalArgumentException("Every parameter is a must!");
        }

        List<Integer> numbersList = new ArrayList<>();

        int max = 0;

        for (int temp:numbers){
            numbersList.add(temp);
            if(temp>max){
                max= temp;
            }
        }

        Collections.sort(numbersList);

        int value = max-i;

        int result = 0;

        for (int temp:numbersList){
            if(temp<=value){
                result= temp;
            }
        }

        if(result==0){
            throw new IllegalStateException("There is no such number!");
        }
        return result;
    }
}

//Add vissza azt a legnagyobb számot, ami a paraméterül kapott tömb legnagyobb eleménél legalább a szintén paraméterül kapott számmal kisebb.
//(Készülj fel minden eshetőségre.)
