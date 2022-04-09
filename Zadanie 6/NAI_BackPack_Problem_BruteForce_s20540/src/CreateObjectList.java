import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CreateObjectList {
    private List<Integer> prices;
    private List<Integer> weights;
    private int wSum;
    private int pSum;

    public CreateObjectList(String fileName) {
        prices = new ArrayList<>();
        weights = new ArrayList<>();
        wSum = 0;
        pSum = 0;
        readData(fileName);
    }

    private void readData(String fileName) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            bufferedReader.readLine();
            String txtLine;
            while ((txtLine = bufferedReader.readLine()) != null) {
                String[] dataFromFile = txtLine.split(",");
                prices.add(Integer.parseInt(dataFromFile[0]));
                weights.add(Integer.parseInt(dataFromFile[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bufferedReader != null;
                bufferedReader.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void displayParamteres() {
        prices.forEach(p -> pSum += p);
        weights.forEach(w -> wSum += w);
        System.out.println("Weights sum: " + wSum + " Prices: " + pSum + " After Loading From File");
    }

    public List<Integer> getPrices() {
        return prices;
    }

    public List<Integer> getWeights() {
        return weights;
    }

}
