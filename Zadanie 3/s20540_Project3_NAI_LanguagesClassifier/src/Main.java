import GUI.View;
import Trainer.Trainer;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Trainer trainer = new Trainer("trenFileLang.txt", "testFileLang.txt", 0.01);
        trainer.displayLanguages();
        userChoice(trainer);
    }

    private static void displayMenu() {
        System.out.println("//////////////////////////");
        System.out.println("MENU");
        System.out.println("Wybierz opcje");
        System.out.println("1 Wprowadz dane i wlasny wektor");
        System.out.println("2 Zaklasyfikuj dane z pliku testowego");
        System.out.println("3 Wprowadz tekst przez GUI");
        System.out.println("0 Wyjdz");
        System.out.println("//////////////////////////");
    }

    public static double [] enterVector(Scanner scanner) {
        int counter = 0;
        double[] vectorTab = new double[26];
        while (counter < vectorTab.length) {
            System.out.println("Prosze podac parametr: ");
            vectorTab[counter] = scanner.nextDouble();
            counter++;
        }
        return vectorTab;
    }


    public static void userChoice(Trainer trainer) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            switch ((choice = scanner.nextInt())) {
                case 1:
                    System.out.println("////////////WPISYWANIE WEKTORA///////////////////");
                    System.out.println(trainer.checkVectorFromUser(enterVector(scanner)));
                    break;
                case 2:
                    System.out.println("////////////DANE TESTOWE///////////////////");
                    trainer.testFile();
                    break;
                case 3:
                    SwingUtilities.invokeLater(() -> {
                        View view = new View(trainer);
                    });
                    break;
                default:
                    if (choice != 0) {
                        System.err.println("!!!Zla liczba!!!");
                    }
                    break;
            }
        } while (choice != 0);
        System.out.println("Dziekuje za skorzystanie z programu");
    }
}

