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

    public static void encode(String key, String input) {
        System.out.println(key + " " + input);
        if(key.length() != userInputLength){//wir versuchen den key lang genug zu machen schmerzen!
            StringBuilder builder = new StringBuilder(input.length());
            while (builder.length() < input.length()) {
                builder.append(key.substring(0, Math.min(key.length(), builder.length() - input.length())));
            }
            String result = builder.toString();
            System.out.println(result);
        }
    }

    public static String getInput(String use) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String userKey = "";
        if (use.equals("input")) {
            System.out.println("Bitte geben Sie ihren Text ein!");
            for (; ; ) {
                userInput = "";
                userInput = scanner.nextLine();
                if (!Pattern.matches("[a-zA-Z]+", userInput)) {
                    System.out.println("Ich bin leider nicht in der Lage irgendetwas ab von Buchstaben zu verstehen... Bitte versuchen Sie es erneut!");
                } else {
                    System.out.println("Pure Buchstaben");
                    break;
                }
            }
            userInputLength = userInput.length();
            return userInput;
        } else if (use.equals("key")) {
            System.out.println("Bitte geben Sie ihren Schlüssel ein!");
            for (; ; ) {
                userKey = "";
                userKey = scanner.nextLine();
                if (!Pattern.matches("[a-zA-Z]+", userKey)) {
                    System.out.println("Ich bin leider nicht in der Lage irgendetwas ab von Buchstaben zu verstehen... Bitte versuchen Sie es erneut!");
                } else {
                    if(userKey.length() > userInputLength){
                        System.out.println("Ihr Key ist länger als ihr Input, das funktioniert nicht. Bitte versuchen Sie es erneut!");
                    }else {
                        System.out.println("Pure Buchstaben");
                        break;
                    }
                }
            }
            return userKey;
        } else {
            return "ERROR";
        }
    }
}
