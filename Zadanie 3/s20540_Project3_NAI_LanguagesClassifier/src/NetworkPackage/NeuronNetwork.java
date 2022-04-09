package NetworkPackage;

public class NeuronNetwork {
    private int numberOfLanguages;
    private Perceptron[] perceptrons;
    private int dimension;

    public NeuronNetwork(int numberOfLanguages) {
        this.numberOfLanguages = numberOfLanguages;
        perceptrons = new Perceptron[numberOfLanguages];
        dimension = 'z' - 'a' + 1;
        fillPerceptronTab();
    }

    private void fillPerceptronTab() {
        for (int i = 0; i < perceptrons.length; i++) {
            perceptrons[i] = new Perceptron(dimension,i);
        }
    }

    public void learningStep(double [] percentOfLetters,int [] langVector) {
        for (int i = 0; i < perceptrons.length; i++) {
            perceptrons[i].oneLineLearning(percentOfLetters,langVector[i]);
        }
    }

    public int[] recognitionLanguageFromVector(double [] percentOfLetters){
        int [] tab = new int[numberOfLanguages];
        for (int i = 0; i < numberOfLanguages; i++) {
            tab[i] = perceptrons[i].checkOneVector(percentOfLetters);
        }
        return tab;
    }
}
