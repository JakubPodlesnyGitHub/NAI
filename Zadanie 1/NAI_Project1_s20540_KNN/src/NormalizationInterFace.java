import java.util.ArrayList;
import java.util.List;

public interface NormalizationInterFace {
    //srednia
    default double average(List<Double> listAverage) {
        double sum = 0;
        for (int i = 0; i < listAverage.size(); i++) {
            sum += listAverage.get(i);
        }
        return sum / (double) listAverage.size();
    }

    default double averagepL(List<Data> list) {
        List<Double> doubleList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            doubleList.add(list.get(i).getPetalLength());
        }
        return average(doubleList);
    }

    default double averagepW(List<Data> list) {
        List<Double> doubleList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            doubleList.add(list.get(i).getPetalWidth());
        }
        return average(doubleList);
    }

    default double averagesL(List<Data> list) {
        List<Double> doubleList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            doubleList.add(list.get(i).getSepalLength());
        }
        return average(doubleList);
    }

    default double averagesW(List<Data> list) {
        List<Double> doubleList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            doubleList.add(list.get(i).getSepalWidth());
        }
        return average(doubleList);
    }

    // Odchylenie Standartowe
    default double standardDevations(List<Double> standardDevationsList) {
        double sum = 0;
        for (int i = 0; i < standardDevationsList.size(); i++) {
            sum += Math.pow((standardDevationsList.get(i) - average(standardDevationsList)), 2);
        }
        return Math.sqrt((sum / standardDevationsList.size()));
    }

    default double standardDevationsL(List<Data> list) {
        List<Double> standardDevationsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            standardDevationsList.add(list.get(i).getSepalLength());
        }
        return standardDevations(standardDevationsList);
    }

    default double standardDevationsW(List<Data> list) {
        List<Double> standardDevationsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            standardDevationsList.add(list.get(i).getSepalWidth());
        }
        return standardDevations(standardDevationsList);
    }

    default double standardDevationpL(List<Data> list) {
        List<Double> standardDevationsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            standardDevationsList.add(list.get(i).getPetalLength());
        }
        return standardDevations(standardDevationsList);
    }

    default double standardDevationpW(List<Data> list) {
        List<Double> standardDevationsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            standardDevationsList.add(list.get(i).getPetalWidth());
        }
        return standardDevations(standardDevationsList);
    }

    //normalizacja
    default double normalizedParameter(double value, double avg, double sD) {
        return (value - avg) / sD;
    }

    default void normalizedVector(Data data, double [] tabArguments) {
        data.setNormalizedSepalLength(normalizedParameter(data.getSepalLength(),tabArguments[0],tabArguments[4]));
        data.setNormalizedsepalWidth(normalizedParameter(data.getSepalWidth(), tabArguments[1],tabArguments[5]));
        data.setNormalizedpetalLength(normalizedParameter(data.getPetalLength(), tabArguments[2],tabArguments[6]));
        data.setNormalizedpetalWidth(normalizedParameter(data.getPetalWidth(), tabArguments[3],tabArguments[7]));
    }

    default void normalization(double [] tabArguments,List<Data> list){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setNormalizedSepalLength(normalizedParameter(list.get(i).getSepalLength(),tabArguments[0],tabArguments[4]));
            list.get(i).setNormalizedsepalWidth(normalizedParameter(list.get(i).getSepalWidth(),tabArguments[1],tabArguments[5]));
            list.get(i).setNormalizedpetalLength(normalizedParameter(list.get(i).getPetalLength(),tabArguments[2],tabArguments[6]));
            list.get(i).setNormalizedpetalWidth(normalizedParameter(list.get(i).getPetalWidth(),tabArguments[3],tabArguments[7]));
        }
    }

    default double [] getParamters(List<Data> trenList) {
        return new double[]{averagesL(trenList),averagesW(trenList),averagepL(trenList),averagepW(trenList),standardDevationsL(trenList),standardDevationsW(trenList),standardDevationpL(trenList),standardDevationpW(trenList)};
    }

}
