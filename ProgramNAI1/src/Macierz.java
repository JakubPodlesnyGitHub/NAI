public class Macierz {
    private int[][] macierz1;
    private int[][] macierz2;

    public Macierz(int[][] macierz1, int[][] macierz2) {
        this.macierz1 = macierz1;
        this.macierz2 = macierz2;
    }

    public void dodawanie() {
        int[][] macierzwynikowa = new int[macierz1.length][macierz1.length];
        if (macierz1.length == macierz2.length) {
            for (int i = 0; i < macierz1.length; i++) {
                for (int j = 0; j < macierz1[i].length; j++) {
                    macierzwynikowa[i][j] = macierz1[i][j] + macierz2[i][j];
                    System.out.print(macierzwynikowa[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.err.println("Nie Mozna Dodawac");
        }
    }

    public void odejmowanie() {
        int[][] macierzwynikowa = new int[macierz1.length][macierz1.length];
        if (macierz1.length == macierz2.length) {
            for (int i = 0; i < macierz1.length; i++) {
                for (int j = 0; j < macierz1[i].length; j++) {
                    macierzwynikowa[i][j] = macierz1[i][j] - macierz2[i][j];
                    System.out.print(macierzwynikowa[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.err.println("Nie Mozna Odejmowac");
        }
    }

    public void mnozenieSkalar(int skal) {
        int[][] macierzwynikowa = new int[macierz1.length][macierz1.length];
        for (int i = 0; i < macierz1.length; i++) {
            for (int j = 0; j < macierz2.length; j++) {
                macierzwynikowa[i][j] = macierz1[i][j] * skal;
                System.out.print(macierzwynikowa[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void mnozenie() {
        if (macierz2.length == macierz1[0].length) {
            int[][] macierzwynikowa = new int[macierz1.length][macierz2[0].length];
            int sum = 0;
            for (int i = 0; i < macierz1.length; i++) {
                for (int j = 0; j < macierz2[0].length; j++) {
                    for (int k = 0; k < macierz2.length; k++) {
                        macierzwynikowa[i][j] = macierzwynikowa[i][j] + macierz1[i][k] * macierz2[k][j];
                    }
                    System.out.print(macierzwynikowa[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.err.println("Nie mozna mnozyc");
        }
    }
}
