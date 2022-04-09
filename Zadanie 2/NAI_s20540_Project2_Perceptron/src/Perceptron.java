import java.util.List;

public class Perceptron {
    private double[] weights;
    private double treshhold;
    private int dimension;
    private double learingRate;
    private double t;

    public Perceptron(int dimension, double t) {
        this.t = t;
        this.dimension = dimension;
        //------------------------------
        weights = generateWeights();
        treshhold = 0;
        learingRate = 0.01;
    }

    private double[] generateWeights() {
        double[] tabWightsTMP = new double[dimension];
        for (int i = 0; i < tabWightsTMP.length; i++) {
            tabWightsTMP[i] = Math.random() * 4 + 2;
        }
        return tabWightsTMP;
    }

    public void learning(List<Data> flowersSet) {
        double mistakesProcent = 1;
        System.out.println("Wagi poczatkowe: ");
        for (int i = 0; i < weights.length ; i++) {
            System.out.print(weights[i] + "  ");
        }
        System.out.println();
        while (mistakesProcent > t) {
            int mistakes = 0;
            for (int i = 0; i < flowersSet.size(); i++) {
                double decision = checkScalarProduct(countScalarProduct(flowersSet.get(i).getValueTab()));
                changeWeightsDelta(flowersSet.get(i), decision);
                displyDataTren(decision,flowersSet.get(i));
                if (decision != flowersSet.get(i).getLabel()) {
                    mistakes++;
                }
            }
            System.out.println("TEST");
            System.out.println("T: " + t);
            System.out.println("Liczba bledow: " + mistakes);
            mistakesProcent = mistakes / (double) (flowersSet.size());
            System.out.println("Procentowy blad: " + mistakesProcent);
        }
    }

    public double checkOneVector(Data dataVector) {
        return checkScalarProduct(countScalarProduct(dataVector.getValueTab()));
    }

    private void displyDataTren(double decision, Data oneFlower) {
        System.out.println("Wektor z pliku: " + oneFlower + " Decyzja: " + decision);
        for (int i = 0; i < weights.length; i++) {
            System.out.print(weights[i] + "   ");
        }
        System.out.println("Treshold: " + treshhold);
        System.out.println();
    }

    private double countScalarProduct(double[] values) {
        double scalarProduct = 0;

        for (int i = 0; i < values.length; i++) {
            scalarProduct += weights[i] * values[i];
        }
        scalarProduct += treshhold;
        return scalarProduct;
    }

    private int checkScalarProduct(double scalarProduct) {
        if (scalarProduct >= 0) {
            return 1;
        } else {
            return -1;
        }
    }

    private void changeWeightsDelta(Data data, double decision) {
        for (int j = 0; j < weights.length; j++) {
            weights[j] = weights[j] + (data.getLabel() - decision) * learingRate * data.getValueTab()[j];
        }
        treshhold = treshhold - (data.getLabel() - decision) * learingRate;
    }

}
