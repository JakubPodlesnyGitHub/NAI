import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestSetData implements NormalizationInterFace {
    private List<Data> testDataList;
    private  double [] tabArguments;

    public TestSetData(double[] tabArguments) throws IOException {
        this.tabArguments = tabArguments;
        testDataList = new ArrayList<>();
        readTestFile("TestSetIris.csv");
    }

    public Data getElement(int indeks) {
        return testDataList.get(indeks);
    }

    public int getListSize(){
        return testDataList.size();
    }

    public Data getNormalizedVector(Data data) {
        this.normalizedVector(data,tabArguments);
        return data;
    }

    private void readTestFile(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
        String textLine;
        String[] dataTMP;
        while ((textLine = bufferedReader.readLine()) != null) {
            dataTMP = textLine.split(",");
            testDataList.add(new Data(Double.parseDouble(dataTMP[1]), Double.parseDouble(dataTMP[2]), Double.parseDouble(dataTMP[3]), Double.parseDouble(dataTMP[4]), dataTMP[5]));
        }
        this.normalization(tabArguments,testDataList);
    }

    public void accuracy() {
        int correctCounter = 0;
        int inCorrectCounter = 0;
        for (int i = 0; i < testDataList.size(); i++) {
            if (testDataList.get(i).getClassifiedName().equals(testDataList.get(i).getName())) {
                correctCounter++;
            } else {
                inCorrectCounter++;
            }
        }
        System.out.println("Licznik z poprawnym dopasowaniem: " + correctCounter);
        System.out.println("Licznik z niepoprawnym dopasowaniem: " + inCorrectCounter);
        System.out.println(((double) correctCounter / (double) testDataList.size()) * 100 + "%");
    }
}
