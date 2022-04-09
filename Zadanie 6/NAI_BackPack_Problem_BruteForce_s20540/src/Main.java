import java.util.Scanner;

public class Main {
    private static CreateObjectList createObjectList;
    private static BackPack backPack;
    private static int capacity;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        createObjectList = new CreateObjectList("knapsack.csv");
        createObjectList.displayParamteres();
        System.out.println("Enter BackPack Capacity");
        capacity = scanner.nextInt();
        backPack = new BackPack(capacity, createObjectList.getPrices(), createObjectList.getWeights());
        backPack.makeAlgorithm();
        userChoice(scanner);
    }

    public static void displayMenu() {
        System.out.println("//////////////////////////");
        System.out.println("MENU");
        System.out.println("Wybierz opcje");
        System.out.println("1 Change BackPack Capacity");
        System.out.println("0 Exit");
        System.out.println("//////////////////////////");
    }

    public static void userChoice(Scanner scanner) {
        System.out.println("Enter A Number: ");
        int choice;
        do {
            displayMenu();
            switch ((choice = scanner.nextInt())) {
                case 1:
                    System.out.println("Enter BackPack Capacity: ");
                    capacity = scanner.nextInt();
                    backPack.setCapacity(capacity);
                    backPack.makeAlgorithm();
                    break;
                default:
                    if (choice != 0) {
                        System.err.println("!!!Wrong Number!!!");
                    }
                    break;
            }
        } while (choice != 0);
        System.out.println("Thank You And Goodbye");
    }
}
