import java.util.List;

public class Wektor {
    private List<Double> vectorList;

    public Wektor(List<Double> vectorList) {
        this.vectorList = vectorList;
    }

    public double dlugosc(){
        double length = 0;
        for (Double i: vectorList) {
            length += Math.sqrt(Math.pow(i,2));
        }
        return length;
    }

    public void wektorJednostkowy(){
        double l = dlugosc();
        for (Double i : vectorList) {
            double w = i/l;
            System.out.print(w + " ");
        }
    }

    public void skalar(int stala){
        for (Double i : vectorList) {
            System.out.print(i * stala + " ");
        }
    }

}
