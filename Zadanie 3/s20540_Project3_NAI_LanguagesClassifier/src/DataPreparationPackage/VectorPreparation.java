package DataPreparationPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class VectorPreparation {
    private List<Data> dataFromFile;
    private List<double[]> probabilityLetterList;
    private String textFromWindow;

    public VectorPreparation(String fileName) throws IOException {
        probabilityLetterList = new ArrayList<>();
        dataFromFile = readDataFromFile(fileName);
        //changeOrder(dataFromFile);
    }

    private List<Data> readDataFromFile(String fileName) throws IOException {
        List<Data> dataListTMP = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String txtLine;
        while ((txtLine = bufferedReader.readLine()) != null) {
            String[] tmpLineTab = txtLine.split("\t");
            List<Character> listOfLineCharacters = cleanData(tmpLineTab[1]).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            dataListTMP.add(new Data(listOfLineCharacters, tmpLineTab[tmpLineTab.length - 1]));
            probabilityLetterList.add(createProbabilityLetterVector(listOfLineCharacters.size(), countLettersInOneLine(listOfLineCharacters)));
        }
        bufferedReader.close();
        return dataListTMP;
    }

    private double[] createProbabilityLetterVector(int lineSize, Map<Character, Integer> numberOfLettersMap) {
        double[] vectorLine = new double['z' - 'a' + 1];
        for (char i = 'a'; i < 'z'; i++) {
            if (numberOfLettersMap.get(i) != null)
                vectorLine[i - 'a'] = (numberOfLettersMap.get(i) / (double) lineSize) * 100;
            else
                vectorLine[i - 'a'] = 0;
        }
        return vectorLine;
    }

    private Map<Character, Integer> countLettersInOneLine(List<Character> line) {
        Map<Character, Integer> numberOfLettersMap = new LinkedHashMap<>();
        for (Character character : line) {
            if (!numberOfLettersMap.containsKey(character)) {
                numberOfLettersMap.put(character, 1);
            } else {
                numberOfLettersMap.put(character, numberOfLettersMap.get(character) + 1);
            }
        }
        return numberOfLettersMap;
    }

    private String cleanData(String dataString) {
        //interpunkcja
        dataString = dataString.replaceAll("\\p{Punct}", "");
        //spacje
        dataString = dataString.replaceAll("\\s", "");
        //na male
        dataString = dataString.toLowerCase();
        //znaki diaktryczne
        dataString = dataString.replaceAll("[åäöæøüé]*", "");
        return dataString;
    }

    private void changeOrder(List<Data> list) {
        for (int i = 0; i < 1000; i++) {
            int element = (int) (Math.random() * list.size());
            Data elementTmp = list.remove(element);
            list.add(elementTmp);
        }
    }

    //Publiczne metody

    public void displayListOfVectors() {
        for (double[] vector : probabilityLetterList) {
            StringBuilder vectorString = new StringBuilder("< ");
            for (int i = 0; i < vector.length; i++) {
                vectorString.append(vector[i]).append(" , ");
            }
            vectorString = new StringBuilder(vectorString.substring(0, vectorString.length() - 2));
            vectorString.append(" >");
            System.out.println(vectorString);
        }
    }

    public void setTextFromWindow(String textFromWindow) {
        this.textFromWindow = textFromWindow;
    }

    public Set<String> getDiffLanguages() {
        Set<String> languages = new HashSet<>();
        for (Data data : dataFromFile) {
            languages.add(data.getLanguage());
        }
        return languages;
    }

    public List<Data> getDataFromFile() {
        return dataFromFile;
    }

    public double[] getProbabilityLetterListFromWindow() {
        List<Character> listOfLineCharacters = cleanData(textFromWindow).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        return createProbabilityLetterVector(listOfLineCharacters.size(), countLettersInOneLine(listOfLineCharacters));
    }

    public List<double[]> getProbabilityLetterList() {
        return probabilityLetterList;
    }
}