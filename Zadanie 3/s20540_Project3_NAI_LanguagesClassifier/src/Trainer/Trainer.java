package Trainer;

import DataPreparationPackage.Data;
import DataPreparationPackage.VectorPreparation;
import DataPreparationPackage.LanguageMatching;
import NetworkPackage.NeuronNetwork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class Trainer {
    private NeuronNetwork network;
    private VectorPreparation vectorTrenPreparation;
    private VectorPreparation vectorTestPreparation;
    private LanguageMatching languageMatching;
    private Set<String> languages;
    private double t;
    private int numberOfTests;

    public Trainer(String fileTrenName, String fileTestName, double t) throws IOException {
        this.t = t;
        vectorTrenPreparation = new VectorPreparation(fileTrenName);
        vectorTestPreparation = new VectorPreparation(fileTestName);
        languages = vectorTrenPreparation.getDiffLanguages();
        languageMatching = new LanguageMatching(new ArrayList<>(languages));
        //////////////////////////////////////////////////
        network = new NeuronNetwork(languages.size());
        trainFile();
    }

    private int[] getCorrectDecision(Data data) {
        String langFromFile = data.getLanguage();
        return languageMatching.getVectorLanguage(langFromFile);
    }

    //PROCES UCZENIA
    //pojedyncze przejscie po danych
    private int oneDataPass() {
        int mistakes = 0;
        for (int i = 0; i < vectorTrenPreparation.getProbabilityLetterList().size(); i++) {
            int[] systemRecognitionTAB = network.recognitionLanguageFromVector(vectorTrenPreparation.getProbabilityLetterList().get(i));
            int[] correctDecisionTAB = getCorrectDecision(vectorTrenPreparation.getDataFromFile().get(i));
            if (!checkAnswers(systemRecognitionTAB, correctDecisionTAB)) {
                mistakes++;
            }
            network.learningStep(vectorTrenPreparation.getProbabilityLetterList().get(i), getCorrectDecision(vectorTrenPreparation.getDataFromFile().get(i)));
        }
        return mistakes;
    }

    //przechodzenie po danych az do uzyskania odpowiedniego % blednych odpowiedzi
    private void trainFile() {
        double mistakesPercent = 1;
        int i = 1;
        while (mistakesPercent > t) {
            int mistakes = oneDataPass();
            mistakesPercent = mistakes / (double) vectorTrenPreparation.getProbabilityLetterList().size();
            displayDataWhileLearning(mistakes, mistakesPercent, i);
            i++;
        }
        numberOfTests = i;
    }

    public void testFile() {
        int correct = 0;
        for (int i = 0; i < vectorTestPreparation.getDataFromFile().size(); i++) {
            String qualifiedLang = languageMatching.getNameOfLanguage(network.recognitionLanguageFromVector(vectorTestPreparation.getProbabilityLetterList().get(i)));
            if (qualifiedLang.equals(vectorTestPreparation.getDataFromFile().get(i).getLanguage()))
                correct++;
            displayTestVector(vectorTestPreparation.getProbabilityLetterList().get(i), qualifiedLang, vectorTestPreparation.getDataFromFile().get(i).getLanguage());
        }
        displayCorrectPercent(correct);
    }

    private void displayCorrectPercent(int correct) {
        System.out.println("Liczba poprawnie zakwalifikowanych jezykow z pliku testowego: " + correct);
        System.out.println("Procent porpawnych odpowiedzi: " + correct / (double) vectorTestPreparation.getDataFromFile().size());
    }

    private void displayTestVector(double[] testVector, String qualifiedLang, String correctLang) {
        StringBuilder vectorString = new StringBuilder("< ");
        for (double v : testVector) {
            vectorString.append(v).append(" , ");
        }
        vectorString = new StringBuilder(vectorString.substring(0, vectorString.length() - 2));
        vectorString.append(" >  Zakwalifkowany jezyk: ").append(qualifiedLang).append(" Poprawny jezyk: ").append(correctLang);
        System.out.println(vectorString);
    }

    private void displayDataWhileLearning(int mistakes, double mistakesPercent, int testNumber) {
        System.out.println("Test: " + testNumber);
        System.out.println("Ilosc Bledow: " + mistakes);
        System.out.println("Blad procentowy: " + mistakesPercent);
    }

    public boolean checkAnswers(int[] systemRecognitionTAB, int[] correctDecisionTAB) {
        for (int i = 0; i < systemRecognitionTAB.length; i++) {
            if (systemRecognitionTAB[i] != correctDecisionTAB[i])
                return false;
        }
        return true;
    }

    public void displayLanguages() {
        System.out.println(languageMatching);
    }

    public int getNumberOfTests() {
        return numberOfTests - 1;
    }

    public double getT() {
        return t;
    }

    public String checkVectorFromUser(double[] values) {
        String qualifiedLang = languageMatching.getNameOfLanguage(network.recognitionLanguageFromVector(values));
        String vectorUserString = "< ";
        for (double value : values) {
            vectorUserString += value + " , ";
        }
        vectorUserString = vectorUserString.substring(0, vectorUserString.length() - 2) + " >" + " Zakwalifikowany jezyk: " + qualifiedLang;
        return vectorUserString;
    }

    public String matchLanguageToTextFromWindow(String text, String correctLanguage) {
        vectorTestPreparation.setTextFromWindow(text);
        String qualifiedLang = languageMatching.getNameOfLanguage(network.recognitionLanguageFromVector(vectorTestPreparation.getProbabilityLetterListFromWindow()));
        displayTestVector(vectorTestPreparation.getProbabilityLetterListFromWindow(), qualifiedLang, correctLanguage);
        return "Zakwalifikowany jezyk dla podanego tesktu: " + qualifiedLang + " Prawidlowa odp: " + correctLanguage;
    }
}
