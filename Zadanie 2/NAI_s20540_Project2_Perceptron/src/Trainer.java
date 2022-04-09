import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private List<Data> trenDataList;
    private List<Data> testDataList;
    Perceptron perceptron;
    private int dimension;

    public Trainer(String trenFile, String testFile) throws IOException {
        setDimensionFromFile("TrenSetIris.csv");
        perceptron = new Perceptron(dimension, 0.01);
        //-----------------------------------------------------
        try {
            trenDataList = readFile(trenFile);
            changeOrder(trenDataList);
            perceptron.learning(trenDataList);
            testDataList = readFile(testFile);
            changeOrder(testDataList);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public double checkVector(Data data) {
        return perceptron.checkOneVector(data);
    }

    public void checkTestFile(){
        for (int i = 0; i < testDataList.size(); i++) {
            System.out.println("Zakwalifikowano wektor: " + testDataList.get(i) + " do " + checkVector(testDataList.get(i)));
        }
    }

    public void changeOrder(List<Data> list) {
        for (int i = 0; i < 1000; i++) {
            int element = (int) (Math.random() * list.size());
            Data elementTmp = list.remove(element);
            list.add(elementTmp);
        }
    }

    private List<Data> readFile(String fileName) throws IOException {
        List<Data> tmp = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String txtLine;
        while ((txtLine = bufferedReader.readLine()) != null) {
            String[] tab = txtLine.split(",");
            tmp.add(new Data(createVectorTab(tab), tab[tab.length - 1]));
        }
        return tmp;
    }

    private void setDimensionFromFile(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String[] tabTMP = bufferedReader.readLine().split(",");
        dimension = tabTMP.length - 2;
        bufferedReader.close();
    }

    private double[] createVectorTab(String[] tab) {
        double[] tabTMP = new double[dimension];
        for (int i = 1; i < tab.length - 1; i++) {
            tabTMP[i-1] = Double.parseDouble(tab[i]);
        }
        return tabTMP;
    }

    public void displayList() {
        for (int i = 0; i < trenDataList.size(); i++) {
            System.out.println(trenDataList.get(i));
        }
    }

    public int getDimension() {
        return dimension;
    }

}
