import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TrenSetData implements NormalizationInterFace {
    private List<Data> trenDataList;

    public TrenSetData() throws IOException {
        trenDataList = new ArrayList<>();
        readTrenFile("TrenSetIris.csv");
    }

    public int getTrenDataListSize() {
        return trenDataList.size();
    }

    public List<Data> getTrenDataList() {
        return trenDataList;
    }

    public void countDistance(Data dataTestFlower) {
        for(int i =0; i<trenDataList.size();i++){
            double d = (Math.sqrt(Math.pow((dataTestFlower.getNormalizedSepalLength() - trenDataList.get(i).getNormalizedSepalLength()), 2)
                    + Math.pow((dataTestFlower.getNormalizedsepalWidth() - trenDataList.get(i).getNormalizedsepalWidth()), 2)
                    + Math.pow((dataTestFlower.getNormalizedpetalLength() - trenDataList.get(i).getNormalizedpetalLength()), 2)
                    + Math.pow((dataTestFlower.getNormalizedpetalWidth() - trenDataList.get(i).getNormalizedpetalWidth()), 2)));
            trenDataList.get(i).setDistance(d);
        }
    }

    ////////

    private void readTrenFile(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
        String textLine;
        String[] dataTMP;
        while ((textLine = bufferedReader.readLine()) != null) {
            dataTMP = textLine.split(",");
            trenDataList.add(new Data(Double.parseDouble(dataTMP[1]), Double.parseDouble(dataTMP[2]), Double.parseDouble(dataTMP[3]), Double.parseDouble(dataTMP[4]), dataTMP[5]));
        }
        this.normalization(this.getParamters(trenDataList),trenDataList);
    }

    public Map<String, Integer> findKElements(int k) {
        trenDataList.sort(Comparator.comparing(Data::getDistance));
        Map<String, Integer> mapTMP = new HashMap<>();
        for (int i = 0; i < k; i++) {
            if (!mapTMP.containsKey(trenDataList.get(i).getName())) {
                mapTMP.put(trenDataList.get(i).getName(), 1);
            } else {
                mapTMP.put(trenDataList.get(i).getName(), mapTMP.get(trenDataList.get(i).getName()) + 1);
            }
        }
        return mapTMP;
    }

    public String classification(Map<String, Integer> map) {
        return Collections.max(map.entrySet(), Comparator.comparing(Map.Entry::getValue)).getKey();
    }

}
