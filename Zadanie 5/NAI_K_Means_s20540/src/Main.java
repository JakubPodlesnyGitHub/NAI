import java.util.Scanner;

public class Main {
    private static int k = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj k: ");
        k = scanner.nextInt();
        K_Means k_means = new K_Means("bankloans1.csv", k);
        userChoice(k_means, scanner);
    }

    public static void displayMenu() {
        System.out.println("//////////////////////////");
        System.out.println("MENU");
        System.out.println("Wybierz opcje");
        System.out.println("1 Wprowadz wlasne k");
        System.out.println("2 Uruchom algorytm k-means");
        System.out.println("0 Wyjdz");
        System.out.println("//////////////////////////");
    }

    public static void userChoice(K_Means k_means, Scanner scanner) {
        System.out.println("Wprowadz liczbe: ");
        int choice;
        do {
            displayMenu();
            switch ((choice = scanner.nextInt())) {
                case 1:
                    System.out.println("Podaj k: ");
                    k = scanner.nextInt();
                    k_means.setK(k);
                    break;
                case 2:
                    k_means.updateCentroids();
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
