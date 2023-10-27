package utils;

public class MiscUtils {
    public static void writeOnConsole(String Message) {
        // ANSI escape codes for text formatting
        String RESET = "\u001B[0m";
        String RED_BOLD = "\u001B[1;31m";

        // Your standout message
        System.out.println(RED_BOLD + Message + RESET);

    }

    public static void writeInfo(String info) {
        // ANSI escape codes for text formatting
        String RESET = "\u001B[0m";
        String GREEN_BACKGROUND = "\u001B[42m";
        String WHITE_BOLD = "\u001B[1;97m";

        // Your standout message
        System.out.println(GREEN_BACKGROUND + WHITE_BOLD + info + RESET);
    }

}
