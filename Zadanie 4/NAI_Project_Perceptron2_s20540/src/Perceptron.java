public class Perceptron {
    private double[] weights;
    private double treshhold;
    private int dimension;
    private double learingRate;

    public Perceptron(int dimension) {
        this.dimension = dimension;
        weights = generateWeights();
        treshhold = 0;
        learingRate = 0.1;
    }

    private double[] generateWeights() {
        double[] tabWightsTMP = new double[dimension];
        for (int i = 0; i < tabWightsTMP.length; i++) {
            tabWightsTMP[i] = Math.random() * 4 + 3;
        }
        return tabWightsTMP;
    }

    public int learningStep(Data element) {
        int decision = checkOneVector(element);
        changeWeightsDelta(element, decision);
        //displayValuesWeights(element.getDataTab());
        if(decision == element.getLabel())
            return 1;
        return 0;
    }

    public int checkOneVector(Data data) {
        return checkScalarProduct(countScalarProduct(data.getDataTab()));
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
            weights[j] = weights[j] + (data.getLabel() - decision) * learingRate * data.getDataTab()[j];
        }
        treshhold = treshhold - (data.getLabel() - decision) * learingRate;
    }

    public void displayStartWeights(){
        for (int i = 0; i < weights.length ; i++) {
            System.out.print(weights[i] + "   ");
        }
        System.out.println("////////////////");
    }

    private void displayValuesWeights(double[] values) {
        StringBuilder valuesString = new StringBuilder("< ");
        StringBuilder weightsString = new StringBuilder("< ");
        for (int i = 0; i < values.length; i++) {
            valuesString.append(values[i]).append(" , ");
            weightsString.append(weights[i]).append(" , ");
        }
        valuesString = new StringBuilder(valuesString.substring(0, valuesString.length() - 2) + " >");
        weightsString = new StringBuilder(weightsString.substring(0, weightsString.length() - 2) + " >");
        System.out.println("Wartosci");
        System.out.println(valuesString);
        System.out.println("Wagi");
        System.out.println(weightsString);
    }


}
