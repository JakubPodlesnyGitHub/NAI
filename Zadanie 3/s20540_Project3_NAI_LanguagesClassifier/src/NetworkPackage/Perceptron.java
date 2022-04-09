package NetworkPackage;

public class Perceptron {
    private double[] weights;
    private double treshhold;
    private int dimension;
    private double learningRate;
    private int numberOfPerceptron;

    public Perceptron(int dimension, int numberOfPerceptron) {
        this.dimension = dimension;
        this.numberOfPerceptron = numberOfPerceptron;
        weights = generateWeights();
        learningRate = 0.5;
        treshhold = 0.5;
    }

    public void oneLineLearning(double[] values, int corDecision) {
        double scalarP = countScalarProduct(values);
        int decision = checkScalarProduct(scalarP);
        if (decision != corDecision) {
            changeWeightsDelta(values, decision, corDecision);
            displayValuesWeights(values);
        }
    }

    private void displayValuesWeights(double[] percentOfLetters) {
        System.out.println("Numer Perceptrona: " + numberOfPerceptron);
        StringBuilder valuesString = new StringBuilder("< ");
        StringBuilder weightsString = new StringBuilder("< ");
        for (int i = 0; i < percentOfLetters.length ; i++) {
            valuesString.append(percentOfLetters[i]).append(" , ");
            weightsString.append(weights[i]).append(" , ");
        }
        valuesString = new StringBuilder(valuesString.substring(0, valuesString.length() - 2) + " >");
        weightsString = new StringBuilder(weightsString.substring(0, weightsString.length() - 2) + " >");
        System.out.println("Wartosci");
        System.out.println(valuesString);
        System.out.println("Wagi");
        System.out.println(weightsString);
    }

    private double[] generateWeights() {
        double[] tabWightsTMP = new double[dimension];
        for (int i = 0; i < tabWightsTMP.length; i++) {
            tabWightsTMP[i] = Math.random() * 4 + 2;
        }
        return tabWightsTMP;
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
            return 0;
        }
    }

    public int checkOneVector(double[] values) {
        return checkScalarProduct(countScalarProduct(values));
    }

    private void changeWeightsDelta(double[] values, int decision, int corDecision) {
        for (int j = 0; j < weights.length; j++) {
            weights[j] = weights[j] + (corDecision - decision) * learningRate * values[j];
        }
        treshhold = treshhold + (corDecision - decision) * learningRate;
    }

}
