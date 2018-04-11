package warmup1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Gábor Hargitai
 */
public class Warmup1 {

    public static void main(String[] args) {
        // EX.1
        printout("Hello World");
        // EX.2
        printout(String.valueOf(argumentCount(args)));
        // EX.3.a
        printout(argumentConcatenate(args, " "));
        // EX.3.b
        argumentList(args, false);
        // EX.3.c
        argumentList(args, true);
        // EX.3.d
        argumentLengths(args);
        // EX.3.e
        printout(argumentSum(args));
        // EX.3.f
        listEvenArguments(args);
        // EX.3.g & .h
        sortArguments(args);
        // EX.3.i
        listDuplicates(args);
        
    }
    
    public static void printout(String text) {
        System.out.println(text);
    }
    
    public static int argumentCount(String[] args) {
        int size = 0;
        for (String arg : args) {
            size += 1;
        }
        return size;
    }
    
    public static String argumentConcatenate(String[] args, String separator) {
        String response = "";
        for (String arg : args) {
            response += arg + separator;
        }
        return response;
    }
    
    public static void argumentList(String[] args, Boolean reverse) {
        if (!reverse) {
            for (int i = 0; i < args.length; i++) {
                System.out.println(i + ". " + args[i]);
            }
        } else {
            int j = 0;
            for (int i = args.length - 1; i >= 0; i--) {
                System.out.println(j + ". " + args[i]);
                j += 1;
            }
        }
    }
    
    public static void argumentLengths(String[] args) {
        for (String arg : args) {
            printout(String.valueOf(arg.length()));
        }
    }
    
    public static String argumentSum(String[] args) {
        int sum = 0;
        for (String arg : args) {
            if (isNumberic(arg)) {
                sum += Integer.valueOf(arg);
            }
        }
        return String.valueOf(sum);
    }
    
    public static void listEvenArguments(String[] args) {
        for (String arg : args) {
            if (isNumberic(arg) && isEven(arg)) {
                printout(arg);
            }
        }
    }
    
    public static void sortArguments(String[] args) {
        Arrays.sort(args);
        for (String arg : args) {
            printout(arg);
        }
    }
    
    public static void listDuplicates(String[] args) {
        List<String> seen = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            int occurance = 0;
            for (int j = i; j < args.length; j++) {
                if (args[i].equals(args[j])) {
                    occurance += 1;
                }
            }
            if (occurance > 1 && !seen.contains(args[i])) {
                seen.add(args[i]);
                printout(args[i]);
            }
        }
    }
    
    /* HELPER METHODS */
    
    public static Boolean isNumberic(String str) {
        return str.chars().allMatch( Character::isDigit );
    }
    
    public static Boolean isEven(String a) {
        return Integer.valueOf(a)%2 == 0;
    }
}
