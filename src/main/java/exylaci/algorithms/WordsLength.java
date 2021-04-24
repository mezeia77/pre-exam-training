package exylaci.algorithms;

import java.util.*;

public class WordsLength {

    final List<Character> TO_BE_REMOVED =List.of('.', ',', '!', '?', '[', ']', '"');

    public Map<Integer, Integer> getStatistic(String... s) {
        if(s==null){
            throw new IllegalArgumentException("The parameter is a must!");
        }

        List<String> wordList = new ArrayList<>();
        String[] words = Arrays.toString(s).split(" ");

        for (String temp:words){
            StringBuilder alphabetic = removeNonAlphabeticals(temp);
            wordList.add(alphabetic.toString());
        }

        Map<Integer, Integer> result = new HashMap<>();

        if(wordList.get(0).length()==0){
            return result;
        }

        for (String temp:wordList){
            count(result, temp);
        }

        return result;
    }

    private void count(Map<Integer, Integer> result, String temp) {
        if(result.containsKey(temp.length())){
            result.put(temp.length(), result.get(temp.length())+1);
        } else {
            result.put(temp.length(), 1);
        }
    }

    private StringBuilder removeNonAlphabeticals(String temp) {
        StringBuilder alphabetic = new StringBuilder();
        for(int i = 0; i< temp.length(); i++){
            if(!TO_BE_REMOVED.contains(temp.charAt(i))){
                alphabetic.append(temp.charAt(i));
            }
        }
        return alphabetic;
    }
}

//Add vissza, hogy a kapott szövegben a különböző hosszúságú szavakból hány-hány darab van.
