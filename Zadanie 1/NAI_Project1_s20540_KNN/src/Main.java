import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        TrenSetData trenSetData = new TrenSetData();
        TestSetData testSetData = new TestSetData(trenSetData.getParamters(trenSetData.getTrenDataList()));
        userChoice(trenSetData, testSetData);
    }

    public static void displayMenu() {
        System.out.println("//////////////////////////");
        System.out.println("MENU");
        System.out.println("Wybierz opcje");
        System.out.println("1 Wprowadz wlasne dane");
        System.out.println("2 Zaklasyfikuj dane z pliku testowego");
        System.out.println("0 Wyjdz");
        System.out.println("//////////////////////////");
    }

    public static void userChoice(TrenSetData trenData, TestSetData testData) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadz liczbe: ");
        int choice;
        do {
            displayMenu();
            switch ((choice = scanner.nextInt())) {
                case 1:
                    Data vector = testData.getNormalizedVector(enterData(scanner));
                    System.out.println(vector);
                    trenData.countDistance(vector);
                    System.out.println("ODP NA PODSTAWIE DANYCH DLA PODANEGO WEKTORU: " + trenData.classification(trenData.findKElements(eneterK(scanner, trenData.getTrenDataListSize()))));
                    break;
                case 2:
                    int k = eneterK(scanner, trenData.getTrenDataListSize());
                    for (int i = 0; i < testData.getListSize(); i++) {
                        trenData.countDistance(testData.getElement(i));
                        testData.getElement(i).setClassifiedName(trenData.classification(trenData.findKElements(k)));
                        System.out.println(testData.getElement(i) + " ODP NA PODSTAWIE DANYCH: " + testData.getElement(i).getClassifiedName() + " ODP Z PLIKU: " + testData.getElement(i).getName());
                    }
                    testData.accuracy();
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

    public static Data enterData(Scanner scanner) {
        System.out.println("Podaj dlugosc dzialki kwiatu: ");
        double sepalLength = scanner.nextDouble();
        System.out.println("Podaj szerokosc dzialki kwiatu: ");
        double sepalWidth = scanner.nextDouble();
        System.out.println("Podaj dlugosc platka: ");
        double pentalLength = scanner.nextDouble();
        System.out.println("Podaj szerokkosc plataka: ");
        double pentalWidth = scanner.nextDouble();
        return new Data(sepalLength, sepalWidth, pentalLength, pentalWidth, null);
    }

    public static int eneterK(Scanner scanner, int trainSetSize) {
        int k = 0;
        while (k <= 0 || k > trainSetSize) {
            System.out.println("Podaj k: ");
            k = scanner.nextInt();
        }
        return k;
    }
}
