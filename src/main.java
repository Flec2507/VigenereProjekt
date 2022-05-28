import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class main {

    public static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static ArrayList<Character> repeatingKey = new ArrayList<>();

    public static int userInputLength = 0;

    public static String output = "";

    public static void main(String[] args) {
        encode(getInput("input"), getInput("key"));
    }

    public static void encode(String input, String key) {
        System.out.println("Key: " + key + ", Text: " + input);
        if (key.length() != userInputLength) {//Resize the Key to the same length as the Text for easy encoding
            for (; ; ) {
                boolean breaker = false;//Helper bool for stopping the for(;;) loop
                for (int i = 0; i < key.length(); i++) {//Go through the key and add the letters one after the other
                    if (repeatingKey.size() < userInputLength) {//As long as the Key's length is smaller than the Text's length, add letters
                        repeatingKey.add(key.charAt(i));
                    } else {//When they are the same length, stop everything
                        breaker = true;
                        break;
                    }
                }
                if (breaker) {
                    break;
                }
            }
        }
        System.out.println(input);//Print the Text for clarity
        for (int i = 0; i < repeatingKey.size(); i++) {//Print the key for clarity
            System.out.print(repeatingKey.get(i));
        }
        System.out.println("");//Switch to next line
        for (int i = 0; i < userInputLength; i++) {//Encoding
            char A = input.charAt(i);//Letter at the position of the cycle in the input word
            char B = repeatingKey.get(i);//Letter at the position of the cycle in the key word
            char C = ' ';//Letter after encoding
            int D = 0;//Position of Key-Letter in alphabet
            int E = 0;//Amount of movement
            D = search(B);//See at definition
            E = search(A);
            E += D;//Set the letter plus the amount of revolving in the grid
            if (E > 25) {//If out of bounds, wrap around
                E = E - 25;
            }
            C = alphabet[E];
            System.out.print(C);
        }
        System.out.println();//Switch to next line
    }

    public static int search(char letter) {//Gives me the position of letter requested in the Alphabet
        int position = 0;
        for (int i = 0; i < alphabet.length; i++) {
            position = 0;
            if (alphabet[i] == letter) {
                position = i;
                break;
            }
        }
        return position;
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
                userInput = userInput.toUpperCase(Locale.ROOT);
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
                userKey = userKey.toUpperCase(Locale.ROOT);
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
