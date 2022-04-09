import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Trainer trainer = new Trainer("TrenSetIris.csv", "TestSetIris.csv");
        userChoice(trainer);
    }

    private static void displayMenu() {
        System.out.println("//////////////////////////");
        System.out.println("MENU");
        System.out.println("Wybierz opcje");
        System.out.println("1 Wprowadz dane i wlasny wektor");
        System.out.println("2 Zaklasyfikuj dane z pliku testowego");
        System.out.println("0 Wyjdz");
        System.out.println("//////////////////////////");
    }

    public static Data enterVector(Scanner scanner, Trainer trainer) {
        int counter = 0;
        double[] vectorTab = new double[trainer.getDimension()];
        while (counter < vectorTab.length) {
            System.out.println("Prosze podac parametr: ");
            vectorTab[counter] = scanner.nextDouble();
            counter++;
        }
        return new Data(vectorTab);
    }


    public static void userChoice(Trainer trainer) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            switch ((choice = scanner.nextInt())) {
                case 1:
                    Data vectorFromUser = enterVector(scanner, trainer);
                    System.out.println("Zakwalifikowanie wektora: " + trainer.checkVector(vectorFromUser));
                    break;
                case 2:
                    System.out.println("/////////DANE TESTOWE//////////");
                    trainer.checkTestFile();
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
