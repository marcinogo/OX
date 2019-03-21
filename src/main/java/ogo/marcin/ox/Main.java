package ogo.marcin.ox;

import java.util.Scanner;

/**
 * @author Marcin Ogorzalek
 */
public class Main {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            Input input = new Input(scanner);
            System.out.println(input.getStringInput());
            System.out.println(input.getIntegerInput());
        }
    }
}
