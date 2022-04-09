import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Double> list = new ArrayList<>();
        list.add(5.0);
        list.add(2.0);
        list.add(1.0);
        list.add(3.0);
        list.add(4.0);
        Wektor wektor = new Wektor(list);
        System.out.println(wektor.dlugosc());
        wektor.skalar(2);
        System.out.println();
        wektor.wektorJednostkowy();
        //--------------------------
        System.out.println("\n--------------------------");
        int[][] a = {{1, 2, 3}, {2, 1, 3}, {3, 3, 3}};
        int[][] b = {{1, 2, 3}, {2, 1, 3}, {3, 3, 3}};
        Macierz macierz = new Macierz(a,b);
        macierz.dodawanie();
        macierz.odejmowanie();
        macierz.mnozenieSkalar(3);
        macierz.mnozenie();
    }
}
