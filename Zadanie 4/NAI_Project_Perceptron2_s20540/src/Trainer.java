import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private List<Data> dataTrenList;
    private List<Data> dataTestList;
    private Perceptron perceptron;
    private int dimension;
    private double t;
    private int[] measuresTab;
    private double precision;
    private double recall;
    private double fmeasure;

    public Trainer(String trenFileName, String testFileName, double t) throws IOException {
        this.t = t;
        setDimensionFromFile(trenFileName);
        dataTrenList = readFile(trenFileName);
        //changeOrder(dataTrenList);
        dataTestList = readFile(testFileName);
        perceptron = new Perceptron(dimension);
        learning();
    }

    //preparation
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
        if (fileName.startsWith("tren"))
            bufferedReader.readLine();
        String txtLine;
        while ((txtLine = bufferedReader.readLine()) != null) {
            String[] tab = txtLine.split(",");
            tmp.add(new Data(createVectorTab(tab), Integer.parseInt(tab[tab.length - 1])));
        }
        return tmp;
    }

    private void setDimensionFromFile(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        bufferedReader.readLine();
        String[] tabTMP = bufferedReader.readLine().split(",");
        dimension = tabTMP.length - 2;
        bufferedReader.close();
    }

    private double[] createVectorTab(String[] tab) {
        double[] tabTMP = new double[dimension];
        for (int i = 1; i < tab.length - 1; i++) {
            tabTMP[i - 1] = Double.parseDouble(tab[i]);
        }
        return tabTMP;
    }

    //learning
    public void learning() {
        double accuracyPercent = 0;
        //perceptron.displayStartWeights();
        while (accuracyPercent < t) {
            int correctTEST = 0;
            onePassTrenFile();
            for (Data testDataElement : dataTestList) {
                int val = perceptron.checkOneVector(testDataElement);
                if (testDataElement.getLabel() == val) {
                    correctTEST++;
                }
            }
            System.out.println("TEST");
            System.out.println("T: " + t);
            System.out.println("Liczba poprawnie zakwalifikowanych: " + correctTEST);
            accuracyPercent = ((double) correctTEST / (double) dataTestList.size());
            displayAcc(accuracyPercent, "Test");
            System.out.println("//////////////////////////////////////////////////");
        }
    }

    private void onePassTrenFile() {
        double accAfterTraining;
        int correctTREN = 0;
        for (Data trenElement : dataTrenList) {
            correctTREN += perceptron.learningStep(trenElement);
        }
        accAfterTraining = (double) correctTREN / (double) dataTrenList.size();
        displayAcc(accAfterTraining, "Tren");
    }

    private void displayAcc(double accAfterTraining, String file) {
        if (file.equals("Tren"))
            System.out.println("Acc Tren: " + accAfterTraining);
        else
            System.out.println("Acc Test: " + accAfterTraining);
    }

    public int checkVectorFromUser(Data vectorFromUser) {
        return perceptron.checkOneVector(vectorFromUser);
    }

    public void checkTestFile() {
        int countBenignRecall = 0;  //pozytywne i faktycznie zakwalifikowane przypadki
        int countBenign = 0; //faktyczne pozytywne przypadki
        int countMalignantRecall = 0;//negatywne i faktycznie negatywne przypadki
        int countMalignant = 0; //faktyczne negatywne przypadki
        int classifiedAsBenign = 0; // pozytywna klasyfikacja
        int classifiedAsMalignant = 0; // negatywna klasyfikacja
        measuresTab = new int[]{countBenign, countMalignant, countBenignRecall, countMalignantRecall, classifiedAsBenign, classifiedAsMalignant};
        for (Data testDataElement : dataTestList) {
            int val = perceptron.checkOneVector(testDataElement);
            if (val == 1) measuresTab[4]++;
            else measuresTab[5]++;
            if (val == 1 && testDataElement.getLabel() == 1) measuresTab[2]++;
            else if (testDataElement.getLabel() == 1) measuresTab[0]++;
            if (val == -1 && testDataElement.getLabel() == -1) measuresTab[3]++;
            else if (testDataElement.getLabel() == -1) measuresTab[1]++;
        }
        displayLearnerMeasures(measuresTab);
    }

    private void displayLearnerMeasures(int[] measuresTab) {
        precision = (double) measuresTab[2] / (double) measuresTab[4];
        System.out.println("P: " + precision);
        recall = (double) measuresTab[2] / (double) measuresTab[0];
        System.out.println("R: " + recall);
        fmeasure = 2 * precision * recall / (precision + recall);
        System.out.println("F: " + fmeasure);
    }

    public int getDimension() {
        return dimension;
    }

    public int[] getMeasuresTab() {
        return measuresTab;
    }

    public double getPrecision() {
        return precision;
    }

    public double getRecall() {
        return recall;
    }

    public double getFmeasure() {
        return fmeasure;
    }
}
