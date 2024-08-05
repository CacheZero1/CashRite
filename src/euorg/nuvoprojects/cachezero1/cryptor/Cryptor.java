package euorg.nuvoprojects.cachezero1.cryptor;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Cryptor {

    private LinkedList<String> letters = new LinkedList<>(Arrays.asList(
        "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","ß",
        " ", ",", ".", "'", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "!", "§", "%", "&", "/", "(", ")", "=", "?", "*", "+", "#", ";", "$",
        "&#8352", "&#8353", "&#8354", "&#8355", "&#8356", "&#8357", "&#8358", "&#8359", "&#8360", "&#8361", "&#8362", "&#8363", "&#8364", "&#8365", "&#8366", "&#8367", "&#8368", "&#8369", "&#8370", "&#8371", "&#8372", "&#8373", "&#8374", "&#8375", "&#8376", "&#8377", "&#8378", "&#8379", "&#8380", "&#8381", "&#8382", "&#8383"
    ));
    private LinkedList<String> lettersChecklist = new LinkedList<>(Arrays.asList(
        "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","ß",
        " ", ",", ".", "'", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "!", "§", "%", "&", "/", "(", ")", "=", "?", "*", "+", "#", ";", "$",
        "&#8352", "&#8353", "&#8354", "&#8355", "&#8356", "&#8357", "&#8358", "&#8359", "&#8360", "&#8361", "&#8362", "&#8363", "&#8364", "&#8365", "&#8366", "&#8367", "&#8368", "&#8369", "&#8370", "&#8371", "&#8372", "&#8373", "&#8374", "&#8375", "&#8376", "&#8377", "&#8378", "&#8379", "&#8380", "&#8381", "&#8382", "&#8383"
    ));
    private LinkedHashMap<Integer, String> letterMap = new LinkedHashMap<>();
    private LinkedList<Integer> letterDefaultNums = new LinkedList<>();
    private LinkedList<String> errorLetter = new LinkedList<>();

    public Cryptor() {}

    public LinkedHashMap<String, LinkedHashMap<String, Object>> algorithm(String formula) {

        //Set Letter numbers
        for (int i = 0; i < letters.size(); i++) {
            letterMap.put(i, letters.get(i));
        }

        for (String letter : letters) {
            letterDefaultNums.add(letters.indexOf(letter) + 1);
        }

        // Make new Map with values
        LinkedHashMap<String, Integer> multiMap = new LinkedHashMap<>();
        for (Integer letterDefaultNumber : letterDefaultNums) {

            Double answer = Algorithm.evalString((formula.replace("x", String.valueOf(letterDefaultNumber))));
            Integer finalAnswer = ((Long) Math.round(answer)).intValue();

            multiMap.put(letters.get(letterDefaultNums.indexOf(letterDefaultNumber)), finalAnswer);

        }

        //Create Array Map
        LinkedHashMap<String, LinkedHashMap<String, Object>> encrypted = new LinkedHashMap<>();
        for (String letter : letters) {

            int encryptNumber = multiMap.get(letter);

            while (encryptNumber >= letters.size()) {
                encryptNumber -= letters.size();
            }

            String newLetter = letterMap.get(encryptNumber);

            if (lettersChecklist.contains(newLetter)) {

                lettersChecklist.remove(newLetter);

                LinkedHashMap<String, Object> individualEntry = new LinkedHashMap<>();
                individualEntry.put("Letter", letter);
                individualEntry.put("DefaultNumber", letterDefaultNums.get(letters.indexOf(letter)));
                individualEntry.put("NewNumber", letters.indexOf(letter));
                individualEntry.put("MathValue", multiMap.get(letter));
                individualEntry.put("Encrypted", newLetter);

                encrypted.put(letter, individualEntry);

            } else {

                errorLetter.add(letter);

                LinkedHashMap<String, Object> individualEntry = new LinkedHashMap<>();
                individualEntry.put("Letter", letter);
                individualEntry.put("DefaultNumber", letterDefaultNums.get(letters.indexOf(letter)));
                individualEntry.put("NewNumber", "null");
                individualEntry.put("MathValue", multiMap.get(letter));
                individualEntry.put("Encrypted", "null");

                encrypted.put(letter, individualEntry);

            }

        }

        for (String erroredLett : errorLetter) {

            String letterVar = lettersChecklist.removeFirst();

            encrypted.get(erroredLett).replace("Encrypted", letterVar);
            encrypted.get(erroredLett).replace("NewNumber", letterDefaultNums.get(letters.indexOf(letterVar)));

        }

        return encrypted;

    }

    public String encrypt(String text, LinkedHashMap<String, LinkedHashMap<String, Object>> cryptionLib) {

        String newText = "";

        for (int i = 0; i < text.length(); i++) {

            newText += cryptionLib.get(String.valueOf(text.charAt(i)).toUpperCase()).get("Encrypted");

        }

        return newText;

    }

    public String decrypt(String text, LinkedHashMap<String, LinkedHashMap<String, Object>> cryptionLib) {

        String newText = "";
        LinkedHashMap<String, Object> importantMap = new LinkedHashMap<>();

        for (int i = 0; i < text.length(); i++) {

            String character = String.valueOf(text.charAt(i)).toUpperCase();

            for (LinkedHashMap<String, Object> map : cryptionLib.values()) {

                if (map.get("Encrypted").equals(character)) {

                    importantMap = map;

                }

            }

            newText += importantMap.get("Letter");

        }

        return newText;

    }

}