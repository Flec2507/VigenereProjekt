import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class main {

    public static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static ArrayList<Character> repeatingKey = new ArrayList<>();

    public static int userInputLength = 0;

    public static void main(String[] args) {
        encode(getInput("input"), getInput("key"));
    }

    public static void encode(String input, String key) {
        System.out.println("Key: " + key + ", Text: " + input);
        if (key.length() != userInputLength) {//Resize the Key to the same length as the Text for easy encoding
            for (; ; ) {
                boolean breaker = false;
                for (int i = 0; i < key.length(); i++) {
                    if (repeatingKey.size() < userInputLength) {
                        repeatingKey.add(key.charAt(i));
                    } else {
                        System.out.println(input);
                        breaker = true;
                        break;
                    }
                }
                if (breaker) {
                    break;
                }
            }
        }
        for (int i = 0; i < repeatingKey.size(); i++) {//Print the key for clarity
            System.out.print(repeatingKey.get(i));
        }
    }

    public static String getInput(String use) {//Get stuff from the User
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String userKey = "";
        if (use.equals("input")) {//When we want the Input we call this
            System.out.println("Bitte geben Sie ihren Text ein!");
            for (; ; ) {
                userInput = "";
                userInput = scanner.nextLine();
                if (!Pattern.matches("[a-zA-Z]+", userInput)) {//If the Text contains anything else than letters it is unusable
                    System.out.println("Ich bin leider nicht in der Lage irgendetwas abseits von Buchstaben zu verstehen... Bitte versuchen Sie es erneut!");
                } else {//Everything should work with this
                    System.out.println("Pure Buchstaben");
                    break;
                }
            }
            userInputLength = userInput.length();//We need a global variable for this because the if statement in the Key-Case is in a separate call of the function
            return userInput;
        } else if (use.equals("key")) {//Same thing
            System.out.println("Bitte geben Sie ihren Schlüssel ein!");
            for (; ; ) {
                userKey = "";
                userKey = scanner.nextLine();
                if (!Pattern.matches("[a-zA-Z]+", userKey)) {//Same thing
                    System.out.println("Ich bin leider nicht in der Lage irgendetwas abseits von Buchstaben zu verstehen... Bitte versuchen Sie es erneut!");
                } else {
                    if (userKey.length() > userInputLength) {//A Key longer than the Text is not useful
                        System.out.println("Ihr Key ist länger als Ihr Input. Das funktioniert hier so nicht. Bitte versuchen Sie es erneut!");
                    } else {//Same thing
                        System.out.println("Pure Buchstaben");
                        break;
                    }
                }
            }
            return userKey;
        } else {
            return "ERROR";//If none of the two programmed use-cases is called, which will never happen
        }
    }
}
