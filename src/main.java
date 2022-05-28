import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class main {

    public static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};//Just the alphabet, nothing to see here

    public static ArrayList<Character> repeatingKey = new ArrayList<>();//The key in the correct length

    public static int userInputLength = 0;//We need a global variable for the length of the Text because the if statement in the Key-Case is in a separate call of the function and would not be able to access this important info

    public static String output = "";//Self-explanatory

    public static void main(String[] args) {
        encode(getInput("input"), getInput("key"));//Self-explanatory
    }

    public static void encode(String input, String key) {
        System.out.println("Key: " + key + ", Text: " + input);//Print both for clarity

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
        for (int i = 0; i < repeatingKey.size(); i++) {//Print the resized key for clarity under the Text to check if they are the same length
            System.out.print(repeatingKey.get(i));
        }
        System.out.println("");//Switch to next line

        char A = ' ';//Usage explained later
        char B = ' ';//Usage explained later
        char C = ' ';//Usage explained later
        int D = 0;//Usage explained later
        int E = 0;//Usage explained later

        for (int i = 0; i < userInputLength; i++) {//Encoding the Text
            A = input.charAt(i);//Letter at the position of the for-cycle in the input word
            B = repeatingKey.get(i);//Letter at the position of the for-cycle in the key word
            C = ' ';//Letter after encoding
            D = 0;//Position of Key-Letter in alphabet
            E = 0;//Conversion from letter to number for better movement in the grid
            D = search(B);//Usage explained at definition
            E = search(A);
            E += D;//Set the number corresponding to the Text's letter plus the amount of circulation in the grid at the Key's letter
            if (E > 25) {//If out of bounds, wrap around
                E = E - 25;
            }
            C = alphabet[E];//Set the output letter to the letter at the position of moved number E in the alphabet
            output += C;//Add the letter to the output word
        }
        System.out.println(output);//Print the output word
    }

    public static int search(char letter) {//Gives me the position of letter requested in the Alphabet
        int position = 0;
        for (int i = 0; i < alphabet.length; i++) {//Goes through the alphabet list
            position = 0;
            if (alphabet[i] == letter) {//When the letter searched for is identical to the letter at the position in the alphabet, we can save into the int and break
                position = i;
                break;
            }
        }
        return position;//Here we return the position of the letter that was searched
    }

    public static String getInput(String use) {//Get stuff from the User
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String userKey = "";
        if (use.equals("input")) {//When we want the Input we call this
            System.out.println("Bitte geben Sie ihren Text ein!");
            for (; ; ) {
                userInput = "";//Here we reset the input String, so nothing can add up and cause pain for me trying to find the problem
                userInput = scanner.nextLine();//Set the input to what is typed in next
                userInput = userInput.toUpperCase();//For easier use we convert everything to upperCase
                if (!Pattern.matches("[a-zA-Z]+", userInput)) {//If the Text contains anything else than letters it is unusable, when we rely on a letters only grid
                    System.out.println("Ich bin leider nicht in der Lage irgendetwas abseits von Buchstaben zu verstehen... Bitte versuchen Sie es erneut!");
                } else {//Everything should work with this
                    System.out.println("Pure Buchstaben");
                    break;
                }
            }
            userInputLength = userInput.length();//Usage explained at definition
            return userInput;
        } else if (use.equals("key")) {//When we want the Key we call this. Everything is basically the same, except for the outputs in the console
            System.out.println("Bitte geben Sie ihren Schlüssel ein!");
            for (; ; ) {
                userKey = "";
                userKey = scanner.nextLine();
                userKey = userKey.toUpperCase(Locale.ROOT);
                if (!Pattern.matches("[a-zA-Z]+", userKey)) {
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
        } else {//If none of the two programmed use-cases is called, we will return an Error, which will never happen, but if this does not exist the IDE does not like me
            return "ERROR";
        }
    }
}
