import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateListOfVectors {
    private List<Data> listOfVectors;

    public CreateListOfVectors(String fileName) {
        listOfVectors = readFile(fileName);
    }

    private List<Data> readFile(String fileName) {
        List<Data> tmpList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
            String txtLine;
            String[] dataTMP;
            bufferedReader.readLine();
            while ((txtLine = bufferedReader.readLine()) != null) {
                dataTMP = txtLine.substring(0, txtLine.length() - 1).split(",");
                tmpList.add(new Data(createVectorTab(dataTMP)));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return tmpList;
    }

    private double[] createVectorTab(String[] tab) {
        double[] tabVal = new double[tab.length];
        for (int i = 0; i < tabVal.length; i++) {
            tabVal[i] = Double.parseDouble(tab[i]);
        }
        return tabVal;
    }

    public List<Data> getListOfVectors() {
        return listOfVectors;
    }
}
